package detectors.aws_custom_retries;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.retry.RetryPolicy;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AwsCustomRetries {

    // {fact rule=aws-custom-retries@v1.0 defects=1}
    public InvokeResult invokeLambdaNoncompliant() {
        AWSLambda awsLambdaClient = AWSLambdaClientBuilder.standard().build();
        final InvokeRequest request = new InvokeRequest();
        // Noncompliant: manual retry if a service exception is thrown.
        for(int i=0; i <= 5; i++) {
            try {
                return awsLambdaClient.invoke(request);
            } catch (AmazonServiceException e) {
                log.error("Exception: " + e);
            }
        }
        return null;
    }
    // {/fact}

    // {fact rule=aws-custom-retries@v1.0 defects=0}
    public InvokeResult invokeLambdaCompliant() {
        // Compliant: uses retry policy.
        ClientConfiguration clientConfiguration = new ClientConfiguration()
                .withRetryPolicy(RetryPolicy.builder()
                        .withMaxErrorRetry(5)
                        .build());
        AWSLambda awsLambdaClient = AWSLambdaClientBuilder.standard()
                .withClientConfiguration(clientConfiguration)
                .build();

        final InvokeRequest request = new InvokeRequest();
        return awsLambdaClient.invoke(request);
    }
    // {/fact}
}
