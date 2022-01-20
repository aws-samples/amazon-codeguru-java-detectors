/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.loose_file_permissions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class LooseFilePermissions {
    // {fact rule=loose-file-permissions@v1.0 defects=1}
    public void grantOthersPermissionsNoncompliant(final Path p) throws Exception {
        Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("------rwx");
        // Noncompliant: Granting other permissions.
        Files.setPosixFilePermissions(p, permissions);
    }
    // {/fact}

    // {fact rule=loose-file-permissions@v1.0 defects=0}
    public void grantGroupPermissionsCompliant(final Path p) throws Exception {
        Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("---rwx---");
        // Compliant: Granting group permissions.
        Files.setPosixFilePermissions(p, permissions);
    }
    // {/fact}
}
