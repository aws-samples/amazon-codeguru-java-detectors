package rules.sudden_service_shutdown;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.SECONDS;

class SuddenServiceShutdown {

    // {fact rule=sudden-service-shutdown@v1.0 defects=1}
    void shutdownNonCompliant(ExecutorService executorService) throws InterruptedException {
        if (executorService != null) {
            // Noncompliant: shutdownNow is called which suddenly shuts down executorService.
            executorService.shutdownNow();
        }
    }
    // {/fact}

    // {fact rule=sudden-service-shutdown@v1.0 defects=0}
    void shutdownCompliant(ExecutorService executorService) throws InterruptedException {
        if (executorService != null) {
            // Compliant: attempts graceful shutdown before doing so forcefully.
            executorService.shutdown();
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                throw new IllegalStateException("Timed out while waiting for executing threads to terminate");
            }
            executorService.shutdownNow();
        }
    }
    // {/fact}
}
