/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package rules.aws_service_client_initialization;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.datapipeline.DataPipeline;
import com.amazonaws.services.lambda.runtime.Client;
import com.amazonaws.services.datapipeline.DataPipelineAsyncClientBuilder;

public class AwsServiceClientInitialization {

    // {fact rule=aws-service-client-initialization@v1.0 defects=1}
    public void createDataPipelineNonCompliant() {
        // Noncompliant: AWS region provider not specified.
        DataPipeline client = DataPipelineAsyncClientBuilder.defaultClient();
    }
    // {/fact}

    // {fact rule=aws-service-client-initialization@v1.0 defects=0}
    public void createDataPipelineCompliant() {
        // Compliant: AWS region provider specified.
        DataPipeline client = DataPipelineAsyncClientBuilder.standard().withRegion(Regions.US_WEST_2).build();
    }
    // {/fact}
}
