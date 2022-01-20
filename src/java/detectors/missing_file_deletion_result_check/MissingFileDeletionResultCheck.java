/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.missing_file_deletion_result_check;

import java.io.File;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MissingFileDeletionResultCheck {

    // {fact rule=missing-file-deletion-result-check@v1.0 defects=1}
    public void fileDeletionNoncompliant(File file) {
        if (file.exists()) {
            log.info("Deleting file: " + file.getName());
            // Noncompliant: result of file deletion not checked.
            file.delete();
        }
    }
    // {/fact}

    // {fact rule=missing-file-deletion-result-check@v1.0 defects=0}
    public void fileDeletionCompliant(File file) {
        if (file.exists()) {
            log.info("Deleting file: " + file.getName());
            // Compliant: result of file deletion is checked.
            if (!file.delete()) {
                throw new RuntimeException("Failed to delete the file!");
            }
        }
    }
    // {/fact}
}
