/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.kcl_with_call_process_records;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.InitialPositionInStream;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.KinesisClientLibConfiguration;

public class KclWithCallProcessRecords {

    // {fact rule=kcl-with-call-process-records@v1.0 defects=1}
    public KinesisClientLibConfiguration configureKCLNoncompliant() {
        // Noncompliant: doesn't set withCallProcessRecordsEvenForEmptyRecordList to true during Kinesis Client Library (KCL) initialization.
        KinesisClientLibConfiguration kclConfig = new KinesisClientLibConfiguration(applicationName,
                streamARN, ddbStreamCredentials, workerID)
                .withMaxRecords(maxRecords)
                .withIdleTimeBetweenReadsInMillis(idleTimeBetweenReadsInMillis)
                .withFailoverTimeMillis(leaseFailOverTimeInMillis)
                .withInitialPositionInStream(InitialPositionInStream.TRIM_HORIZON);
        return kclConfig;
    }
    // {/fact}

    // {fact rule=kcl-with-call-process-records@v1.0 defects=0}
    public KinesisClientLibConfiguration configureKCLCompliant() {
        // Compliant: sets withCallProcessRecordsEvenForEmptyRecordList to true during Kinesis Client Library (KCL) initialization.
        KinesisClientLibConfiguration kclConfig = new KinesisClientLibConfiguration(applicationName,
                streamARN, ddbStreamCredentials, workerID)
                .withMaxRecords(maxRecords)
                .withCallProcessRecordsEvenForEmptyRecordList(true)
                .withIdleTimeBetweenReadsInMillis(idleTimeBetweenReadsInMillis)
                .withFailoverTimeMillis(leaseFailOverTimeInMillis)
                .withInitialPositionInStream(InitialPositionInStream.TRIM_HORIZON);
        return kclConfig;
    }
    // {/fact}

    private String applicationName;
    private String streamARN;
    private AWSCredentialsProvider ddbStreamCredentials;
    private String workerID;
    private int maxRecords;
    private long idleTimeBetweenReadsInMillis;
    private long leaseFailOverTimeInMillis;
}
