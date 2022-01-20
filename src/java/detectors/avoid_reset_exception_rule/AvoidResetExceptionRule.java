/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.avoid_reset_exception_rule;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import java.io.InputStream;

@Slf4j
public class AvoidResetExceptionRule {

    // {fact rule=avoid-reset-exception-rule@v1.0 defects=1}
    public void s3PutObjectNoncompliant(String bucket, String key, InputStream content,
                                        ObjectMetadata metadata, AmazonS3 s3Client, String owner) {
        log.info("Putting content into bucket {} and key {}", bucket, key);
        // Noncompliant: readLimit not set.
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, content, metadata);
        putObjectRequest.setExpectedBucketOwner(owner);
        s3Client.putObject(putObjectRequest);
    }
    // {/fact}

    // {fact rule=avoid-reset-exception-rule@v1.0 defects=0}
    public void s3PutObjectCompliant(String bucket, String key, InputStream content,
                                     ObjectMetadata metadata, AmazonS3 s3Client, String owner) {
        log.info("Putting content into bucket {} and key {}", bucket, key);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, content, metadata);
        putObjectRequest.setExpectedBucketOwner(owner);
        // Compliant: readLimit is set.
        putObjectRequest.getRequestClientOptions().setReadLimit(100);
        s3Client.putObject(putObjectRequest);
    }
    // {/fact}
}
