/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.simple_date_format_time_zone;

import java.util.Date;
import java.text.SimpleDateFormat;

public class SimpleDateFormatTimeZone {
    // {fact rule=simple-date-format-time-zone@v1.0 defects=1}
    void setTimezoneNoncompliant() {
        // Noncompliant: does not set the timezone while using a 'SimpleDateFormat' object.
        doSomething(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }
    // {/fact}

    // {fact rule=simple-date-format-time-zone@v1.0 defects=0}
    void setTimezoneCompliant() {
        // Compliant: sets the timezone while using a 'SimpleDateFormat' object.
        doSomething(new SimpleDateFormat("yyyy-MM-dd'Z'").format(new Date()));
    }
    // {/fact}

    void doSomething(String date) {
        // do-something
    }
}
