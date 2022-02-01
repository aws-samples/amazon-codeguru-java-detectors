package rules.missing_timeout_check_on_latch_await;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class MissingTimeoutCheckOnLatchAwait {

    private volatile Object result = null;
    private CountDownLatch completionLatch = new CountDownLatch(1);

    // {fact rule=missing-timeout-check-on-latch-await@v1.0 defects=1}
    public Object getResultNonCompliant(long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException {

        // Noncompliant: code does not check if await timed out or the latch counted down to zero.
        completionLatch.await(timeout, unit);
        return result;
    }
    // {/fact}

    // {fact rule=missing-timeout-check-on-latch-await@v1.0 defects=0}
    public Object getResultCompliant(long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException {

        // Compliant: code handles the case when await times out.
        if (!completionLatch.await(timeout, unit)) {
            throw new TimeoutException();
        }
        return result;
    }
    // {/fact}
}
