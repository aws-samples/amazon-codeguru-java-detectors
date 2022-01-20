/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.aws_bad_params;

import com.amazonaws.services.elasticmapreduce.model.ActionOnFailure;
import com.amazonaws.services.elasticmapreduce.model.StepConfig;

public class AwsBadParams {

    // {fact rule=aws-bad-params@v1.0 defects=1}
    public void createStepConfigNoncompliant() {
        // Noncompliant: ActionOnFailure.TERMINATE_JOB_FLOW is outdated.
        new StepConfig().withName("sampleStepName").withActionOnFailure(ActionOnFailure.TERMINATE_JOB_FLOW);
    }
    // {/fact}

    // {fact rule=aws-bad-params@v1.0 defects=0}
    public void createStepConfigCompliant() {
        // Compliant: ActionOnFailure.TERMINATE_CLUSTER is used.
        new StepConfig().withName("sampleStepName").withActionOnFailure(ActionOnFailure.TERMINATE_CLUSTER);
    }
    // {/fact}
}
