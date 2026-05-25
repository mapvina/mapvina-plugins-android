package io.github.mapvina.android.plugins.testapp.activity.annotation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import io.github.mapvina.android.camera.CameraPosition;
import io.github.mapvina.android.geometry.LatLng;
import io.github.mapvina.android.maps.MapVinaMap;
import io.github.mapvina.android.maps.MapView;
import io.github.mapvina.android.maps.Style;
import io.github.mapvina.android.plugins.annotation.SymbolManager;
import io.github.mapvina.android.plugins.annotation.SymbolOptions;
import io.github.mapvina.android.plugins.testapp.TestStyles;
import io.github.mapvina.android.plugins.testapp.R;
import io.github.mapvina.android.plugins.testapp.Utils;

import static io.github.mapvina.android.style.layers.Property.ICON_ANCHOR_BOTTOM;

/**
 * Test activity showcasing to add a Symbol on click.
 * <p>
 * Shows how to use a OnMapClickListener and a OnMapLongClickListener
 * </p>
 */
public class PressForSymbolActivity extends AppCompatActivity {

    public static final String ID_ICON = "id-icon";
    private SymbolManager symbolManager;
    private MapView mapView;
    private MapVinaMap mapvinaMap;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(map -> {
            mapvinaMap = map;
            mapvinaMap.setCameraPosition(new CameraPosition.Builder()
                .target(new LatLng(60.169091, 24.939876))
                .zoom(12)
                .tilt(20)
                .bearing(90)
                .build()
            );
            mapvinaMap.addOnMapLongClickListener(this::addSymbol);
            mapvinaMap.addOnMapClickListener(this::addSymbol);
            mapvinaMap.setStyle(getStyleBuilder(TestStyles.BRIGHT.getUrl()), style -> {
                findViewById(R.id.fabStyles).setOnClickListener(v ->
                    mapvinaMap.setStyle(getStyleBuilder(Utils.INSTANCE.getNextStyle())));

                symbolManager = new SymbolManager(mapView, mapvinaMap, style);
                symbolManager.setIconAllowOverlap(true);
                symbolManager.setTextAllowOverlap(true);
            });
        });
    }

    private boolean addSymbol(LatLng point) {
        if (symbolManager == null) {
            return false;
        }

        symbolManager.create(new SymbolOptions()
            .withLatLng(point)
            .withIconImage(ID_ICON)
            .withIconAnchor(ICON_ANCHOR_BOTTOM)
        );
        return true;
    }

    private Style.Builder getStyleBuilder(@NonNull String styleUrl) {
        return new Style.Builder().fromUri(styleUrl);
        //.withImage(ID_ICON, generateBitmap(R.drawable.mapbox_ic_place));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapvinaMap.removeOnMapClickListener(this::addSymbol);
        mapvinaMap.removeOnMapLongClickListener(this::addSymbol);

        if (symbolManager != null) {
            symbolManager.onDestroy();
        }

        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    private Bitmap generateBitmap(@DrawableRes int drawableRes) {
        Drawable drawable = getResources().getDrawable(drawableRes);
        return getBitmapFromDrawable(drawable);
    }

    static Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else {
            // width and height are equal for all assets since they are ovals.
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        }
    }
}

