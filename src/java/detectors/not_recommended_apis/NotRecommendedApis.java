/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.not_recommended_apis;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;

public class NotRecommendedApis {
    // {fact rule=not-recommended-apis@v1.0 defects=1}
    public void s3MultiPartUploadNoncompliant() {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
        // Noncompliant: uses an API that we don't recommend, and a better alternative exists.
        s3Client.initiateMultipartUpload(new InitiateMultipartUploadRequest(bucketName,key));
    }
    // {/fact}

    // {fact rule=not-recommended-apis@v1.0 defects=0}
    public void shutdownTransferManagerCompliant(final PutObjectRequest putRequest) {
        TransferManager transferManager = null;
        try {
            transferManager = TransferManagerBuilder.defaultTransferManager();
            final Upload upload = transferManager.upload(putRequest);
            upload.waitForCompletion();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // Compliant: uses recommended APIs from TransferManager.
            transferManager.shutdownNow();
        }
    }
    // {/fact}

    String bucketName = null;
    String key= null;
}
