/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.aws_parse_error_message;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AwsParseErrorMessage {
    // {fact rule=aws-parse-error-message@v1.0 defects=1}
    public void branchingNoncompliant() {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        try {
            s3Client.doesObjectExist("bucketName", "key");
        } catch (AmazonServiceException e) {
            // Noncompliant: checks the status message of the exception instead of the status code.
            if (e.getMessage().contains("bucketName")) {
                log.info("one thing");
            } else {
                log.info("another thinking");
            }
        }
    }
    // {/fact}

    // {fact rule=aws-parse-error-message@v1.0 defects=0}
    public void branchingCompliant() {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        try {
            s3Client.doesObjectExist("bucketName", "key");
        } catch (AmazonServiceException e) {
            // Compliant: checks the status code of the exception instead of the status message.
            if (e.getStatusCode() == 404){
                log.info("The specified bucket does not exist.");
            }
            else {
                log.info("Caught error: " + e.getMessage());
            }
        }
    }
}
