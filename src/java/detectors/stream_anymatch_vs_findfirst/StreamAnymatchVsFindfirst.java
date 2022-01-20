/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.stream_anymatch_vs_findfirst;

import java.util.Collection;
import java.util.Objects;

public class StreamAnymatchVsFindfirst {
    Collection<String> col;

    // {fact rule=stream-anymatch-vs-findfirst@v1.0 defects=1}
    boolean streamCollectionNoncompliant(final Collection<String> col) {
        // Noncompliant: uses a chain of "filter", "findFirst" and "isPresent" stream methods over anyMatch.
        return col.stream()
                .filter(Objects::isNull)
                .findFirst()
                .isPresent();
    }
    // {/fact}

    // {fact rule=stream-anymatch-vs-findfirst@v1.0 defects=0}
    boolean streamCollectionCompliant(final Collection<String> col) {
        // Compliant: uses anyMatch stream method over the others which is more readable and convenient.
        return col.stream().anyMatch(Objects::isNull);
    }
    // {/fact}
}
