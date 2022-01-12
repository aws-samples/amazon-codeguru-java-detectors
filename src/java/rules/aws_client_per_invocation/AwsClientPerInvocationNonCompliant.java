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

// {fact rule=aws-client-per-invocation@v1.0 defects=1}
public class AwsClientPerInvocationNonCompliant implements RequestHandler<String, Void> {

    private AmazonS3 s3Client;

    public void AwsClientPerInvocationNonCompliant() {
        // Initialize class members here.
    }

    @Override
    public Void handleRequest(String requestEvent, Context context) {
        // Noncompliant: a new client is created by the request handler on every invocation.
        s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_WEST_2).build();
        return null;
    }
}
// {/fact}
