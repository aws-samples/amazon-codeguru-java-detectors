/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.aws_inefficient_chain;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;

import java.io.IOException;

public class AwsInefficientChain {
    // {fact rule=aws-inefficient-chain@v1.0 defects=1}
    private String inefficientApiCallsNoncompliant(final String bucketName, final String key) throws IOException {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        // Noncompliant: uses inefficient chain of API calls over an efficient single API call.
        S3Object s3object = s3Client.getObject(bucketName, key);
        try {
            return s3object.getObjectMetadata().getVersionId();
        } finally {
            s3object.close();
        }

    }
    // {/fact}

    // {fact rule=aws-inefficient-chain@v1.0 defects=0}
    private String efficientApiCallsCompliant(final String bucketName, final String key) {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        // Compliant: uses an efficient single API call over inefficient chain of API calls.
        return s3Client.getObjectMetadata(bucketName, key).getVersionId();
    }
    // {/fact}
}
