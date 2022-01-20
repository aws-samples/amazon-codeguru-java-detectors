package detectors.sync_metric_publish;

import com.amazonaws.services.cloudwatch.model.MetricDatum;
import com.amazonaws.services.cloudwatch.model.StandardUnit;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;

// {fact rule=sync-metric-publish@v1.0 defects=0}
public class SyncMetricPublishCompliant implements RequestHandler<ScheduledEvent, Void> {

    public SyncMetricPublishCompliant() {
        // Initialize class members here.
    }

    @Override
    public Void handleRequest(ScheduledEvent scheduledEvent, Context context) {
        LambdaLogger logger = context.getLogger();
        final long startTime = System.currentTimeMillis();
        doSomething(scheduledEvent, context);
        final long endTime = System.currentTimeMillis();
        final long timeElapsed = endTime - startTime;
        MetricDatum metricDatum = new MetricDatum().withMetricName("TIME_ELAPSED")
                .withUnit(StandardUnit.Milliseconds).withValue((double) timeElapsed);
        // Compliant: logs the metrics for further postprocessing outside the Lambda.
        logger.log("Metrics: " + metricDatum);
        return null;
    }

    private void doSomething(ScheduledEvent scheduledEvent, Context context) {
        // Placeholder for code.
    }
}
// {/fact}
