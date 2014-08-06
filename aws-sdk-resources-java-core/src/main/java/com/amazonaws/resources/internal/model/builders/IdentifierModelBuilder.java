package com.amazonaws.resources.internal.model.builders;

import com.amazonaws.resources.internal.model.IdentifierModel;

public class IdentifierModelBuilder implements Builder<IdentifierModel> {
    private String type;

    /**
     * @return the type of this identifier
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{type=" + type + "}";
    }

    @Override
    public IdentifierModel build() {
        return new IdentifierModel(type);
    }
}
