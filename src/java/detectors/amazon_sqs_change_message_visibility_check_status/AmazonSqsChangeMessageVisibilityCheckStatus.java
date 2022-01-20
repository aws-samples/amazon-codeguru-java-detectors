/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.amazon_sqs_change_message_visibility_check_status;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityRequest;
import com.amazonaws.services.sqs.model.MessageNotInflightException;
import lombok.extern.slf4j.Slf4j;

import static java.lang.String.format;

@Slf4j
public class AmazonSqsChangeMessageVisibilityCheckStatus {

    // {fact rule=amazon-sqs-change-message-visibility-check-status@v1.0 defects=1}
    public void changeSqsMessageVisibilityNoncompliant(AmazonSQS amazonSqsClient, ChangeMessageVisibilityRequest request) {
        // Noncompliant: MessageNotInFlight exception is not checked when changing message visibility.
        amazonSqsClient.changeMessageVisibility(request);
    }
    // {/fact}

    // {fact rule=amazon-sqs-change-message-visibility-check-status@v1.0 defects=0}
    public void changeSqsMessageVisibilityCompliant(AmazonSQS amazonSqsClient, ChangeMessageVisibilityRequest request) {
        // Compliant: MessageNotInFlight exception is checked when changing message visibility.
        try {
            amazonSqsClient.changeMessageVisibility(request);
        } catch (MessageNotInflightException ex) {
            log.info(format("Message with receipt handle %s already visible. Too late to abandon", request.getReceiptHandle()));
        } catch (Exception ex) {
            log.error(format("Caught unknown exception %s", request.getReceiptHandle()), ex);
        }
    }
    // {/fact}
}
