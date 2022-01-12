/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package rules.aws_client_per_invocation;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.AmazonS3;

// {fact rule=aws-client-per-invocation@v1.0 defects=0}
public class AwsClientPerInvocationCompliant implements RequestHandler<String, Void> {

    private AmazonS3 s3Client;

    public AwsClientPerInvocationCompliant() {
        // Compliant: the client is created only once by the constructor.
        s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_WEST_2).build();
    }

    @Override
    public Void handleRequest(String requestEvent, Context context) {
        // Handle the request here.
        return null;
    }
}
// {/fact}
