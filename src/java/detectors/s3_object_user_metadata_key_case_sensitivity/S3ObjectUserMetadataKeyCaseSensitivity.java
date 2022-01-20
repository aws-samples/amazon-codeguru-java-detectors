/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.s3_object_user_metadata_key_case_sensitivity;

import com.amazonaws.services.s3.model.ObjectMetadata;

public class S3ObjectUserMetadataKeyCaseSensitivity {

    // {fact rule=s3-object-user-metadata-key-case-sensitivity@v1.0 defects=1}
    public void getUserMetaDataNoncompliant(ObjectMetadata objectMetadata) {
        // Noncompliant: the metadata key contains an uppercase letter.
        objectMetadata.getUserMetaDataOf("Key");
    }
    // {/fact}

    // {fact rule=s3-object-user-metadata-key-case-sensitivity@v1.0 defects=0}
    public void getUserMetaDataCompliant(ObjectMetadata objectMetadata) {
        // Compliant: the metadata key only contains lowercase letters.
        objectMetadata.getUserMetaDataOf("key");
    }
    // {/fact}
}
