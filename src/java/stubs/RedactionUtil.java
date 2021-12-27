/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package stubs;

public class RedactionUtil {
    public static String redact(String name) {
        return name.replaceAll("[^a-zA-Z]", "");
    }
}
