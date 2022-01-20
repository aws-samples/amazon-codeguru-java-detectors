/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.aws_dynamodb_getitem_null_check;

import java.util.Map;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AwsDynamodbGetitemNullCheck {

    // {fact rule=aws-dynamodb-getitem-null-check@v1.0 defects=1}
    public void dynamoDBGetItemNoncompliant(Map<String, AttributeValue> key, String tableName) {
        AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.standard().build();
        GetItemRequest request = new GetItemRequest()
                .withTableName(tableName)
                .withKey(key);
        try {
            GetItemResult result = dynamoDBClient.getItem(request);
            // Noncompliant: result is not null-checked.
            System.out.println(result.getItem().get("key"));
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        }
    }
    // {/fact}

    // {fact rule=aws-dynamodb-getitem-null-check@v1.0 defects=0}
    public void dynamoDBGetItemCompliant(Map<String, AttributeValue> key, String tableName) {
        AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.standard().build();
        GetItemRequest request = new GetItemRequest()
                .withTableName(tableName)
                .withKey(key);
        try {
            GetItemResult result = dynamoDBClient.getItem(request);
            // Compliant: result is null-checked.
            if (result.getItem() != null) {
                System.out.println(result.getItem().get("key"));
            }
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        }
    }
    // {/fact}
}
