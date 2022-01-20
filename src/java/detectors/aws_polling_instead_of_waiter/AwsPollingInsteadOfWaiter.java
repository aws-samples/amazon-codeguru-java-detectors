/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.aws_polling_instead_of_waiter;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.ec2.model.DescribeInstanceStatusRequest;
import com.amazonaws.services.ec2.model.DescribeInstanceStatusResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.InstanceStatus;
import com.amazonaws.waiters.WaiterParameters;
import com.amazonaws.waiters.WaiterTimedOutException;
import stubs.EC2Client;
import java.util.List;
import java.util.logging.Logger;

public class AwsPollingInsteadOfWaiter {

    private Logger logger;

    // {fact rule=aws-polling-instead-of-waiter@v1.0 defects=1}
    public boolean terminateInstanceNoncompliant(final String instanceId, final EC2Client ec2Client)
            throws InterruptedException {
        long start = System.currentTimeMillis();
        int WAIT_FOR_TRANSITION_INTERVAL = 10;
        int INSTANCE_TERMINATION_TIMEOUT =100;
        while (true) {
            try {
                // Noncompliant: uses custom polling instead of waiters feature.
                DescribeInstanceStatusResult describeInstanceStatusResult = ec2Client.describeInstanceStatus(
                        new DescribeInstanceStatusRequest().withInstanceIds(instanceId).withIncludeAllInstances(true));
                List<InstanceStatus> instanceStatusList = describeInstanceStatusResult.getInstanceStatuses();
                long finish = System.currentTimeMillis();
                long timeElapsed = finish - start;
                if (timeElapsed > INSTANCE_TERMINATION_TIMEOUT) {
                    break;
                }
                if (instanceStatusList.size() < 1) {
                    Thread.sleep(WAIT_FOR_TRANSITION_INTERVAL);
                    continue;
                }
                String currentState = instanceStatusList.get(0).getInstanceState().getName();
                if ("shutting-down".equals(currentState) || "terminated".equals(currentState)) {
                    return true;
                } else {
                    Thread.sleep(WAIT_FOR_TRANSITION_INTERVAL);
                }
            } catch (AmazonServiceException ex) {
                logger.info(ex.getErrorCode());
            }
        }
        return false;
    }
    // {/fact}

    // {fact rule=aws-polling-instead-of-waiter@v1.0 defects=0}
    public void terminateInstanceWaitersCompliant(final String instanceId, final EC2Client ec2Client)
            throws InterruptedException {
        DescribeInstancesRequest requestInstance = new DescribeInstancesRequest().withInstanceIds(instanceId);
        this.logger.info("Waiting to terminate instance: " + instanceId + "\n");
        WaiterParameters waitRequestForInstance = new WaiterParameters().withRequest(requestInstance);
        try {
            // Compliant: uses waiters feature.
            ec2Client.waiters().instanceTerminated().run(waitRequestForInstance);
        } catch (WaiterTimedOutException e) {
            logger.info("WaiterTimedOutException: " + e);
        }
    }
    
}
