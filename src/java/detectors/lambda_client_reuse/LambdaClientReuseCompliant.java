/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.lambda_client_reuse;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.AmazonS3URI;
import com.amazonaws.regions.Regions;

// {fact rule=lambda-client-reuse@v1.0 defects=0}
public class LambdaClientReuseCompliant implements RequestHandler<String, Void> {

    private AmazonS3 s3Client;
    
    public LambdaClientReuseCompliant() {
        // Compliant: creates the client only once.
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1).build();
    }

    public Void handleRequest(String requestEvent, Context context) {
        System.err.println("Nothing to see here");
        createBucketCompliant();
        insertObjectCompliant("storeOject");
        return null;
    }

    private void createBucketCompliant() {
        // Compliant: uses the cached client.
        s3Client.createBucket("storeObject");
    }

    private void insertObjectCompliant(String s3Folder) {
        AmazonS3URI amazonS3URI = new AmazonS3URI(s3Folder);
        // Compliant: uses the cached client.
        s3Client.putObject(amazonS3URI.getBucket(), amazonS3URI.getKey(), "hello");
    }
}
// {/fact}
