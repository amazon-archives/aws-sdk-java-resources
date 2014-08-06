package com.amazonaws.resources.internal;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.internal.model.ActionModel;
import com.amazonaws.resources.internal.model.CollectionModel;
import com.amazonaws.resources.internal.model.ResourceModel;
import com.amazonaws.resources.internal.model.ServiceModel;
import com.amazonaws.resources.internal.model.SubResourceGetterModel;

/**
 * @param <C> client interface type
 */
public final class ServiceImpl<C> implements ActionContext {

    private final ServiceModel model;
    private final C client;

    public ServiceImpl(ServiceModel model, C client) {
        this.model = model;
        this.client = client;
    }

    @Override
    public boolean hasState() {
        return false;
    }

    @Override
    public ServiceModel getServiceModel() {
        return model;
    }

    @Override
    public C getClient() {
        return client;
    }

    @Override
    public Object getIdentifier(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getAttribute(String name) {
        throw new UnsupportedOperationException();
    }

    public ResourceImpl getSubResource(String name, Object parameter) {
        SubResourceGetterModel getter = model.getSubResourceGetter(name);
        if (getter == null) {
            throw new UnsupportedOperationException(
                    "No top-level-resource named " + name);
        }

        Map<String, Object> ids = new HashMap<>();

        if (getter.getParameterMapping() != null) {
            ids.put(getter.getParameterMapping().getTarget(), parameter);
        }

        ResourceModel refTypeModel = model.getResource(name);

        return new ResourceImpl(model, refTypeModel, client, ids);
    }

    public ResourceCollectionImpl getCollection(
            String name,
            AmazonWebServiceRequest request) {

        CollectionModel collection = model.getCollection(name);
        if (collection == null) {
            throw new IllegalArgumentException("No collection named " + name);
        }

        return new ResourceCollectionImpl(
                this,
                collection.getListAction(),
                request);
    }

    public ActionResult performAction(
            String name,
            AmazonWebServiceRequest request,
            ResultCapture<?> extractor) {

        ActionModel action = model.getAction(name);
        if (action == null) {
            throw new UnsupportedOperationException(
                    "Service does not support the action " + name);
        }

        // The facade generator will ensure we only ever pass in an
        // appropriately-typed extractor object.
        @SuppressWarnings("unchecked")
        ResultCapture<Object> erasedExtractor =
                (ResultCapture<Object>) extractor;

        return ActionUtils.perform(this, action, request, erasedExtractor);
    }

    @Override
    public String toString() {
        return model.getName();
    }
}
