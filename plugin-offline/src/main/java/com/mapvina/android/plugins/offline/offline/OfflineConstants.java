package com.mapvina.android.plugins.offline.offline;

public class OfflineConstants {

    private OfflineConstants() {
        // No Instances
    }

    static final String ACTION_START_DOWNLOAD = "com.mapvina.android.plugins.offline.download.start";
    static final String ACTION_CANCEL_DOWNLOAD = "com.mapvina.android.plugins.offline.download.cancel";
    static final String ACTION_OFFLINE = "com.mapvina.android.plugins.offline";
    static final String KEY_STATE = "com.mapvina.android.plugins.offline.state";
    static final String STATE_STARTED = "com.mapvina.android.plugins.offline.state.started";
    static final String STATE_FINISHED = "com.mapvina.android.plugins.offline.state.complete";
    static final String STATE_ERROR = "com.mapvina.android.plugins.offline.state.error";
    static final String STATE_CANCEL = "com.mapvina.android.plugins.offline.state.cancel";
    static final String STATE_PROGRESS = "com.mapvina.android.plugins.offline.state.progress";
    static final String KEY_BUNDLE_OFFLINE_REGION = "com.mapvina.android.plugins.offline.region";
    static final String KEY_BUNDLE_ERROR = "com.mapvina.android.plugins.offline.error";
    static final String KEY_BUNDLE_MESSAGE = "com.mapvina.android.plugins.offline.error";
    static final String KEY_PROGRESS = "com.mapvina.android.plugins.offline.progress";
    public static final String NOTIFICATION_CHANNEL = "offline";

    public static final String RETURNING_DEFINITION = "com.mapvina.android.plugins.offline.returning.definition";
    public static final String RETURNING_REGION_NAME = "com.mapvina.android.plugins.offline.returing.region.name";

    public static final String KEY_BUNDLE = "com.mapvina.android.plugins.offline.download.object";
}
