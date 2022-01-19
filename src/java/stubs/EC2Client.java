/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package stubs;

import com.amazonaws.services.ec2.model.DescribeInstanceStatusRequest;
import com.amazonaws.services.ec2.model.DescribeInstanceStatusResult;
import com.amazonaws.services.ec2.waiters.AmazonEC2Waiters;

public class EC2Client {
    public DescribeInstanceStatusResult describeInstanceStatus(DescribeInstanceStatusRequest withIncludeAllInstances) {
            return null;
    }

    public AmazonEC2Waiters waiters() {
            return null;
    }
}
