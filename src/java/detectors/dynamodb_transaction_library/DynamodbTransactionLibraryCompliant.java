/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.dynamodb_transaction_library;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.Put;
import com.amazonaws.services.dynamodbv2.model.TransactWriteItem;
import com.amazonaws.services.dynamodbv2.model.TransactWriteItemsRequest;
import java.util.Collections;
import java.util.Map;


// {fact rule=dynamodb-transaction-library@v1.0 defects=0}
public class DynamodbTransactionLibraryCompliant {
    public void DynamoDBTransactionLibraryCompliant(String name) throws InterruptedException {
        final Map<String, AttributeValue> item = createItem();
        final Put put = new Put()
                .withTableName(name)
                .withItem(item);
        // Compliant: uses DynamoDB native transactional APIs over AWS Lab Transactions Library.
        final TransactWriteItem transactWriteItem = new TransactWriteItem().withPut(put);
        final TransactWriteItemsRequest request = new TransactWriteItemsRequest()
                .withTransactItems(transactWriteItem)
                .withReturnConsumedCapacity("TOTAL");
    }

    private Map<String, AttributeValue> createItem() {
        Map<String, AttributeValue> item = Collections.emptyMap();
        return item;
    }
}
// {/fact}
