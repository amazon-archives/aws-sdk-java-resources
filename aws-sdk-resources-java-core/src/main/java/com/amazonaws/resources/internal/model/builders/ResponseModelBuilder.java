package com.amazonaws.resources.internal.model.builders;

import java.util.List;

import com.amazonaws.resources.internal.model.DataMapping;
import com.amazonaws.resources.internal.model.ResourceMapping;
import com.amazonaws.resources.internal.model.ResponseModel;

public class ResponseModelBuilder implements Builder<ResponseModel> {

    private ResourceMapping resourceMapping;
    private DataMapping dataMapping;
    private List<String> nextTokenPath;

    public ResourceMapping getResourceMapping() {
        return resourceMapping;
    }

    public void setResourceMapping(ResourceMapping resourceMapping) {
        this.resourceMapping = resourceMapping;
    }

    public DataMapping getDataMapping() {
        return dataMapping;
    }

    public void setDataMapping(DataMapping dataMapping) {
        this.dataMapping = dataMapping;
    }

    public List<String> getNextTokenPath() {
        return nextTokenPath;
    }

    public void setNextTokenPath(List<String> nextTokenPath) {
        this.nextTokenPath = nextTokenPath;
    }

    @Override
    public ResponseModel build() {
        return new ResponseModel(resourceMapping, dataMapping, nextTokenPath);
    }
}
