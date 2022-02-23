/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.string_format_arguments;

import lombok.extern.slf4j.Slf4j;
import java.io.File;

@Slf4j
public class StringFormatArguments {
    // {fact rule=string-format-arguments@v1.0 defects=1}
    void formatStringNoncompliant(final File file) {
        final long length = file.length();
        // Noncompliant: avoids using the correct format strings for their argument types.
        final String s = String.format("File length is %s", length);
        log.info(s);
    }
    // {/fact}

    // {fact rule=string-format-arguments@v1.0 defects=0}
    void formatStringCompliant(final File file) {
        final long length = file.length();
        // Compliant: uses the correct format strings for their argument types.
        final String s = String.format("File length is %d", length);
        log.info(s);
    }
    // {/fact}
}
