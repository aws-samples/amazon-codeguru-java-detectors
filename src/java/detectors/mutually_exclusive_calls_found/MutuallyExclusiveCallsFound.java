/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.mutually_exclusive_calls_found;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.*;

public class MutuallyExclusiveCallsFound {

    // {fact rule=mutually-exclusive-calls-found@v1.0 defects=1}
    public void ec2RunInstancesNoncompliant(String templateName, String templateID, String zone) {
        AmazonEC2 ec2Client = AmazonEC2ClientBuilder.standard().withRegion(Regions.US_WEST_2).build();
        // Noncompliant: uses mutually exclusive withLaunchTemplateId and withLaunchTemplateName together.
        RunInstancesResult runInstancesResult = ec2Client.runInstances(
                new RunInstancesRequest()
                        .withPlacement(new Placement().withAvailabilityZone(zone))
                        .withInstanceType(InstanceType.T2Micro)
                        .withInstanceInitiatedShutdownBehavior("terminate")
                        .withMinCount(1)
                        .withMaxCount(2)
                        .withLaunchTemplate(
                                new LaunchTemplateSpecification()
                                        .withLaunchTemplateName(templateName)
                                        .withLaunchTemplateId(templateID)
                        )
        );
    }
    // {/fact}

    // {fact rule=mutually-exclusive-calls-found@v1.0 defects=0}
    public void ec2RunInstancesCompliant(String templateName, String templateID, String zone) {
        AmazonEC2 ec2Client = AmazonEC2ClientBuilder.standard().withRegion(Regions.US_WEST_2).build();
        // Compliant: uses only withLaunchTemplateId.
        RunInstancesResult runInstancesResult = ec2Client.runInstances(
                new RunInstancesRequest()
                        .withPlacement(new Placement().withAvailabilityZone(zone))
                        .withInstanceType(InstanceType.T2Micro)
                        .withInstanceInitiatedShutdownBehavior("terminate")
                        .withMinCount(1)
                        .withMaxCount(2)
                        .withLaunchTemplate(
                                new LaunchTemplateSpecification()
                                        .withLaunchTemplateId(templateID)
                        )
        );
    }
    // {/fact}
}
