/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.stream_min_max_vs_sort;

import java.util.Collection;
import java.util.Optional;

public class StreamMinMaxVsSort {
    // {fact rule=stream-min-max-vs-sort@v1.0 defects=1}
    Optional<String> streamSortThenFindFirstNoncompliant(final Collection<String> col) {
        // Noncompliant: uses sorted and findFirst over min, max stream methods.
        return col.stream()
                .sorted()
                .findFirst();
    }
    // {/fact}

    // {fact rule=stream-min-max-vs-sort@v1.0 defects=0}
    Optional<String> MinCompliant(final Collection<String> col) {
        // Compliant: uses min, max methods over sorted and findFirst stream methods.
        return col.stream().min(String::compareTo);
    }
    // {/fact}
}
