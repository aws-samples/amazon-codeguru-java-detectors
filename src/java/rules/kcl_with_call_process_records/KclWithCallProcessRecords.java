/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package rules.kcl_with_call_process_records;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.InitialPositionInStream;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.KinesisClientLibConfiguration;

public class KclWithCallProcessRecords {

    // {fact rule=kcl-with-call-process-records@v1.0 defects=1}
    public KinesisClientLibConfiguration configureKCLNonCompliant() {
        // Noncompliant: avoids setting 'withCallProcessRecordsEvenForEmptyRecordList' to 'TRUE' during Kinesis Client Library (KCL) initialization.
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
        // Compliant: sets 'withCallProcessRecordsEvenForEmptyRecordList' to 'TRUE' during Kinesis Client Library (KCL) initialization.
        KinesisClientLibConfiguration kclConfig = new KinesisClientLibConfiguration(applicationName,
                streamARN, ddbStreamCredentials, workerID)
                .withMaxRecords(maxRecords)
                .withCallProcessRecordsEvenForEmptyRecordList(callProcessRecordsEvenForEmptyRecordList)
                .withIdleTimeBetweenReadsInMillis(idleTimeBetweenReadsInMillis)
                .withFailoverTimeMillis(leaseFailOverTimeInMillis)
                .withInitialPositionInStream(InitialPositionInStream.TRIM_HORIZON);
        return kclConfig;
    }
    // {/fact}

    String applicationName;
    String streamARN;
    AWSCredentialsProvider ddbStreamCredentials;
    String workerID;
    int maxRecords;
    long idleTimeBetweenReadsInMillis;
    long leaseFailOverTimeInMillis;
    boolean callProcessRecordsEvenForEmptyRecordList;
}
