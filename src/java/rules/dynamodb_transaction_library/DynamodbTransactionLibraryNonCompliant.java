/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package rules.dynamodb_transaction_library;

import stubs.TransactionManager;

// {fact rule=dynamodb-transaction-library@v1.0 defects=1}
public class DynamodbTransactionLibraryNonCompliant {
    public void createTransactionNonCompliant() throws Exception {
        // Noncompliant: uses AWS Lab Transactions Library over DynamoDB native transactional APIs.
        TransactionManager.verifyOrCreateTransactionTable("client", "Transactions", 1, 1, null);
    }
}
// {/fact}
