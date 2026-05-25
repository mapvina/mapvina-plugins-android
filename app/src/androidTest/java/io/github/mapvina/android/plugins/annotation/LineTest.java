
// This file is generated.

package io.github.mapvina.android.plugins.annotation;

import android.graphics.PointF;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import io.github.mapvina.android.geometry.LatLng;
import io.github.mapvina.android.plugins.BaseActivityTest;
import io.github.mapvina.android.plugins.testapp.activity.TestActivity;
import io.github.mapvina.android.utils.ColorUtils;

import timber.log.Timber;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

import static io.github.mapvina.android.plugins.annotation.MapVinaMapAction.invoke;
import static org.junit.Assert.*;
import static io.github.mapvina.android.style.layers.Property.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic smoke tests for Line
 */
@RunWith(AndroidJUnit4.class)
public class LineTest extends BaseActivityTest {

    private Line line;

    @Override
    protected Class getActivityClass() {
        return TestActivity.class;
    }

    private void setupAnnotation() {
        Timber.i("Retrieving layer");
        invoke(mapvinaMap, (uiController, mapvinaMap) -> {
            LineManager lineManager = new LineManager(idlingResource.getMapView(), mapvinaMap, Objects.requireNonNull(mapvinaMap.getStyle()));
            List<LatLng> latLngs = new ArrayList<>();
            latLngs.add(new LatLng());
            latLngs.add(new LatLng(1, 1));
            line = lineManager.create(new LineOptions().withLatLngs(latLngs));
        });
    }

    @Test
    public void testLineJoin() {
        validateTestSetup();
        setupAnnotation();
        Timber.i("line-join");
        invoke(mapvinaMap, (uiController, mapvinaMap) -> {
            assertNotNull(line);

            line.setLineJoin(LINE_JOIN_BEVEL);
            assertEquals((String) line.getLineJoin(), (String) LINE_JOIN_BEVEL);
        });
    }

    @Test
    public void testLineOpacity() {
        validateTestSetup();
        setupAnnotation();
        Timber.i("line-opacity");
        invoke(mapvinaMap, (uiController, mapvinaMap) -> {
            assertNotNull(line);

            line.setLineOpacity(2.0f);
            assertEquals((Float) line.getLineOpacity(), (Float) 2.0f);
        });
    }

    @Test
    public void testLineColor() {
        validateTestSetup();
        setupAnnotation();
        Timber.i("line-color");
        invoke(mapvinaMap, (uiController, mapvinaMap) -> {
            assertNotNull(line);

            line.setLineColor("rgba(0, 0, 0, 1)");
            assertEquals(line.getLineColor(), "rgba(0, 0, 0, 1)");
        });
    }

    @Test
    public void testLineColorAsInt() {
        validateTestSetup();
        setupAnnotation();
        Timber.i("line-color");
        invoke(mapvinaMap, (uiController, mapvinaMap) -> {
            assertNotNull(line);
            line.setLineColor(ColorUtils.rgbaToColor("rgba(0, 0, 0, 1)"));
            assertEquals(line.getLineColorAsInt(), ColorUtils.rgbaToColor("rgba(0, 0, 0, 1)"));
        });
    }


    @Test
    public void testLineWidth() {
        validateTestSetup();
        setupAnnotation();
        Timber.i("line-width");
        invoke(mapvinaMap, (uiController, mapvinaMap) -> {
            assertNotNull(line);

            line.setLineWidth(2.0f);
            assertEquals((Float) line.getLineWidth(), (Float) 2.0f);
        });
    }

    @Test
    public void testLineGapWidth() {
        validateTestSetup();
        setupAnnotation();
        Timber.i("line-gap-width");
        invoke(mapvinaMap, (uiController, mapvinaMap) -> {
            assertNotNull(line);

            line.setLineGapWidth(2.0f);
            assertEquals((Float) line.getLineGapWidth(), (Float) 2.0f);
        });
    }

    @Test
    public void testLineOffset() {
        validateTestSetup();
        setupAnnotation();
        Timber.i("line-offset");
        invoke(mapvinaMap, (uiController, mapvinaMap) -> {
            assertNotNull(line);

            line.setLineOffset(2.0f);
            assertEquals((Float) line.getLineOffset(), (Float) 2.0f);
        });
    }

    @Test
    public void testLineBlur() {
        validateTestSetup();
        setupAnnotation();
        Timber.i("line-blur");
        invoke(mapvinaMap, (uiController, mapvinaMap) -> {
            assertNotNull(line);

            line.setLineBlur(2.0f);
            assertEquals((Float) line.getLineBlur(), (Float) 2.0f);
        });
    }

    @Test
    public void testLinePattern() {
        validateTestSetup();
        setupAnnotation();
        Timber.i("line-pattern");
        invoke(mapvinaMap, (uiController, mapvinaMap) -> {
            assertNotNull(line);

            line.setLinePattern("pedestrian-polygon");
            assertEquals((String) line.getLinePattern(), (String) "pedestrian-polygon");
        });
    }
}
