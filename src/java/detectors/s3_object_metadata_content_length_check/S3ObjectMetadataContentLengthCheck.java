/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.s3_object_metadata_content_length_check;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.util.IOUtils;

public class S3ObjectMetadataContentLengthCheck {

    // {fact rule=s3-object-metadata-content-length-check@v1.0 defects=1}
    public void s3PutOjectFromStreamNoncompliant(AmazonS3 s3Client, File inputFile) throws FileNotFoundException {
        String s3Bucket = "sample-bucket";
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(inputFile);
            ObjectMetadata metadata = new ObjectMetadata();
            // Noncompliant: puts object from stream without specifying the content length of the stream.
            s3Client.putObject(s3Bucket, inputFile.getName(), inputStream, metadata);
        } finally {
            IOUtils.closeQuietly(inputStream, null);
        }
    }
    // {/fact}

    // {fact rule=s3-object-metadata-content-length-check@v1.0 defects=0}
    public void s3PutOjectFromStreamCompliant(AmazonS3 s3Client, File inputFile) throws FileNotFoundException {
        String s3Bucket = "sample-bucket";
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(inputFile);
            ObjectMetadata metadata = new ObjectMetadata();
            // Compliant: specifies the content length of the stream.
            metadata.setContentLength(inputFile.length());
            s3Client.putObject(s3Bucket, inputFile.getName(), inputStream, metadata);
        } finally {
            IOUtils.closeQuietly(inputStream, null);
        }
    }
    // {/fact}

}
