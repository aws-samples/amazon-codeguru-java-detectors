/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package rules.missing_specifically_thrown_exception_handling;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MissingSpecificallyThrownExceptionHandling {

    // {fact rule=missing-specifically-thrown-exception-handling@v1.0 defects=1}
    public void exceptionHandlingNonCompliant()  {
        // Noncompliant: catch is handling generic exception and not SomeException.
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
        // Compliant: catch is handling SomeException.
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

    }
}
