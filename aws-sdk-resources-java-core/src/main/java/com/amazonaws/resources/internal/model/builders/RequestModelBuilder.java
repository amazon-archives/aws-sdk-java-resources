package com.amazonaws.resources.internal.model.builders;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.resources.internal.model.ParameterModel;
import com.amazonaws.resources.internal.model.PathTargetMapping;
import com.amazonaws.resources.internal.model.RequestModel;

public class RequestModelBuilder implements Builder<RequestModel> {

    private String method;
    private String clientRequestType;

    private List<PathTargetMapping> identifierMappings;
    private List<PathTargetMapping> attributeMappings;
    private List<ParameterModel> parameterMappings;
    private List<PathTargetMapping> constantMappings;
    private List<String> tokenPath;
    private List<String> limitPath;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getClientRequestType() {
        return clientRequestType;
    }

    public void setClientRequestType(String clientRequestType) {
        this.clientRequestType = clientRequestType;
    }

    public List<PathTargetMapping> getIdentifierMappings() {
        return identifierMappings;
    }

    public void setIdentifierMappings(List<PathTargetMapping> identifierMappings) {
        this.identifierMappings = identifierMappings;
    }

    public List<PathTargetMapping> getAttributeMappings() {
        return attributeMappings;
    }

    public void setAttributeMappings(List<PathTargetMapping> attributeMappings) {
        this.attributeMappings = attributeMappings;
    }

    public List<ParameterModel> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterModel> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }

    public List<PathTargetMapping> getConstantMappings() {
        return constantMappings;
    }

    public void setConstantMappings(List<PathTargetMapping> constantMappings) {
        this.constantMappings = constantMappings;
    }

    public List<String> getTokenPath() {
        return tokenPath;
    }

    public void setTokenPath(List<String> tokenPath) {
        this.tokenPath = tokenPath;
    }

    public List<String> getLimitPath() {
        return limitPath;
    }

    public void setLimitPath(List<String> limitPath) {
        this.limitPath = limitPath;
    }

    public void addParameterMapping(ParameterModel parameterModel) {
        if (parameterMappings == null) {
            parameterMappings = new ArrayList<ParameterModel>();
        }
        parameterMappings.add(parameterModel);
    }

    public void addIdentifierMapping(PathTargetMapping pathTargetMapping) {
        if (identifierMappings == null) {
            identifierMappings = new ArrayList<PathTargetMapping>();
        }
        identifierMappings.add(pathTargetMapping);
    }

    public void addAttributeMapping(PathTargetMapping pathTargetMapping) {
        if (attributeMappings == null) {
            attributeMappings = new ArrayList<PathTargetMapping>();
        }
        attributeMappings.add(pathTargetMapping);
    }

    public void addConstantMapping(PathTargetMapping pathTargetMapping) {
        if (constantMappings == null) {
            constantMappings = new ArrayList<PathTargetMapping>();
        }
        constantMappings.add(pathTargetMapping);
    }

    @Override
    public RequestModel build() {
        return new RequestModel(method, clientRequestType, identifierMappings,
                attributeMappings, parameterMappings, constantMappings,
                tokenPath, limitPath);
    }

}
