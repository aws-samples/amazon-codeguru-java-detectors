/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.client_constructor_deprecated_rule;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClient;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;

public class ClientConstructorDeprecatedRule {

    // {fact rule=client-constructor-deprecated-rule@v1.0 defects=1}
    public void createKinesisClientNoncompliant(){
        // Noncompliant: deprecated client constructor used.
        AmazonKinesisClient producerKinesisClient = new AmazonKinesisClient();
    }
    // {/fact}

    // {fact rule=client-constructor-deprecated-rule@v1.0 defects=0}
    public void createKinesisClientCompliant(String region) {
        // Compliant: client builder used.
        AmazonKinesis kinesis = AmazonKinesisClientBuilder.standard().withRegion(region).build();
    }
    // {/fact}
}
