/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.aws_batch_write_output_ignored;

import com.amazonaws.services.sqs.model.BatchResultErrorEntry;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.SendMessageBatchResult;
import java.util.List;
import java.util.stream.Collectors;

public class AwsBatchWriteOutputIgnored {

    // {fact rule=aws-batch-write-output-ignored@v1.0 defects=1}
    public void flushNoncompliant(final SqsClient amazonSqs,
                                  final String sqsEndPoint,
                                  final List<SendMessageBatchRequestEntry> batch)
            throws CloneNotSupportedException {
        if (batch.isEmpty()) {
            return;
        }
        SendMessageBatchResult sendResult =
                amazonSqs.sendMessageBatch(sqsEndPoint, batch);
        // Noncompliant: no checks to handle errors returned by batch operations.
        batch.clear();
    }

    // {/fact}

    // {fact rule=aws-batch-write-output-ignored@v1.0 defects=0}
    public void flushCompliant(final SqsClient amazonSqs,
                               final String sqsEndPoint,
                               final List<SendMessageBatchRequestEntry> batch)
            throws SQSUpdateException, CloneNotSupportedException {
        if (batch.isEmpty() || sqsEndPoint == null) {
            return;
        }
        SendMessageBatchResult sendResult =
                amazonSqs.sendMessageBatch(sqsEndPoint, batch);
        if (sendResult == null) {
            return;
        } else {
            final List<BatchResultErrorEntry> failed = sendResult.getFailed();
            // Compliant: has checks to handle errors returned by batch operations.
            if (!failed.isEmpty()) {
                final String failedMessage = failed.stream()
                        .map(batchResultErrorEntry -> String.format("messageId:%s failedReason:%s",
                                batchResultErrorEntry.getId(), batchResultErrorEntry.getMessage()))
                        .collect(Collectors.joining(","));
                throw new SQSUpdateException("Error occurred while sending messages to SQS::" + failedMessage);
            }
        }
    }
    // {/fact}

    public class SqsClient {
        public SendMessageBatchResult sendMessageBatch(String sqsEndPoint, List<SendMessageBatchRequestEntry> batch)
                throws CloneNotSupportedException {
            SendMessageBatchResult result = (SendMessageBatchResult) clone();
            return result;
        }
    }

    private class SQSUpdateException extends Throwable {
        public SQSUpdateException(String s) {
        }
    }
}
