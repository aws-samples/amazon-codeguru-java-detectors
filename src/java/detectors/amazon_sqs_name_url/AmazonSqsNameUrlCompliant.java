/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.amazon_sqs_name_url;

import com.amazonaws.services.sqs.model.Message;
import stubs.sqsMetricsClient;
import java.util.concurrent.Callable;

// {fact rule=amazon-sqs-name-url@v1.0 defects=0}
public abstract class AmazonSqsNameUrlCompliant implements Callable<Message> {

    public void compliant() {
        final String queueUrl = "queueUrl";
        // Compliant: queue URL passed to SQS instead of queue name.
        sqsMetricsClient.changeMessageVisibility(queueUrl, "ReceiptHandle", "VisibilityTimeout");
    }
}
// {/fact}
