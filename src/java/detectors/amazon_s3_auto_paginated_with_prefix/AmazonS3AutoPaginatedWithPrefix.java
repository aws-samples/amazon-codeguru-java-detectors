package detectors.amazon_s3_auto_paginated_with_prefix;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.iterable.S3Objects;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class AmazonS3AutoPaginatedWithPrefix {

    // {fact rule=amazon-s3-auto-paginated-with-prefix@v1.0 defects=1}
    public void s3GetObjectsNoncompliant(AmazonS3 amazonS3Client, String bucketName) {
        String continuationToken = null;
        ListObjectsV2Request listObjectsV2Request = new ListObjectsV2Request().withBucketName(bucketName);
        ListObjectsV2Result listObjectsV2Result;
        do {
            // Noncompliant: uses manual pagination.
            listObjectsV2Result = amazonS3Client.listObjectsV2(listObjectsV2Request);
            for (S3ObjectSummary objectSummary : listObjectsV2Result.getObjectSummaries()) {
                System.out.printf(" - %s (size: %d)\n", objectSummary.getKey(), objectSummary.getSize());
            }
            continuationToken = listObjectsV2Result.getNextContinuationToken();
            listObjectsV2Request.setContinuationToken(continuationToken);
        } while (continuationToken != null);
    }
    // {/fact}

    // {fact rule=amazon-s3-auto-paginated-with-prefix@v1.0 defects=0}
    public void s3GetObjectsCompliant(AmazonS3 amazonS3Client, String bucketName, String prefix) {
        // Compliant: uses S3Objects.withPrefix() for pagination.
        for (S3ObjectSummary objectSummary : S3Objects.withPrefix(amazonS3Client, bucketName, prefix)) {
            System.out.printf(" - %s (size: %d)\n", objectSummary.getKey(), objectSummary.getSize());
        }
    }
    // {/fact}
}
