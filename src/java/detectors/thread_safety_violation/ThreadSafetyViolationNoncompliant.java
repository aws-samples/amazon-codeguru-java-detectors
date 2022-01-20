/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.thread_safety_violation;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.locks.ReentrantLock;

// {fact rule=thread-safety-violation@v1.0 defects=1}
class PropertiesNoncompliant {

    private int parameter = 0;

    public int getParameter() {
        return parameter;
    }

    public void setParameter(int number) {
        parameter = number;
    }
}

@ThreadSafe
class ThreadSafetyViolationNoncompliant {
    private final PropertiesNoncompliant properties = new PropertiesNoncompliant();
    private final ReentrantLock lock = new ReentrantLock();

    public void syncReadCallOutsideNoncompliant(int i) {
        // Noncompliant: violates thread safety by indirectly reading without synchronization.
        if (properties.getParameter() > i) {
            lock.lock();
            properties.setParameter(i);
            lock.unlock();
        }
    }
}
// {/fact}

