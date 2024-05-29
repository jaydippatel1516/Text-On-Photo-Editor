package com.kessi.textarts.views;

import android.graphics.Paint;
import android.graphics.Path;

public class ClipPathManager implements ClipManager {
    private ClipPathCreator createClipPath = null;
    private final Paint paint;
    protected final Path path = new Path();

    public interface ClipPathCreator {
        Path createClipPath(int i, int i2);

        boolean requiresBitmap();
    }

    public ClipPathManager() {
        Paint paint2 = new Paint(1);
        this.paint = paint2;
        paint2.setColor(-16777216);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);
        paint2.setStrokeWidth(1.0f);
    }

    @Override 
    public Paint getPaint() {
        return this.paint;
    }

    @Override 
    public boolean requiresBitmap() {
        ClipPathCreator clipPathCreator = this.createClipPath;
        return clipPathCreator != null && clipPathCreator.requiresBitmap();
    }

    public final Path getPath(int i, int i2) {
        ClipPathCreator clipPathCreator = this.createClipPath;
        if (clipPathCreator != null) {
            return clipPathCreator.createClipPath(i, i2);
        }
        return null;
    }

    public void setClipPathCreator(ClipPathCreator clipPathCreator) {
        this.createClipPath = clipPathCreator;
    }

    @Override 
    public Path createMask(int i, int i2) {
        return this.path;
    }

    @Override 
    public Path getShadowConvexPath() {
        return this.path;
    }

    @Override 
    public void setupClipLayout(int i, int i2) {
        this.path.reset();
        Path path2 = getPath(i, i2);
        if (path2 != null) {
            this.path.set(path2);
        }
    }
}
