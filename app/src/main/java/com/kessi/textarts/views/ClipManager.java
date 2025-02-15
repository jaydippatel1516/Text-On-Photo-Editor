package com.kessi.textarts.views;

import android.graphics.Paint;
import android.graphics.Path;

public interface ClipManager {
    Path createMask(int i, int i2);

    Paint getPaint();

    Path getShadowConvexPath();

    boolean requiresBitmap();

    void setupClipLayout(int i, int i2);
}
