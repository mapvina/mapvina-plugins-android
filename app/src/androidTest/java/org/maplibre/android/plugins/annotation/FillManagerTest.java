// This file is generated.

package com.mapvina.android.plugins.annotation;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.mapvina.android.geometry.LatLng;
import com.mapvina.android.plugins.testapp.activity.TestActivity;
import com.mapvina.android.plugins.BaseActivityTest;

import timber.log.Timber;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

import static com.mapvina.android.plugins.annotation.MapVinaMapAction.invoke;
import static org.junit.Assert.*;
import static com.mapvina.android.style.layers.Property.*;

/**
 * Basic smoke tests for FillManager
 */
@RunWith(AndroidJUnit4.class)
public class FillManagerTest extends BaseActivityTest {

    private FillManager fillManager;

    @Override
    protected Class getActivityClass() {
        return TestActivity.class;
    }

    private void setupFillManager() {
        Timber.i("Retrieving layer");
        invoke(mapvinaMap, (uiController, mapvinaMap) -> {
            fillManager = new FillManager(idlingResource.getMapView(), mapvinaMap, Objects.requireNonNull(mapvinaMap.getStyle()));
        });
    }

    @Test
    public void testFillAntialiasAsConstant() {
        validateTestSetup();
        setupFillManager();
        Timber.i("fill-antialias");
        invoke(mapvinaMap, (uiController, mapvinaMap) -> {
            assertNotNull(fillManager);

            fillManager.setFillAntialias(true);
            assertEquals((Boolean) fillManager.getFillAntialias(), (Boolean) true);
        });
    }

    @Test
    public void testFillTranslateAsConstant() {
        validateTestSetup();
        setupFillManager();
        Timber.i("fill-translate");
        invoke(mapvinaMap, (uiController, mapvinaMap) -> {
            assertNotNull(fillManager);

            fillManager.setFillTranslate(new Float[]{0f, 0f});
            assertEquals((Float[]) fillManager.getFillTranslate(), (Float[]) new Float[]{0f, 0f});
        });
    }

    @Test
    public void testFillTranslateAnchorAsConstant() {
        validateTestSetup();
        setupFillManager();
        Timber.i("fill-translate-anchor");
        invoke(mapvinaMap, (uiController, mapvinaMap) -> {
            assertNotNull(fillManager);

            fillManager.setFillTranslateAnchor(FILL_TRANSLATE_ANCHOR_MAP);
            assertEquals((String) fillManager.getFillTranslateAnchor(), (String) FILL_TRANSLATE_ANCHOR_MAP);
        });
    }
}
