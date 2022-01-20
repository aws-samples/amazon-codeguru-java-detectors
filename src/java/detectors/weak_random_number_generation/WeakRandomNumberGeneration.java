/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.weak_random_number_generation;

import java.security.SecureRandom;

public class WeakRandomNumberGeneration {
    // {fact rule=weak-random-number-generation@v1.0 defects=1}
    static void secureRandomSpecificAlgorithmNoncompliant() throws Exception {
        final String ALGORITHM_NAME = "DES";
        // Noncompliant: one specific algorithm is requested.
        SecureRandom generator = SecureRandom.getInstance(ALGORITHM_NAME);
        System.out.println(generator.nextInt());
    }
    // {/fact}

    // {fact rule=weak-random-number-generation@v1.0 defects=0}
    static void secureRandomDefaultCompliant() throws Exception {
        // Compliant: no specific algorithm is requested.
        SecureRandom generator = new SecureRandom();
        System.out.println(generator.nextInt());
    }
    // {/fact}
}
