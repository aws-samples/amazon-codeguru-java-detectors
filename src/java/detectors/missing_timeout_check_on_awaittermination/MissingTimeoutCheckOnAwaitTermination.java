package rules.missing_timeout_check_on_awaittermination;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.extern.log4j.Log4j;

@Log4j
class MissingTimeoutCheckOnAwaitTermination {

    // {fact rule=missing-timeout-check-on-awaittermination@v1.0 defects=1}
    public void shutdownNonCompliant(ExecutorService executor) {
        executor.shutdown();
        try {
            // Noncompliant: awaitTermination might silently time out before all threads finish their execution.
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.warn("Failed to wait for all tasks to finish", e);
        }
    }
    // {/fact}

    // {fact rule=missing-timeout-check-on-awaittermination@v1.0 defects=0}
    public void shutdownCompliant(ExecutorService executor) {
        executor.shutdown();
        try {
            // Compliant: code handles the case when awaitTermination times out.
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                log.warn("Failed to terminate executor");
            }
        } catch (InterruptedException e) {
            log.warn("Failed to wait for all tasks to finish", e);
        }
    }
    // {/fact}
}
