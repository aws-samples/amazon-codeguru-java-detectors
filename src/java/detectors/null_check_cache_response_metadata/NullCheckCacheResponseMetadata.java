/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.null_check_cache_response_metadata;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.S3ResponseMetadata;
import com.amazonaws.AmazonWebServiceRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NullCheckCacheResponseMetadata {

    // {fact rule=null-check-cache-response-metadata@v1.0 defects=1}
    public void getCachedResponseMetadataNoncompliant(AmazonWebServiceRequest request, AmazonS3 amazonS3Client) {
        S3ResponseMetadata responseMetadata = amazonS3Client.getCachedResponseMetadata(request);
        // Noncompliant: uses the result of getCachedResponseMetadata without null-checking it.
        log.info("Request ID: " + responseMetadata.getRequestId());
    }
    // {/fact}

    // {fact rule=null-check-cache-response-metadata@v1.0 defects=0}
    public void getCachedResponseMetadataCompliant(AmazonWebServiceRequest request, AmazonS3 amazonS3Client) {
        S3ResponseMetadata responseMetadata = amazonS3Client.getCachedResponseMetadata(request);
        // Compliant: ensures that the result of getCachedResponseMetadata is not null before using it.
        if (responseMetadata != null) {
            log.info("Request ID: " + responseMetadata.getRequestId());
        } else {
            log.info("Could not obtain cached metadata.");
        }
    }
    // {/fact}
}
