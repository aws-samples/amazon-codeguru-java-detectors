/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.amazon_s3_transfer_manager_shutdown;

import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;

public class AmazonS3TransferManagerShutdown {

    // {fact rule=amazon-s3-transfer-manager-shutdown@v1.0 defects=1}
    public void transferManagerNoncompliant(PutObjectRequest putRequest) {
        // Noncompliant: transferManager is not shutdown.
        TransferManager transferManager = TransferManagerBuilder.defaultTransferManager();
        try {
            final Upload upload = transferManager.upload(putRequest);
            upload.waitForCompletion();
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    // {/fact}

    // {fact rule=amazon-s3-transfer-manager-shutdown@v1.0 defects=0}
    public void transferManagerCompliant(PutObjectRequest putRequest) {
        TransferManager transferManager = TransferManagerBuilder.defaultTransferManager();
        try {
            final Upload upload = transferManager.upload(putRequest);
            upload.waitForCompletion();
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally
        {
            // Compliant: transferManager is shutdown.
            transferManager.shutdownNow();
        }
    }
    // {/fact}
}
