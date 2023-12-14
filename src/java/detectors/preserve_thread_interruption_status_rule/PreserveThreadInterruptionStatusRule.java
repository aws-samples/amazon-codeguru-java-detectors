/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.preserve_thread_interruption_status_rule;

import java.lang.InterruptedException;
import java.lang.RuntimeException;
import java.lang.Thread;

public class PreserveThreadInterruptionStatusRule {

    // {fact rule=preserve-thread-interruption-status-rule@v1.0 defects=1}
    public void preserveThreadInterruptionStatusRuleNoncompliant(int numTimes) throws RuntimeException {
        try {
            for (int i=0; i < numTimes; i++ ) {
                Thread.sleep(1000L);
            }
        // Noncompliant: InterruptedException wrapped and rethrown using RuntimeException but without resetting interrupt status.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    // {/fact}

    // {fact rule=preserve-thread-interruption-status-rule@v1.0 defects=0}
    public void preserveThreadInterruptionStatusRuleCompliant(int numTimes) throws RuntimeException {
        try {
            for (int i=0; i < numTimes; i++ ) {
                Thread.sleep(1000L);
            }
        } catch (InterruptedException e) {
            // Compliant: InterruptedException wrapped and rethrown using RuntimeException and resetting interrupt status.
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
    // {/fact}
}
