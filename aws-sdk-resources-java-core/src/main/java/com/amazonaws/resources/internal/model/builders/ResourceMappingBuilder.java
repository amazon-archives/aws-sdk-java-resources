package com.amazonaws.resources.internal.model.builders;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.resources.internal.model.FlatMapping;
import com.amazonaws.resources.internal.model.PathSourceMapping;
import com.amazonaws.resources.internal.model.ResourceMapping;

public class ResourceMappingBuilder implements Builder<ResourceMapping> {

    private String type;
    private List<FlatMapping> parentIdentifierMappings;
    private List<PathSourceMapping> requestParamMappings;
    private List<PathSourceMapping> responseIdentifierMappings;
    private List<String> path;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FlatMapping> getParentIdentifierMappings() {
        return parentIdentifierMappings;
    }

    public void setParentIdentifierMappings(
            List<FlatMapping> parentIdentifierMappings) {
        this.parentIdentifierMappings = parentIdentifierMappings;
    }

    public List<PathSourceMapping> getResponseIdentifierMappings() {
        return responseIdentifierMappings;
    }

    public void setResponseIdentifierMappings(
            List<PathSourceMapping> responseIdentifierMappings) {
        this.responseIdentifierMappings = responseIdentifierMappings;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public void addParentIdentifierMapping(FlatMapping flatMapping){
        if(parentIdentifierMappings == null){
            parentIdentifierMappings = new ArrayList<FlatMapping>();
        }
        parentIdentifierMappings.add(flatMapping);
    }

    public void addResponseIdentifierMapping(PathSourceMapping pathSourceMapping){
        if(responseIdentifierMappings == null){
            responseIdentifierMappings = new ArrayList<PathSourceMapping>();
        }
        responseIdentifierMappings.add(pathSourceMapping);
    }

    public void addRequestParamMapping(PathSourceMapping pathSourceMapping){
        if(requestParamMappings == null){
            requestParamMappings = new ArrayList<PathSourceMapping>();
        }
        requestParamMappings.add(pathSourceMapping);
    }

    @Override
    public ResourceMapping build() {
        return new ResourceMapping(
                type,
                parentIdentifierMappings,
                requestParamMappings,
                responseIdentifierMappings,
                path);
    }

}
