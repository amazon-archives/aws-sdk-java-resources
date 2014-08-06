package com.amazonaws.resources.internal.model.builders;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.resources.internal.model.ActionModel;
import com.amazonaws.resources.internal.model.CollectionModel;
import com.amazonaws.resources.internal.model.ResourceModel;
import com.amazonaws.resources.internal.model.ServiceModel;
import com.amazonaws.resources.internal.model.SubResourceGetterModel;

public class ServiceModelBuilder implements Builder<ServiceModel> {

    private String name;
    private String abbreviation;

    private String clientInterface;
    private String clientImplementation;

    private Map<String, ResourceModelBuilder> resources;

    private Map<String, SubResourceGetterModel> subResourceGetters;
    private Map<String, CollectionModel> collections;
    private Map<String, ActionModel> actions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getClientInterface() {
        return clientInterface;
    }

    public void setClientInterface(String clientInterface) {
        this.clientInterface = clientInterface;
    }

    public String getClientImplementation() {
        return clientImplementation;
    }

    public void setClientImplementation(String clientImplementation) {
        this.clientImplementation = clientImplementation;
    }

    public Map<String, ResourceModelBuilder> getResources() {
        return resources;
    }

    public void setResources(Map<String, ResourceModelBuilder> resources) {
        this.resources = resources;
    }

    public Map<String, SubResourceGetterModel> getSubResourceGetters() {
        return subResourceGetters;
    }

    public void setSubResourceGetters(
            Map<String, SubResourceGetterModel> subResourceGetters) {

        this.subResourceGetters = subResourceGetters;
    }

    public Map<String, CollectionModel> getCollections() {
        return collections;
    }

    public void setCollections(Map<String, CollectionModel> collections) {
        this.collections = collections;
    }

    public Map<String, ActionModel> getActions() {
        return actions;
    }

    public void setActions(Map<String, ActionModel> actions) {
        this.actions = actions;
    }

    @Override
    public ServiceModel build() {

        Map<String, ResourceModel> resourceModel =
                new HashMap<String, ResourceModel>();

        for (Map.Entry<String, ResourceModelBuilder> entry : resources
                .entrySet()) {
            resourceModel.put(entry.getKey(), entry.getValue().build());
        }

        return new ServiceModel(
                name,
                abbreviation,
                clientInterface,
                clientImplementation,
                resourceModel,
                subResourceGetters,
                collections,
                actions);
    }

}
