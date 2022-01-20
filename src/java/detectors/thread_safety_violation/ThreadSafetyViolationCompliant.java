/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.thread_safety_violation;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.locks.ReentrantLock;

// {fact rule=thread-safety-violation@v1.0 defects=0}
class PropertiesCompliant {
    private int parameter = 0;

    public int getParameter() {
        return parameter;
    }

    public void setParameter(int number) {
        parameter = number;
    }
}

@ThreadSafe
class ThreadSafetyViolationCompliant {
    private final PropertiesCompliant properties = new PropertiesCompliant();
    private final ReentrantLock lock = new ReentrantLock();

    public void syncReadCallOutsideCompliant(int i) {
        // Compliant: follows thread safety by indirectly reading with synchronization.
        lock.lock();
        if (properties.getParameter() > i) {
            properties.setParameter(i);
        }
        lock.unlock();
    }
}
// {/fact}

