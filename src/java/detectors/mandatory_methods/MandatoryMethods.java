/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.mandatory_methods;
import com.amazonaws.services.simplesystemsmanagement.model.PutParameterRequest;

public class MandatoryMethods {
    // {fact rule=mandatory-methods@v1.0 defects=1}
    public void checkParameterDescriptionAllCallNoncompliant() {
        // Noncompliant: does not call mandatory methods on the object after its creation.
        PutParameterRequest putParameterRequest = new PutParameterRequest();
        putParameterRequest.setName("parameterName");
    }
    // {/fact}

    // {fact rule=mandatory-methods@v1.0 defects=0}
    public void checkParameterDescriptionAllCallCompliant() {
        // Compliant: calls the mandatory methods on the objects after its creation.
        PutParameterRequest putParameterRequest = new PutParameterRequest();
        putParameterRequest.setDescription("Description");
        putParameterRequest.setName("parameterName");
    }
    // {/fact}
}
