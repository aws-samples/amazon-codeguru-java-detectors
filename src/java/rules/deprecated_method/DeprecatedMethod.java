/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package rules.deprecated_method;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;

import java.util.Map;

public class DeprecatedMethod {

    // {fact rule=deprecated-method@v1.0 defects=1}
    public PutItemRequest createPutItemRequestNonCompliant(Map<String, ExpectedAttributeValue> expectedAttributeValuesByName,
                                                       String tableName,Map<String, AttributeValue> item ) {
        final PutItemRequest request = new PutItemRequest()
                // Noncompliant: expected is a legacy paramater.
                .withExpected(expectedAttributeValuesByName)
                .withTableName(tableName)
                .withItem(item);
        return request;
    }
    // {/fact}

    // {fact rule=deprecated-method@v1.0 defects=0}
    public PutItemRequest createPutItemRequestCompliant(Map<String,ExpectedAttributeValue> expectedAttributeValuesByName,
                                                    String tableName, Map<String, AttributeValue> item) {
        // Compliant: no deprecated methods used.
        final PutItemRequest request = new PutItemRequest()
                .withTableName(tableName)
                .withItem(item);
        return request;
    }
    // {/fact}
}
