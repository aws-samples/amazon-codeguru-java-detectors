package rules.missing_timneout_check_on_awaittermination;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.extern.log4j.Log4j;

@Log4j
class MissingTimeoutCheckOnAwaitTermination {

    public void shutdownNonCompliant(ExecutorService executor) {
        executor.shutdown();
        try {
            // Noncompliant: awaitTermination might silently time out before all threads finish their execution.
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.warn("Failed to wait for all tasks to finish", e);
        }
    }

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
}