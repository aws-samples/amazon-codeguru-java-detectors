/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package stubs;

import java.util.concurrent.TimeoutException;

public class ResultClass {
    public String getResult() throws TimeoutException {
        return "pass";
    }

    public boolean connect() {
        return true;
    }

    public void retry() {
        //do something
    }
}
