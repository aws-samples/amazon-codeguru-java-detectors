/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package rules.infinite_loop;

import stubs.ResultClass;
import java.util.concurrent.TimeoutException;


public class InfiniteLoop {

    // {fact rule=infinte-loop@v1.0 defects=1}
    public String loopControlNonCompliant() {
        ResultClass resultClass = new ResultClass();
        // Noncompliant: does not have loop control flow to prevent an infinite loop.
        for ( ; ; ) {
            try {
                String result = resultClass.getResult();
                return result;
            } catch (TimeoutException e) {
                resultClass.retry();
            }
        }
    }
    // {/fact}

    // {fact rule=infinte-loop@v1.0 defects=0}
    public String loopControlCompliant() {
        ResultClass resultClass = new ResultClass();
        // Compliant: has loop control flow to prevent an infinite loop.
        for (int i = 0; i < 10; ++i) {
            try {
                String result = resultClass.getResult();
                return result;
            } catch (TimeoutException e) {
                resultClass.retry();
            }
        }
        return null;
    }
    // {/fact}
}
