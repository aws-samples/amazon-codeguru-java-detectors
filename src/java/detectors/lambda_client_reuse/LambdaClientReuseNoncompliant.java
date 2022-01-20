/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.lambda_client_reuse;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

// {fact rule=lambda-client-reuse@v1.0 defects=1}
public class LambdaClientReuseNoncompliant implements RequestHandler<String, Void> {

    private AmazonS3 s3Client;


    public Void handleRequest(String requestEvent, Context context) {
        System.err.println("Nothing to see here");
        createBucketNoncompliant();
        return null;
    }

    private void createBucketNoncompliant() {
        s3Client.createBucket("bucketName");
        // Noncompliant: recreates AWS clients in each lambda invocation.
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1).build();
    }
}
// {/fact}
