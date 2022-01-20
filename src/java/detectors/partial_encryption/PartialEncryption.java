/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.partial_encryption;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.SSEAwsKeyManagementParams;
import java.io.File;

public class PartialEncryption {

    // {fact rule=partial-encryption@v1.0 defects=1}
    public void s3PutObjectNoncompliant(AmazonS3 s3Client, String bucketName, String partFileKey, String kmsKeyId,
                                        File partFile, String bucketOwner) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, partFileKey, partFile).withExpectedBucketOwner(bucketOwner);
        // Noncompliant: encryption is not performed in all paths.
        if (kmsKeyId != null) {
            putObjectRequest.setSSEAwsKeyManagementParams(new SSEAwsKeyManagementParams(kmsKeyId));
        }
        s3Client.putObject(putObjectRequest);
    }
    // {/fact}

    // {fact rule=partial-encryption@v1.0 defects=0}
    public void s3PutObjectCompliant(AmazonS3 s3Client, String bucketName, String partFileKey, String kmsKeyId,
                                     File partFile, String bucketOwner) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, partFileKey, partFile).withExpectedBucketOwner(bucketOwner);
        // Compliant: encryption is performed in all paths.
        if (kmsKeyId != null) {
            putObjectRequest.setSSEAwsKeyManagementParams(new SSEAwsKeyManagementParams(kmsKeyId));
        }
        else {
            putObjectRequest.setSSEAwsKeyManagementParams(new SSEAwsKeyManagementParams());
        }
        s3Client.putObject(putObjectRequest);
    }
    // {/fact}
}
