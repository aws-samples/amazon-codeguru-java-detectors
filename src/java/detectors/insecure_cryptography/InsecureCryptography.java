/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.insecure_cryptography;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class InsecureCryptography {

    // {fact rule=insecure-cryptography@v1.0 defects=1}
    public void keyPairGeneratorNoncompliant() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        // Noncompliant: keysize too small for this algorithm.
        keyPairGenerator.initialize(128);
        keyPairGenerator.genKeyPair();
    }
    // {/fact}

    // {fact rule=insecure-cryptography@v1.0 defects=0}
    public void keyPairGeneratorCompliant() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        // Compliant: keysize sufficient for this algorithm.
        keyPairGenerator.initialize(4096);
        keyPairGenerator.genKeyPair();
    }
    // {/fact}
}
