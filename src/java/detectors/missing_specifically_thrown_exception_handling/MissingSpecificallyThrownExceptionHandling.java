/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.missing_specifically_thrown_exception_handling;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MissingSpecificallyThrownExceptionHandling {

    // {fact rule=missing-specifically-thrown-exception-handling@v1.0 defects=1}
    public void exceptionHandlingNoncompliant()  {
        // Noncompliant: catch block handles generic Exception, but not SomeException.
        try {
            doSomething();
            throw new SomeException();
        } catch (IndexOutOfBoundsException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
    // {/fact}

    // {fact rule=missing-specifically-thrown-exception-handling@v1.0 defects=0}
    public void exceptionHandlingCompliant() {
        // Compliant: catch block handles SomeException.
        try {
            doSomething();
            throw new SomeException();
        } catch (IndexOutOfBoundsException e) {
            log.error(e.getMessage());
        } catch (SomeException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());

        }
    }
    // {/fact}

    private void doSomething() {
        // Placeholder for code.
    }

    public class SomeException extends Exception {
        // Placeholder for SomeException definition.
    }
}
