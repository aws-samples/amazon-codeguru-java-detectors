/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.null_pointer_dereference;

import javax.annotation.Nullable;

public class NullPointerDereference {
    // {fact rule=null-pointer-dereference@v1.0 defects=1}
    private Double nullCheckPointerNoncompliant(@Nullable Double digit) {
        // Noncompliant: avoids null checks before dereferencing the pointer.
        return digit + 1.0;
    }
    // {/fact}

    // {fact rule=null-pointer-dereference@v1.0 defects=0}
    private Double nullCheckPointerCompliant(@Nullable Double digit) {
        // Compliant: has null checks before derefencing the pointer.
        if (digit != null) {
            return digit + 1.0;
        }
        return 1.0;
    }
    // {/fact}
}
