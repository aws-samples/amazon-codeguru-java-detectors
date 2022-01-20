/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.thread_safety_class_violations;

import com.amazonaws.annotation.ThreadSafe;
import java.util.HashMap;
import stubs.FileInfo;

// {fact rule=thread-safety-class-violations@v1.0 defects=0}
@ThreadSafe
public class ThreadSafetyClassViolationsCompliant {

    private HashMap<String, FileInfo> fileInfoMap = new HashMap<String, FileInfo>();

    // Compliant: all methods are thread-safe.

    public synchronized void reset() {
        fileInfoMap.clear();
    }

    public synchronized void addFileInfo(String fileName, long fileSize) {
        FileInfo fileInfo = new FileInfo(fileName, fileSize);
        fileInfoMap.put(fileName, fileInfo);
    }

    public synchronized FileInfo getFileInfoSync(String fileName) {
        return fileInfoMap.get(fileName);
    }
}
// {/fact}
