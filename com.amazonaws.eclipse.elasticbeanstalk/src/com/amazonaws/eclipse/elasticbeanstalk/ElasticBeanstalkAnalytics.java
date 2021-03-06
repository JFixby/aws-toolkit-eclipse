/*
 * Copyright 2015-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.eclipse.elasticbeanstalk;

import com.amazonaws.eclipse.core.AwsToolkitCore;
import com.amazonaws.eclipse.core.mobileanalytics.ToolkitAnalyticsManager;
import com.amazonaws.eclipse.core.mobileanalytics.ToolkitAnalyticsUtils;

public final class ElasticBeanstalkAnalytics {

    /*
     * Deploy Application to Elastic Beanstalk
     */
    private static final String EVENT_TYPE_DEPLOY_APPLICATION = "ElasticBeanstalk-DeployApplication";

    private static final String METRIC_NAME_DEPLOY_TOTAL_TIME_MS = "DeployTotalTimeMs";
    private static final String METRIC_NAME_UPLOAD_S3_BUCKET_TIME_MS = "UploadS3BucketTimeMs";
    private static final String METRIC_NAME_UPLOAD_S3_BUCKET_BYTES_PER_MS = "UploadS3BucketBytesPerMs";
    private static final String METRIC_NAME_APPLICATION_SOURCE_BUNDLE_SIZE = "ApplicationSourceBundleSize";

    private static final ToolkitAnalyticsManager ANALYTICS = AwsToolkitCore.getDefault().getAnalyticsManager();

    /*
     * Analytics for ElasticBeanstalk-DeployApplication
     */

    public static void trackDeployTotalTime(long deployTotalTime) {
        ANALYTICS.publishEvent(ANALYTICS.eventBuilder()
                .setEventType(EVENT_TYPE_DEPLOY_APPLICATION)
                .addMetric(METRIC_NAME_DEPLOY_TOTAL_TIME_MS, (double)deployTotalTime)
                .build());
    }

    public static void trackUploadMetrics(long uploadS3BucketTime, long sourceFileBundleSize) {
        ToolkitAnalyticsUtils.trackSpeedMetrics(ANALYTICS, EVENT_TYPE_DEPLOY_APPLICATION,
                METRIC_NAME_UPLOAD_S3_BUCKET_TIME_MS, uploadS3BucketTime,
                METRIC_NAME_APPLICATION_SOURCE_BUNDLE_SIZE, sourceFileBundleSize,
                METRIC_NAME_UPLOAD_S3_BUCKET_BYTES_PER_MS);
    }

}
