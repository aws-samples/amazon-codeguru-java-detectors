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

public class LambdaEnvironmentOverrideCompliant implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent,
                                                      Context context) {

        overrideEnvironmentCompliant();
        return null;
    }

    // {fact rule=lambda-override-reserved@v1.0 defects=0}
    private void overrideEnvironmentCompliant() {
        final Map<String, String> environment = new ProcessBuilder().environment();
        // Compliant: overrides an unreserved environment variable name in a AWS Lambda function.
        environment.put("LANG", "en_US.UTF-8");
    }
    // {/fact}
}
