/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.code_clone;

import lombok.extern.slf4j.Slf4j;
import stubs.*;

import java.nio.file.Path;
import java.util.Map;

@Slf4j
public class CodeCloneNoncompliant {
    // {fact rule=code-clone@v1.0 defects=1}
    private boolean containsActivityVideoWithGivenAngleAndArn(final String kinesisVideoStreamArn,
                                                              final String viewAngle,
                                                              ActivityJobItem activityJob){
        VideoAngle videoAngle = VideoAngle.valueOf(viewAngle);
        Map<VideoAngle, ActivityJobItem.ActivityVideo> activityVideos = activityJob.getActivityVideos();
        if ((activityVideos != null) && (activityVideos.containsKey(videoAngle))) {
            ActivityJobItem.ActivityVideo activityVideo = activityVideos.get(videoAngle);
            if ((activityVideo != null) && (activityVideo.getVideoStreamArn().equals(kinesisVideoStreamArn))) {
                return true;
            }
        }
        return false;
    }

    private boolean doesKvsExistForJobNoncompliant(final String kinesisVideoStreamArn,
                                       final String viewAngle,
                                       final String activityJobArn,
                                       final WorkflowType workflowType) throws Exception {
        try {
            ActivityJobItem activityJob = null;
            ActivityType activityType = workflowType.getActivityType();
            // Noncompliant: uses similar code fragments in the same file.
            if (activityType == ActivityType.TRAINING) {
                activityJob = trainingJobDao.loadTrainingJob(activityJobArn);
            } else if (activityType == ActivityType.EVALUATION) {
                activityJob = evaluationJobDao.loadEvaluationJob(activityJobArn);
            } else if (activityType == ActivityType.LEADERBOARD_SUBMISSION) {
                activityJob = leaderboardEvaluationDao.loadLeaderboardEvaluationJob(activityJobArn);
            }
            if (activityJob == null) {
                throw new Exception("Unexpected workflow activity job.");
            }

            return containsActivityVideoWithGivenAngleAndArn(kinesisVideoStreamArn, viewAngle, activityJob);
        } catch (Exception ex) {
            log.error("Unable to get KVS data from DynamoDB.", ex);
            throw ex;
        }
    }

    private void updateVideoInfoInDynamoDBNoncompliant(final Path videoFilePath,
                                           final VideoAngle viewAngle,
                                           final String s3BucketName,
                                           final String activityJobArn,
                                           final WorkflowType workflowType) {
        String videoFileLocation = null;
        if ((videoFilePath != null) && (s3BucketName != null)) {
            String videoFileName = videoFilePath.toFile().getName();
            videoFileLocation = "s3://" + s3BucketName + "/" + S3_OBJECT_KEY_PREFIX + videoFileName;
        }
        ActivityJobItem activityJob = null;
        ActivityType activityType = (workflowType != null) ? workflowType.getActivityType() : null;
        // Noncompliant: uses similar code fragments in the same file.
        if (activityType == ActivityType.TRAINING) {
            activityJob = trainingJobDao.loadTrainingJob(activityJobArn);
        } else if (activityType == ActivityType.EVALUATION) {
            activityJob = evaluationJobDao.loadEvaluationJob(activityJobArn);
        } else if (activityType == ActivityType.LEADERBOARD_SUBMISSION) {
            activityJob = leaderboardEvaluationDao.loadLeaderboardEvaluationJob(activityJobArn);
        }
        if (activityJob == null) {
            return;
        }

        updateActivityJobItem(activityJob, viewAngle, videoFileLocation, activityType);
    }
    // {/fact}

    String S3_OBJECT_KEY_PREFIX="Object_Key_Prefix";
    private void updateActivityJobItem(ActivityJobItem activityJob, VideoAngle viewAngle, String videoFileLocation, ActivityType activityType) {
    }
}
