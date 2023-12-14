/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.device_permission_usage;

import android.media.MediaPlayer;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.media.MediaRecorder;
import java.io.File;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import java.lang.Math;


public class DevicePermissionUsage {

    // {fact rule=device-permission-usage@v1.0 defects=1}
    public void devicePermissionUsageNoncompliant() {
        File song = new File(getString(R.string.song_path));
        MediaPlayer mediaPlayer = MediaPlayer.create(this, Uri.fromFile(song));
        mediaPlayer.setLooping(true);
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(final SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(final SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
                if (fromUser) {
                    final int ms = Math.max(progress, 0);
                    mediaPlayer.seekTo(ms);
                    setPosition(ms);
                }
            }
        });
        // Noncompliant: The call to method android.media.MediaPlayer.start triggers an OS-level permission (media) request directly.
        mediaPlayer.start();
    }
    // {/fact}

    // {fact rule=device-permission-usage@v1.0 defects=0}
    public void devicePermissionUsageCompliant() {
        File song = new File(getString(R.string.song_path));
        // Compliant: The call to method android.media.MediaPlayer.start does not trigger an OS-level permission (media) request directly.
        MediaPlayer mediaPlayer = MediaPlayer.create(this, Uri.fromFile(song));
        mediaPlayer.setLooping(true);
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        seekBar.setMax(mediaPlayer.getDuration());
    }
    // {/fact}
}
