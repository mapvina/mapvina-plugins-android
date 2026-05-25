package io.github.mapvina.android.plugins.offline.ui;

import io.github.mapvina.android.offline.OfflineRegionDefinition;

public interface RegionSelectedCallback {

    void onSelected(OfflineRegionDefinition definition, String regionName);

    void onCancel();
}
