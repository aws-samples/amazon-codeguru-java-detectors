/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.aws_dynamodb_mapper_batch_output_ignored;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper.FailedBatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import stubs.DynamoBatchWriteOutputCompliant;
import stubs.DynamoDBMapperCollection;
import java.util.List;
import java.util.stream.Collectors;

// {fact rule=aws-dynamodb-mapper-batch-output-ignored@v1.0 defects=0}
@Slf4j
@Repository
public class AwsDynamodbMapperBatchOutputIgnoredCompliant extends DynamoBatchWriteOutputCompliant {
    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
    DynamoDBMapper myDynamoDBMapper = new DynamoDBMapper(client);
    @Override
    public List<String> mapperCompliant(DynamoDBMapperCollection<String> batch) {
        // Compliant: has checks to handle errors returned by batch operation.
        List<FailedBatch> failures = myDynamoDBMapper.batchSave(batch);
        return failures.stream()
                .map(FailedBatch -> String.format("messageId:%s failedReason:%s",
                        FailedBatch.getException(),
                        FailedBatch.getUnprocessedItems())).collect(Collectors.toList());
    }
}
// {/fact}
