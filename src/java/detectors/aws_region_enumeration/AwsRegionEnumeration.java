/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.aws_region_enumeration;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AwsRegionEnumeration {

    // {fact rule=aws-region-enumeration@v1.0 defects=1}
    public void createS3ClientNoncompliant() {
        // Noncompliant: a string is used to specify AWS region.
        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion("us-west-2")
                .build();
    }
    // {/fact}

    // {fact rule=aws-region-enumeration@v1.0 defects=0}
    public void createS3ClientCompliant() {
        // Compliant: Regions enum is used to specify AWS region.
        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_WEST_2)
                .build();
    }
    // {/fact}
}
