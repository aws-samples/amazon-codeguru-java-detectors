/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.aws_kms_reencryption;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.DecryptRequest;
import software.amazon.awssdk.services.kms.model.EncryptRequest;
import software.amazon.awssdk.services.kms.model.ReEncryptRequest;

public class AwsKmsReencryption {
    // {fact rule=aws-kms-reencryption@v1.0 defects=1}
    public void withoutReEncryptNoncompliant(KmsClient client, SdkBytes encryptedContent) {

        DecryptRequest decryptRequest = DecryptRequest.builder()
                .ciphertextBlob(encryptedContent)
                .build();
        SdkBytes plaintext = client.decrypt(decryptRequest).plaintext();
        // Noncompliant: client-side decrypt immediately followed by encrypt.
        EncryptRequest encryptRequest = EncryptRequest.builder()
                .keyId("my-key-id")
                .plaintext(plaintext)
                .build();
        client.encrypt(encryptRequest);
    }
    // {/fact}

    // {fact rule=aws-kms-reencryption@v1.0 defects=0}
    public void withReEncryptCompliant(KmsClient client, SdkBytes encryptedContent) {
        // Compliant: uses a ReEncryptRequest which runs server-side.
        ReEncryptRequest req = ReEncryptRequest.builder()
                .ciphertextBlob(encryptedContent)
                .destinationKeyId("my-key-id")
                .build();
        client.reEncrypt(req).ciphertextBlob();
    }
    // {/fact}
}
