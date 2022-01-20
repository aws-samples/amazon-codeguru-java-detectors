/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.file_syntax_error;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import java.io.IOException;
import java.io.InputStream;
import com.amazonaws.util.IOUtils;

public class FileSyntaxError {

    // {fact rule=file-syntax-error@v1.0 defects=1}
    public void getObjectContentNoncompliant(AmazonS3 amazonS3Client, String bucketName, String key) throws IOException {
        final S3Object s3object = amazonS3Client.getObject(bucketName, key);
        // Noncompliant: the statement is incomplete and is missing ";" at the end.
        System.out.println(s3object.getObjectMetadata())
        InputStream reportStream = s3object.getObjectContent();
        IOUtils.toString(reportStream);
    }
    // {/fact}

    // {fact rule=file-syntax-error@v1.0 defects=0}
    public void getObjectContentCompliant(AmazonS3 amazonS3Client, String bucketName, String key) throws IOException {
        final S3Object s3object = amazonS3Client.getObject(bucketName, key);
        // Compliant: the statement is complete and ends with a ";".
        System.out.println(s3object.getObjectMetadata());
        InputStream reportStream = s3object.getObjectContent();
        IOUtils.toString(reportStream);
    }
    // {/fact}
}
