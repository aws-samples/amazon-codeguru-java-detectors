/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.incorrect_null_check_before_setting;

public class IncorrectNullCheckBeforeSetting {

    private String data1, data2;

    // {fact rule=incorrect-null-check-before-setting@v1.0 defects=1}
    public void nullCheckNoncompliant() {
        if (data1 != null) {
            // Noncompliant: overwriting immediately after a non-null check is likely to be a typo.
            data1 = data2;
        }
    }
    // {/fact}

    // {fact rule=incorrect-null-check-before-setting@v1.0 defects=0}
    public void nullCheckCompliant() {
        if (data1 == null) {
            // Compliant: overwriting after a null check is a common pattern.
            data1 = data2;
        }
    }
    // {/fact}
}
