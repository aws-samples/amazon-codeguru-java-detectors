/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.dynamodb_transaction_library;

// {fact rule=dynamodb-transaction-library@v1.0 defects=1}
import com.amazonaws.services.dynamodbv2.transactions.TransactionManager;

public class DynamodbTransactionLibraryNoncompliant {
    public void createTransactionNoncompliant() throws Exception {
        // Noncompliant: uses AWS Lab Transactions Library over DynamoDB native transactional APIs.
        TransactionManager.verifyOrCreateTransactionTable("client", "Transactions", 1, 1, null);
    }
}
// {/fact}
