/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.device_permission_usage;

import android.media.MediaRecorder;
import java.lang.IllegalStateException;
import java.io.IOException;


public class DevicePermissionUsage {

    // {fact rule=device-permission-usage@v1.0 defects=1}
    public void devicePermissionUsageNoncompliant() {
        String file = "";
        MediaRecorder recorder = new MediaRecorder();
        // Noncompliant: The call to method android.media.MediaRecorder.start triggers an OS-level permission (media) request directly.
        recorder.start();
        recorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        try {
            recorder.prepare();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            return;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // {/fact}

    // {fact rule=device-permission-usage@v1.0 defects=0}
    public void devicePermissionUsageCompliant() {
        String file = "";
        // Compliant: The call to method android.media.MediaRecorder.start does not trigger a permission request through the MediaRecorder class.
        MediaRecorder recorder = new MediaRecorder();
    }
    // {/fact}
}
