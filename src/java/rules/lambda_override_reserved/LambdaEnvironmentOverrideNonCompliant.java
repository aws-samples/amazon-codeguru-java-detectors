/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package rules.lambda_override_reserved;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.Map;

public class LambdaEnvironmentOverrideNonCompliant implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent,
                                                      Context context) {

        overrideEnvironmentNonCompliant();
        return null;
    }

    // {fact rule=lambda-override-reserved@v1.0 defects=1}
    private void overrideEnvironmentNonCompliant() {
        final Map<String, String> environment = new ProcessBuilder().environment();
        // Noncompliant: overrides reserved environment variable names in a Lambda function.
        environment.put("AWS_REGION", "us-west-2");
    }
    // {/fact}

}
