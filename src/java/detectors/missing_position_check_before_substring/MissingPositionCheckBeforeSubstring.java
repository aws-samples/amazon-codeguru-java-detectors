/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.missing_position_check_before_substring;

public class MissingPositionCheckBeforeSubstring {

    // {fact rule=missing-position-check-before-substring@v1.0 defects=1}
    public void checkSubStringNoncompliant(String sampleString) {
        final String sampleSubstring = "sample";
        final int index = sampleString.lastIndexOf(sampleSubstring);
        // Noncompliant: it is not checked if substring is part of the string.
        final String subString = sampleString.substring(0, index);
    }
    // {/fact}

    // {fact rule=missing-position-check-before-substring@v1.0 defects=0}
    public void checkSubStringCompliant(String sampleString) {
        final String sampleSubstring = "sample";
        final int index = sampleString.lastIndexOf(sampleSubstring);
        // Compliant: checked if substring is part of the string.
        final String subString = index > 0 ? sampleString.substring(0, index) : sampleString;
    }
    // {/fact}
}
