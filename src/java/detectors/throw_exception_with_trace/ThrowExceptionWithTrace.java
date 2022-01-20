/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.throw_exception_with_trace;

public class ThrowExceptionWithTrace {

    // {fact rule=throw-exception-with-trace@v1.0 defects=1}
    public void throwExceptionWithTraceNoncompliant() throws Exception {
        try {
            doSomething();
        } catch (Exception e) {
            // Noncompliant: the new exception only has the message from the original exception.
            throw new Exception("Error: " + e.getMessage());
        }
    }
    // {/fact}

    // {fact rule=throw-exception-with-trace@v1.0 defects=0}
    public void throwExceptionWithTraceCompliant() throws Exception {
        try {
            doSomething();
        } catch (Exception e) {
            // Compliant: the new exception contains the original exception.
            throw new Exception("Error: " + e.getMessage(), e);
        }
    }
    // {/fact}

    private void doSomething() {
        // Placeholder for code.
    }
}
