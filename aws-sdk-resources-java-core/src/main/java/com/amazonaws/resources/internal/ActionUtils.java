package com.amazonaws.resources.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.internal.model.ActionModel;
import com.amazonaws.resources.internal.model.FlatMapping;
import com.amazonaws.resources.internal.model.PathSourceMapping;
import com.amazonaws.resources.internal.model.PathTargetMapping;
import com.amazonaws.resources.internal.model.RequestModel;
import com.amazonaws.resources.internal.model.ResourceMapping;
import com.amazonaws.resources.internal.model.ResourceModel;
import com.amazonaws.resources.internal.model.ResponseModel;

/**
 * A helper class containing logic for performing actions on a resource or a
 * collection.
 */
final class ActionUtils {
    /**
     * Performs the given action passing the given parameters.
     *
     * @param context the context on which this action is being called
     * @param action the model of the action to perform
     * @param request the client-supplied request object
     * @param extractor a result extractor object
     * @return the result of the action
     */
    public static ActionResult perform(
            ActionContext context,
            ActionModel action,
            AmazonWebServiceRequest request,
            ResultCapture<Object> extractor) {

        return perform(context, action, request, extractor, null);
    }

    /**
     * Performs the given action passing the given parameters.
     *
     * @param context the context on which this action is being called
     * @param action the model of the action to perform
     * @param request the client-supplied request object
     * @param token the pagination token for the action
     * @param extractor a result extractor object
     * @return the result of the action
     */
    public static ActionResult perform(
            ActionContext context,
            ActionModel action,
            AmazonWebServiceRequest request,
            ResultCapture<Object> extractor,
            Object token) {

        try {

            Method method = findClientMethod(
                    context.getClient(), action.getRequest().getMethod());

            request = generateRequest(
                    context,
                    method.getParameterTypes()[0],
                    action.getRequest(),
                    request,
                    token);

            Object clientResult = method.invoke(context.getClient(), request);

            if (extractor != null) {
                Map<String, String> responseMetadata =
                        getResponseMetadata(context, request);

                extractor.setResponseMetadata(responseMetadata);
                extractor.setClientResult(clientResult);
            }

            ResponseModel responseModel = action.getResponse();

            Object data = null;
            List<ResourceImpl> resources = null;
            Object nextToken = null;

            if (responseModel != null) {
                if (responseModel.getDataMapping() != null) {
                    data = getResultAttributes(
                        responseModel.getDataMapping().getSource(),
                        clientResult);

                } else if (responseModel.getResourceMapping() != null) {
                    resources = getResultResources(
                        context,
                        responseModel.getResourceMapping(),
                        request,
                        clientResult);

                } else {
                    data = clientResult;
                }

                if (responseModel.getNextTokenPath() != null) {
                    nextToken = ReflectionUtils.getByPath(
                        clientResult, responseModel.getNextTokenPath());
                }
            }

            return new ActionResult(data, resources, nextToken);

        } catch (IllegalAccessException | InstantiationException exception) {
            throw new IllegalStateException("BOOM", exception);

        } catch (InvocationTargetException exception) {
            if (exception.getCause() instanceof RuntimeException) {
                throw (RuntimeException) exception.getCause();
            }
            throw new IllegalStateException("BOOM", exception);
        }
    }

    /**
     * Generates a client-level request by extracting the user parameters (if
     */
    private static AmazonWebServiceRequest generateRequest(
            ActionContext context,
            Class<?> type,
            RequestModel model,
            AmazonWebServiceRequest request,
            Object token)
                    throws InstantiationException, IllegalAccessException {

        if (request == null) {
            request = (AmazonWebServiceRequest) type.newInstance();
        }

        for (PathTargetMapping mapping : model.getIdentifierMappings()) {
            Object value = context.getIdentifier(mapping.getSource());
            if (value == null) {
                throw new IllegalStateException(
                        "Action has a mapping for identifier "
                        + mapping.getSource() + ", but the target has no "
                        + "identifier of that name!");
            }
            ReflectionUtils.setByPath(request, value, mapping.getTarget());
        }

        for (PathTargetMapping mapping : model.getAttributeMappings()) {
            Object value = context.getAttribute(mapping.getSource());
            if (value == null) {
                // TODO: Is this ever valid?
                throw new IllegalStateException(
                        "Action has a mapping for attribute "
                        + mapping.getSource() + ", but the target has no "
                        + "attribute of that name!");
            }
            ReflectionUtils.setByPath(request, value, mapping.getTarget());
        }

        for (PathTargetMapping mapping : model.getConstantMappings()) {
            // TODO: This only works for strings; can we do better?
            ReflectionUtils.setByPath(
                    request, mapping.getSource(), mapping.getTarget());
        }

        if (token != null) {
            List<String> tokenPath = model.getTokenPath();
            if (tokenPath == null) {
                throw new IllegalArgumentException(
                        "Cannot pass a token with a null token path");
            }

            ReflectionUtils.setByPath(request, token, tokenPath);
        }

        return request;
    }

    private static Object getResultAttributes(
            List<String> basePath,
            Object result) {

        boolean multivalued = basePath.contains("*");

        if (multivalued) {
            return Collections.unmodifiableList(
                    ReflectionUtils.getAllByPath(result, basePath));
        } else {
            return ReflectionUtils.getByPath(result, basePath);
        }
    }

    private static List<ResourceImpl> getResultResources(
            ActionContext context,
            ResourceMapping mapping,
            AmazonWebServiceRequest request,
            Object result) {

        List<Map<String, Object>> identifiers =
                extractIdentifiers(context, mapping, request, result);

        List<Object> data = null;
        if (mapping.getPath() != null) {
            data = ReflectionUtils.getAllByPath(result, mapping.getPath());
        }

        ResourceModel refTypeModel =
                context.getServiceModel().getResource(mapping.getType());

        List<ResourceImpl> rval = new ArrayList<>(identifiers.size());

        for (int i = 0; i < identifiers.size(); ++i) {
            Object attributes = null;
            if (data != null) {
                attributes = data.get(i);
            }

            rval.add(new ResourceImpl(
                    context.getServiceModel(),
                    refTypeModel,
                    context.getClient(),
                    identifiers.get(i),
                    attributes));
        }

        return Collections.unmodifiableList(rval);
    }

    private static List<Map<String, Object>> extractIdentifiers(
            ActionContext context,
            ResourceMapping mapping,
            AmazonWebServiceRequest request,
            Object result) {

        Map<String, Object> ids =
                extractSharedIdentifiers(context, mapping, request);

        Map<String, List<Object>> multiIds =
                extractResponseIdentifiers(mapping, result);

        if (multiIds.isEmpty()) {
            return Collections.singletonList(ids);
        }

        List<Map<String, Object>> rval = new ArrayList<>();

        for (Map.Entry<String, List<Object>> entry : multiIds.entrySet()) {
            String key = entry.getKey();
            List<Object> values = entry.getValue();

            for (int i = 0; i < values.size(); ++i) {
                if (rval.size() == i) {
                    rval.add(new HashMap<String, Object>());
                }
                rval.get(i).put(key, values.get(i));
            }
        }

        for (Map.Entry<String, Object> entry : ids.entrySet()) {
            for (Map<String, Object> map : rval) {
                map.put(entry.getKey(), entry.getValue());
            }
        }

        return rval;
    }

    private static Map<String, Object> extractSharedIdentifiers(
            ActionContext context,
            ResourceMapping mapping,
            AmazonWebServiceRequest request) {

        Map<String, Object> ids = new HashMap<>();

        for (FlatMapping m : mapping.getParentIdentifierMappings()) {
            Object value = context.getIdentifier(m.getSource());
            if (value == null) {
                throw new IllegalStateException(
                        "This action metadata has a mapping "
                        + "for the " + m.getSource() + " identifier, but "
                        + "this resource doesn't have an identifier of that "
                        + "name!");
            }
            ids.put(m.getTarget(), value);
        }

        for (PathSourceMapping m : mapping.getRequestParamMappings()) {
            Object value = ReflectionUtils.getByPath(request, m.getSource());
            if (value == null) {
                throw new IllegalStateException(
                        "This action has a mapping for the " + m.getSource()
                        + " request parameter, but you somehow managed to "
                        + "sneak through a request without that parameter "
                        + "specified!");
            }
            ids.put(m.getTarget(), value);
        }

        return ids;
    }

    private static Map<String, List<Object>> extractResponseIdentifiers(
            ResourceMapping mapping,
            Object result) {

        Map<String, List<Object>> ids = new HashMap<>();

        int listSize = -1;
        for (PathSourceMapping m : mapping.getResponseIdentifierMappings()) {
            List<Object> values =
                    ReflectionUtils.getAllByPath(result, m.getSource());

            if (listSize == -1) {
                listSize = values.size();
            } else if (values.size() != listSize) {
                throw new IllegalStateException(
                        "List size mismatch! " + listSize + " vs "
                        + values.size());
            }

            ids.put(m.getTarget(), values);
        }

        return ids;
    }

    private static Map<String, String> getResponseMetadata(
            ActionContext context,
            Object parameter)
                    throws IllegalAccessException, InvocationTargetException {

        Method method = tryFindClientMethod(
                context.getClient(), "getCachedResponseMetadata");
        if (method == null) {
            return Collections.<String, String>emptyMap();
        }

        Object result = method.invoke(context.getClient(), parameter);
        if (result == null) {
            return Collections.<String, String>emptyMap();
        }

        Map<String, String> metadata = new HashMap<>();

        for (Method getter : result.getClass().getMethods()) {
            String name = getter.getName();
            if (name.startsWith("get")
                    && getter.getParameterTypes().length == 0
                    && getter.getReturnType().equals(String.class)) {

                String value = (String) getter.invoke(result);
                metadata.put(name.substring(3), value);
            }
        }

        return Collections.unmodifiableMap(metadata);
    }

    private static Method findClientMethod(Object client, String name) {
        Method result = tryFindClientMethod(client, name);

        if (result == null) {
            throw new RuntimeException("No client method named " + name);
        }

        return result;
    }

    private static Method tryFindClientMethod(Object client, String name) {
        // TODO: Cache me.
        for (Method method : client.getClass().getMethods()) {
            if (!method.getName().equals(name)) {
                continue;
            }

            Class<?>[] parameters = method.getParameterTypes();
            if (parameters.length != 1) {
                continue;
            }

            // This is the inverse of the normal approach of findMethod() -
            // we're looking for a method which will accept a specific subtype
            // of AmazonWebServiceRequest, without worrying overmuch about
            // what subtype it is. We'll create an object of the appropriate
            // type and fill it in.
            if (AmazonWebServiceRequest.class.isAssignableFrom(parameters[0])) {
                return method;
            }
        }

        return null;
    }

    private ActionUtils() {
    }
}