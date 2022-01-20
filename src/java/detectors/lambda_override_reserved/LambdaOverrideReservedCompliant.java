/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.lambda_override_reserved;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.Map;

// {fact rule=lambda-override-reserved@v1.0 defects=0}
public class LambdaOverrideReservedCompliant implements RequestHandler<String, Void> {
    public Void handleRequest(String requestEvent, Context context) {
        final Map<String, String> environment = new ProcessBuilder().environment();
        // Compliant: does not override any reserved environment variable names in a Lambda function.
        environment.put("LANG", "en_US.UTF-8");
        return null;
    }
}
// {/fact}
