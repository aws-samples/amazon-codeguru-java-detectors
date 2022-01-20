/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.cipher_update_aad;

import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;

public class CipherUpdateAAD {

    // {fact rule=cipher-update-aad@v1.0 defects=1}
    public byte[] createCipherNoncompliant(byte[] key, int tagLength, byte[] ivSource, byte[] salt, byte[] data)
            throws GeneralSecurityException {

        SecretKey secretKeySpec = new SecretKeySpec(key, "AES");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(tagLength, ivSource);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        // Noncompliant: Additional authenticated data (AAD) is not used.
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, gcmParameterSpec);
        return cipher.doFinal(data);
    }
    // {/fact}

    // {fact rule=cipher-update-aad@v1.0 defects=0}
    public byte[] createCipherCompliant(byte[] key, int tagLength, byte[] ivSource, byte[] salt, byte[] data)
            throws GeneralSecurityException {

        SecretKey secretKeySpec = new SecretKeySpec(key, "AES");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(tagLength, ivSource);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, gcmParameterSpec);
        // Compliant: Additional authenticated data (AAD) is used.
        cipher.updateAAD(salt);
        return cipher.doFinal(data);
    }
    // {/fact}
}
