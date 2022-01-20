/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.aws_service_client_initialization;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.datapipeline.DataPipeline;
import com.amazonaws.services.datapipeline.DataPipelineAsyncClientBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

// {fact rule=aws-service-client-initialization@v1.0 defects=0}
public class AwsServiceClientInitializationCompliant implements RequestHandler<String, Void> {

    private DataPipeline dataPipeline;

    public void AwsServiceClientInitializationCompliant() {
        // Compliant: AWS region provider specified.
        dataPipeline = DataPipelineAsyncClientBuilder.standard().withRegion(Regions.US_WEST_2).build();
    }

    @Override
    public Void handleRequest(String requestEvent, Context context) {
        // Handle the request here.
        return null;
    }
}
// {/fact}
