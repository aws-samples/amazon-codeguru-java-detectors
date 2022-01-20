/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.insecure_temporary_file;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InsecureTemporaryFile {
    // {fact rule=insecure-temporary-file@v1.0 defects=1}
    public void withDeleteNoncompliant() throws Exception {
        File tmp = File.createTempFile("myprefix", "mysuffix");
        tmp.delete();
        // Noncompliant: uses a temporary file path to create a temporary directory.
        tmp.mkdir();
    }
    // {/fact}

    // {fact rule=insecure-temporary-file@v1.0 defects=0}
    public void usingCreateTempDirectoryCompliant() throws Exception {
        Path where = Paths.get("/tmp");
        // Compliant: uses the correct mechanism to create a temporary directory.
        Path path = Files.createTempDirectory(where, "myprefix");
        File tmpDir = path.toFile();
    }
    // {/fact}
}
