/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.string_equality_check;

public class StringEqualityCheck {

    // {fact rule=string-equality-check@v1.0 defects=1}
    public void stringEqualityCheckNoncompliant(String string1, String string2) {
        // Noncompliant: the == operator doesn't compare the contents of the strings.
        if(string1 == string2) {
            System.out.println("The strings are equal.");
        }
    }
    // {/fact}

    // {fact rule=string-equality-check@v1.0 defects=0}
    public void stringEqualityCheckCompliant(String string1, String string2) {
        // Compliant: the equals() method compares the contents of the strings.
        if(string1.equals(string2)) {
            System.out.println("The strings are equal.");
        }
    }
    // {/fact}
}
