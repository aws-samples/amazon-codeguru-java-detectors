/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.s3_verify_bucket_owner;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import java.nio.file.Path;
import java.nio.file.Paths;


public class S3VerifyBucketOwner {
    // {fact rule=s3-verify-bucket-owner@v1.0 defects=1}
    public void putObjectNoncompliant() {
        S3Client s3Client = S3Client.create();
        // Noncompliant: the account that owns the bucket is not specified in the request.
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket("PUT-EXAMPLE-BUCKET")
                .key("example-key")
                .build();
        Path path = Paths.get("put_file.txt");
        s3Client.putObject(request, path);
    }
    // {/fact}

    // {fact rule=s3-verify-bucket-owner@v1.0 defects=0}
    public void putObjectCompliant() {
        S3Client s3Client = S3Client.create();
        // Compliant: the account that owns the bucket is specified in the request.
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket("PUT-EXAMPLE-BUCKET")
                .key("example-key")
                .expectedBucketOwner("111122223333")
                .build();
        Path path = Paths.get("put_file.txt");
        s3Client.putObject(request, path);
    }
    // {/fact}
}
