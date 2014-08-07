/*
 * Copyright 2014 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.resources.identitymanagement;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.services.identitymanagement.model.DeleteAccountAliasRequest
;

/**
 * The AccountAlias resource.
 */
public interface AccountAlias {
    /**
     * Returns true if this resource's attributes have been loaded. If this
     * method returns {@code false}, calls to attribute getter methods on this
     * instance will make an implicit call to {@code load()} to retrieve the
     * value.
     */
    boolean isLoaded();

    /**
     * Gets the value of the Name identifier.
     */
    String getName();

    /**
     * Performs an action.
     */
    void delete(DeleteAccountAliasRequest request);

    /**
     * Performs an action.
     */
    void delete(DeleteAccountAliasRequest request, ResultCapture<Void> extractor
            );
}
