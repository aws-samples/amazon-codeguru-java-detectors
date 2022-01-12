/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package rules.mutually_exclusive_calls_found;

import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.GenerateDataKeyRequest;
import software.amazon.awssdk.services.kms.model.GenerateDataKeyResponse;

public class MutuallyExclusiveCallsFound {

    // {fact rule=mutually-exclusive-calls-found@v1.0 defects=1}
    public GenerateDataKeyResponse generateDataKeyNonCompliant(String keyId) {
        String requestKeyId = "alias/ExampleAlias";
        KmsClient client = KmsClient.create();
        String keySpec = "AES_256";
        // Noncompliant: keySpec and numberOfBytes cannot be used together in GenerateDataKey request.
        GenerateDataKeyRequest request = GenerateDataKeyRequest.builder()
                .keyId(keyId)
                .keySpec(keySpec)
                .numberOfBytes(32)
                .build();
        GenerateDataKeyResponse response = client.generateDataKey(request);
        return response;
    }
    // {/fact}

    // {fact rule=mutually-exclusive-calls-found@v1.0 defects=0}
    public GenerateDataKeyResponse generateDataKeyCompliant(String keyId) {
        String requestKeyId = "alias/ExampleAlias";
        KmsClient client = KmsClient.create();
        String keySpec = "AES_256";
        // Compliant: only keySpec parameter is used.
        GenerateDataKeyRequest request = GenerateDataKeyRequest.builder()
                .keyId(requestKeyId)
                .keySpec(keySpec)
                .build();
        GenerateDataKeyResponse response = client.generateDataKey(request);
        return response;
    }
    // {/fact}
}
