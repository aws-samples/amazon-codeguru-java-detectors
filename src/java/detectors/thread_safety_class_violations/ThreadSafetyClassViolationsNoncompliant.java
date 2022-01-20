/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.thread_safety_class_violations;
import com.amazonaws.annotation.ThreadSafe;
import stubs.FileInfo;

import java.util.HashMap;

// {fact rule=thread-safety-class-violations@v1.0 defects=1}
@ThreadSafe
public class ThreadSafetyClassViolationsNoncompliant {

    private HashMap<String, FileInfo> fileInfoMap = new HashMap<String, FileInfo>();

    public synchronized void reset() {
        fileInfoMap.clear();
    }

    public synchronized void addFileInfo(String fileName, long fileSize) {
        FileInfo fileInfo = new FileInfo(fileName, fileSize);
        fileInfoMap.put(fileName, fileInfo);
    }

    // Noncompliant: the method doesn't protect the parallel use of map.
    public FileInfo getFileInfo(String fileName) {
        return fileInfoMap.get(fileName);
    }

    public synchronized FileInfo getFileInfoSync(String fileName) {
        return fileInfoMap.get(fileName);
    }
}
// {/fact}
