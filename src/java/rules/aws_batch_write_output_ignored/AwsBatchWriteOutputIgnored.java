/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package rules.aws_batch_write_output_ignored;

import com.amazonaws.services.sqs.model.BatchResultErrorEntry;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.SendMessageBatchResult;

import java.util.List;
import java.util.stream.Collectors;

public class AwsBatchWriteOutputIgnored {

    // {fact rule=aws-batch-write-output-ignored@v1.0 defects=1}
    public void flushNonCompliant(final String sqsEndPoint,
                                 final List<SendMessageBatchRequestEntry> batch)
    {
        final SqsClient amazonSqs=null;
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
    public void flushCompliant(final String sqsEndPoint,
                                 final List<SendMessageBatchRequestEntry> batch) throws SQSUpdateException {
        final SqsClient amazonSqs=null;
        if (batch.isEmpty() || sqsEndPoint == null) {
            return;
        }
        SendMessageBatchResult sendResult =
                amazonSqs.sendMessageBatch(sqsEndPoint, batch);

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
    // {/fact}

    public class SqsClient {
        public SendMessageBatchResult sendMessageBatch(String sqsEndPoint, List<SendMessageBatchRequestEntry> batch) {
            SendMessageBatchResult result = null;
            return result;
        }
    }

    private class SQSUpdateException extends Throwable {
        public SQSUpdateException(String s) {
        }
    }
}
