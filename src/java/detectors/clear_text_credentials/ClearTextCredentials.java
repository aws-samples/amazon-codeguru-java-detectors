/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.clear_text_credentials;

import lombok.extern.slf4j.Slf4j;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;

@Slf4j
public class ClearTextCredentials {

    // {fact rule=clear-text-credentials@v1.0 defects=1}
    public void logCredentialsNoncompliant() {
        String publicData = "some public data";
        AWSCredentials credentials = new DefaultAWSCredentialsProviderChain().getCredentials();
        // Noncompliant: secret access key is logged.
        log.error("somePublicData: " + publicData + " key: " + credentials.getAWSSecretKey());
    }
    // {/fact}

    // {fact rule=clear-text-credentials@v1.0 defects=0}
    public void logCredentialsCompliant() {
        String publicData = "some public data";
        AWSCredentials credentials = new DefaultAWSCredentialsProviderChain().getCredentials();
        // Compliant: secret access key is not logged.
        log.error("somePublicData: " + publicData);
    }
    // {/fact}
}
