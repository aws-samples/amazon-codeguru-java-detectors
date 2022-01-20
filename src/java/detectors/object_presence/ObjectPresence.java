/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.object_presence;

import com.amazonaws.services.s3.AmazonS3;

public class ObjectPresence {

    // {fact rule=object-presence@v1.0 defects=1}
    public boolean checkS3ObjectNoncompliant(AmazonS3 s3Client, String bucketName, String key) {
        // Noncompliant: implements an object existence check from scratch instead of using doesObjectExist.
        try {
            s3Client.getObjectMetadata(bucketName, key);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    // {/fact}

    // {fact rule=object-presence@v1.0 defects=0}
    public boolean checkS3ObjectCompliant(AmazonS3 s3Client, String bucketName, String key) {
        // Compliant: uses doesObjectExist instead of implementing it from scratch.
        try {
            return s3Client.doesObjectExist(bucketName, key);
        } catch (Exception e) {
            return false;
        }
    }
    // {/fact}
}
