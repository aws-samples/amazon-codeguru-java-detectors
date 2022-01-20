/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.untrusted_ami_images;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.Filter;
import java.util.Arrays;

public class UntrustedAmiImages {

    // {fact rule=untrusted-ami-images@v1.0 defects=1}
    public void describeImagesNoncompliant(AmazonEC2 client) {
        final String imageName = "sample_image_name";
        final Filter filter = new Filter("name").withValues(imageName);
        // Noncompliant: images are filtered using name only.
        DescribeImagesResult result =
                client.describeImages(new DescribeImagesRequest().withFilters(filter));
    }
    // {/fact}

    // {fact rule=untrusted-ami-images@v1.0 defects=0}
    public void describeImagesCompliant(AmazonEC2 client) {
        final String imageName = "sample_image_name";
        final String imageOwner = "sample_image_owner";
        final Filter nameFilter = new Filter("name").withValues(imageName);
        final Filter ownerFilter = new Filter("owner-alias").withValues(imageOwner);
        // Compliant: images are filtered using name and owner.
        DescribeImagesResult result =
                client.describeImages(new DescribeImagesRequest().withFilters(Arrays.asList(nameFilter, ownerFilter)));
    }
    // {/fact}
}
