// This file is generated.

package io.github.mapvina.android.plugins.annotation;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import io.github.mapvina.android.geometry.LatLng;
import io.github.mapvina.android.plugins.testapp.activity.TestActivity;
import io.github.mapvina.android.plugins.BaseActivityTest;

import timber.log.Timber;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

import static io.github.mapvina.android.plugins.annotation.MapVinaMapAction.invoke;
import static org.junit.Assert.*;
import static io.github.mapvina.android.style.layers.Property.*;

/**
 * Basic smoke tests for CircleManager
 */
@RunWith(AndroidJUnit4.class)
public class CircleManagerTest extends BaseActivityTest {

    private CircleManager circleManager;

    @Override
    protected Class getActivityClass() {
        return TestActivity.class;
    }

    private void setupCircleManager() {
        Timber.i("Retrieving layer");
        invoke(mapvinaMap, (uiController, mapvinaMap) -> {
            circleManager = new CircleManager(idlingResource.getMapView(), mapvinaMap, Objects.requireNonNull(mapvinaMap.getStyle()));
        });
    }

    @Test
    public void testCircleTranslateAsConstant() {
        validateTestSetup();
        setupCircleManager();
        Timber.i("circle-translate");
        invoke(mapvinaMap, (uiController, mapvinaMap) -> {
            assertNotNull(circleManager);

            circleManager.setCircleTranslate(new Float[]{0f, 0f});
            assertEquals((Float[]) circleManager.getCircleTranslate(), (Float[]) new Float[]{0f, 0f});
        });
    }

    @Test
    public void testCircleTranslateAnchorAsConstant() {
        validateTestSetup();
        setupCircleManager();
        Timber.i("circle-translate-anchor");
        invoke(mapvinaMap, (uiController, mapvinaMap) -> {
            assertNotNull(circleManager);

            circleManager.setCircleTranslateAnchor(CIRCLE_TRANSLATE_ANCHOR_MAP);
            assertEquals((String) circleManager.getCircleTranslateAnchor(), (String) CIRCLE_TRANSLATE_ANCHOR_MAP);
        });
    }

    @Test
    public void testCirclePitchScaleAsConstant() {
        validateTestSetup();
        setupCircleManager();
        Timber.i("circle-pitch-scale");
        invoke(mapvinaMap, (uiController, mapvinaMap) -> {
            assertNotNull(circleManager);

            circleManager.setCirclePitchScale(CIRCLE_PITCH_SCALE_MAP);
            assertEquals((String) circleManager.getCirclePitchScale(), (String) CIRCLE_PITCH_SCALE_MAP);
        });
    }

    @Test
    public void testCirclePitchAlignmentAsConstant() {
        validateTestSetup();
        setupCircleManager();
        Timber.i("circle-pitch-alignment");
        invoke(mapvinaMap, (uiController, mapvinaMap) -> {
            assertNotNull(circleManager);

            circleManager.setCirclePitchAlignment(CIRCLE_PITCH_ALIGNMENT_MAP);
            assertEquals((String) circleManager.getCirclePitchAlignment(), (String) CIRCLE_PITCH_ALIGNMENT_MAP);
        });
    }
}
