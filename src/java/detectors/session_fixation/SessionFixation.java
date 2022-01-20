/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.session_fixation;

import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;

public class SessionFixation {

    // {fact rule=session-fixation@v1.0 defects=1}
    public Object sessionFixationConfigurerNoncompliant(SessionManagementConfigurer.SessionFixationConfigurer sessionFixationConfigurer) {
        // Noncompliant: session fixation protection is disabled.
        return sessionFixationConfigurer.none();
    }
    // {/fact}

    // {fact rule=session-fixation@v1.0 defects=0}
    public Object sessionFixationConfigurerCompliant(SessionManagementConfigurer.SessionFixationConfigurer sessionFixationConfigurer) {
        // Compliant: session fixation protection is enabled.
        return sessionFixationConfigurer.migrateSession();
    }
    // {/fact}
}
