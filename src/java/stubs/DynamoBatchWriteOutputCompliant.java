/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package stubs;
import java.util.List;

public abstract class DynamoBatchWriteOutputCompliant {
    public abstract List<String> mapperCompliant(DynamoDBMapperCollection<String> batch);
}
