/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.out_of_sync_input_and_output;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class OutOfSyncInputAndOutput {
    // {fact rule=out-of-sync-input-and-output@v1.0 defects=1}
    void outOfSyncNoncompliant(String deploymentId) {
        String deploymentItem = null;
        try {
            // Noncompliant: the output value would not be updated if the producing method
            // throws an exception, resulting the input and output values becoming out of sync.
            deploymentItem = loadDeployment(deploymentId);
        } catch (final Exception e) {
            log.warn("Exception: ", e);
        }
        doSomething(deploymentId, deploymentItem);
    }
    // {/fact}

    // {fact rule=out-of-sync-input-and-output@v1.0 defects=0}
    void outOfSyncCompliant(String deploymentId) {
        String deploymentItem = null;
        try {
            deploymentItem = loadDeployment(deploymentId);
        } catch (final Exception e) {
            log.warn("Exception: ", e);
            deploymentId=null;
        }
        // Compliant: the input value is reset, resulting the input and output values to stay in sync.
        doSomething(deploymentId, deploymentItem);
    }
    // {/fact}

    abstract String loadDeployment(String deploymentId);

    abstract void doSomething(String a, String b);
}
