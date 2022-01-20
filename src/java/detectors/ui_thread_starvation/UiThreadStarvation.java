/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.ui_thread_starvation;

import android.support.annotation.UiThread;
import java.util.concurrent.CountDownLatch;

public class UiThreadStarvation {
    // {fact rule=ui-thread-starvation@v1.0 defects=1}
    @UiThread
    void awaitOnMainNoncompliant(CountDownLatch latch) throws InterruptedException {
        Object main = new Object();
        // Noncompliant: blocks calls in a user interface thread.
        latch.await();
    }
    // {/fact}

    // {fact rule=ui-thread-starvation@v1.0 defects=0}
    @UiThread
    void awaitOnMainCompliant(CountDownLatch latch) throws InterruptedException {
        // Compliant: avoids blocking calls on user interface thread.
        latch.countDown();
    }
    // {/fact}
}
