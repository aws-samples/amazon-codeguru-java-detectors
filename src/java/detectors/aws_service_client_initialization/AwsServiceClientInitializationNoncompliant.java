/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.aws_service_client_initialization;

import com.amazonaws.services.datapipeline.DataPipeline;
import com.amazonaws.services.datapipeline.DataPipelineAsyncClientBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

// {fact rule=aws-service-client-initialization@v1.0 defects=1}
public class AwsServiceClientInitializationNoncompliant implements RequestHandler<String, Void> {

    private DataPipeline dataPipeline;

    public void AwsServiceClientInitializationNoncompliant() {
        // Noncompliant: AWS region provider not specified.
        dataPipeline = DataPipelineAsyncClientBuilder.defaultClient();
    }

    @Override
    public Void handleRequest(String requestEvent, Context context) {
        // Handle the request here.
        return null;
    }
}
// {/fact}
