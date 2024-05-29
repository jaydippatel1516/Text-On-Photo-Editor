package com.kessi.textarts.photoeditor;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.effect.Effect;
import android.media.effect.EffectContext;
import android.media.effect.EffectFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import androidx.core.view.InputDeviceCompat;
import java.util.Map;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class ImageFilterView extends GLSurfaceView implements GLSurfaceView.Renderer {
    private static final String TAG = "ImageFilterView";
    private boolean isSaveImage;
    private PhotoFilter mCurrentEffect;
    private CustomEffect mCustomEffect;
    private Effect mEffect;
    private EffectContext mEffectContext;
    private int mImageHeight;
    private int mImageWidth;
    private boolean mInitialized;
    public OnSaveBitmap mOnSaveBitmap;
    private Bitmap mSourceBitmap;
    private final TextureRenderer mTexRenderer;
    private final int[] mTextures;

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
    }

    public ImageFilterView(Context context) {
        super(context);
        this.isSaveImage = false;
        this.mInitialized = false;
        this.mTexRenderer = new TextureRenderer();
        this.mTextures = new int[2];
        init();
    }

    public ImageFilterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isSaveImage = false;
        this.mInitialized = false;
        this.mTexRenderer = new TextureRenderer();
        this.mTextures = new int[2];
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);
        setRenderer(this);
        setRenderMode(0);
        setFilterEffect(PhotoFilter.NONE);
    }

    public void setSourceBitmap(Bitmap bitmap) {
        this.mSourceBitmap = bitmap;
        this.mInitialized = false;
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        TextureRenderer textureRenderer = this.mTexRenderer;
        if (textureRenderer != null) {
            textureRenderer.updateViewSize(i, i2);
        }
    }

    public void onDrawFrame(GL10 gl10) {
        if (!this.mInitialized) {
            this.mEffectContext = EffectContext.createWithCurrentGlContext();
            this.mTexRenderer.init();
            loadTextures();
            this.mInitialized = true;
        }
        if (!(this.mCurrentEffect == PhotoFilter.NONE && this.mCustomEffect == null)) {
            initEffect();
            applyEffect();
        }
        renderResult();
        if (this.isSaveImage) {
            Bitmap createBitmapFromGLSurface = BitmapUtil.createBitmapFromGLSurface(this, gl10);
            Log.e(TAG, "onDrawFrame: " + createBitmapFromGLSurface);
            this.isSaveImage = false;
            if (this.mOnSaveBitmap != null) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        mOnSaveBitmap.onBitmapReady(createBitmapFromGLSurface);
                    }
                });
            }
        }
    }


    public void setFilterEffect(PhotoFilter photoFilter) {
        this.mCurrentEffect = photoFilter;
        this.mCustomEffect = null;
        requestRender();
    }

    public void setFilterEffect(CustomEffect customEffect) {
        this.mCustomEffect = customEffect;
        requestRender();
    }

    public void saveBitmap(OnSaveBitmap onSaveBitmap) {
        this.mOnSaveBitmap = onSaveBitmap;
        this.isSaveImage = true;
        requestRender();
    }

    private void loadTextures() {
        GLES20.glGenTextures(2, this.mTextures, 0);
        Bitmap bitmap = this.mSourceBitmap;
        if (bitmap != null) {
            this.mImageWidth = bitmap.getWidth();
            int height = this.mSourceBitmap.getHeight();
            this.mImageHeight = height;
            this.mTexRenderer.updateTextureSize(this.mImageWidth, height);
            GLES20.glBindTexture(3553, this.mTextures[0]);
            GLUtils.texImage2D(3553, 0, this.mSourceBitmap, 0);
            GLToolbox.initTexParams();
        }
    }

    private void initEffect() {
        EffectFactory factory = this.mEffectContext.getFactory();
        Effect effect = this.mEffect;
        if (effect != null) {
            effect.release();
        }
        CustomEffect customEffect = this.mCustomEffect;
        if (customEffect != null) {
            this.mEffect = factory.createEffect(customEffect.getEffectName());
            for (Map.Entry<String, Object> entry : this.mCustomEffect.getParameters().entrySet()) {
                this.mEffect.setParameter(entry.getKey(), entry.getValue());
            }
            return;
        }
        switch (mCurrentEffect.ordinal()) {
            case 1:
                Effect createEffect = factory.createEffect("android.media.effect.effects.AutoFixEffect");
                this.mEffect = createEffect;
                createEffect.setParameter("scale", Float.valueOf(0.5f));
                return;
            case 2:
                Effect createEffect2 = factory.createEffect("android.media.effect.effects.BlackWhiteEffect");
                this.mEffect = createEffect2;
                createEffect2.setParameter("black", Float.valueOf(0.1f));
                this.mEffect.setParameter("white", Float.valueOf(0.7f));
                return;
            case 3:
                Effect createEffect3 = factory.createEffect("android.media.effect.effects.BrightnessEffect");
                this.mEffect = createEffect3;
                createEffect3.setParameter("brightness", Float.valueOf(2.0f));
                return;
            case 4:
                Effect createEffect4 = factory.createEffect("android.media.effect.effects.ContrastEffect");
                this.mEffect = createEffect4;
                createEffect4.setParameter("contrast", Float.valueOf(1.4f));
                return;
            case 5:
                this.mEffect = factory.createEffect("android.media.effect.effects.CrossProcessEffect");
                return;
            case 6:
                this.mEffect = factory.createEffect("android.media.effect.effects.DocumentaryEffect");
                return;
            case 7:
                Effect createEffect5 = factory.createEffect("android.media.effect.effects.DuotoneEffect");
                this.mEffect = createEffect5;
                createEffect5.setParameter("first_color", Integer.valueOf((int) InputDeviceCompat.SOURCE_ANY));
                this.mEffect.setParameter("second_color", -12303292);
                return;
            case 8:
                Effect createEffect6 = factory.createEffect("android.media.effect.effects.FillLightEffect");
                this.mEffect = createEffect6;
                createEffect6.setParameter("strength", Float.valueOf(0.8f));
                return;
            case 9:
                Effect createEffect7 = factory.createEffect("android.media.effect.effects.FisheyeEffect");
                this.mEffect = createEffect7;
                createEffect7.setParameter("scale", Float.valueOf(0.5f));
                return;
            case 10:
                Effect createEffect8 = factory.createEffect("android.media.effect.effects.FlipEffect");
                this.mEffect = createEffect8;
                createEffect8.setParameter("horizontal", true);
                return;
            case 11:
                Effect createEffect9 = factory.createEffect("android.media.effect.effects.FlipEffect");
                this.mEffect = createEffect9;
                createEffect9.setParameter("vertical", true);
                return;
            case 12:
                Effect createEffect10 = factory.createEffect("android.media.effect.effects.GrainEffect");
                this.mEffect = createEffect10;
                createEffect10.setParameter("strength", Float.valueOf(1.0f));
                return;
            case 13:
                this.mEffect = factory.createEffect("android.media.effect.effects.GrayscaleEffect");
                return;
            case 14:
                this.mEffect = factory.createEffect("android.media.effect.effects.LomoishEffect");
                return;
            case 15:
                this.mEffect = factory.createEffect("android.media.effect.effects.NegativeEffect");
                return;
            case 16:
                this.mEffect = factory.createEffect("android.media.effect.effects.PosterizeEffect");
                return;
            case 17:
                Effect createEffect11 = factory.createEffect("android.media.effect.effects.RotateEffect");
                this.mEffect = createEffect11;
                createEffect11.setParameter("angle", 180);
                return;
            case 18:
                Effect createEffect12 = factory.createEffect("android.media.effect.effects.SaturateEffect");
                this.mEffect = createEffect12;
                createEffect12.setParameter("scale", Float.valueOf(0.5f));
                return;
            case 19:
                this.mEffect = factory.createEffect("android.media.effect.effects.SepiaEffect");
                return;
            case 20:
                this.mEffect = factory.createEffect("android.media.effect.effects.SharpenEffect");
                return;
            case 21:
                Effect createEffect13 = factory.createEffect("android.media.effect.effects.ColorTemperatureEffect");
                this.mEffect = createEffect13;
                createEffect13.setParameter("scale", Float.valueOf(0.9f));
                return;
            case 22:
                Effect createEffect14 = factory.createEffect("android.media.effect.effects.TintEffect");
                this.mEffect = createEffect14;
                createEffect14.setParameter("tint", -65281);
                return;
            case 23:
                Effect createEffect15 = factory.createEffect("android.media.effect.effects.VignetteEffect");
                this.mEffect = createEffect15;
                createEffect15.setParameter("scale", Float.valueOf(0.5f));
                return;
            default:
                return;
        }
    }

    private void applyEffect() {
        Effect effect = this.mEffect;
        int[] iArr = this.mTextures;
        effect.apply(iArr[0], this.mImageWidth, this.mImageHeight, iArr[1]);
    }

    private void renderResult() {
        if (this.mCurrentEffect == PhotoFilter.NONE && this.mCustomEffect == null) {
            this.mTexRenderer.renderTexture(this.mTextures[0]);
        } else {
            this.mTexRenderer.renderTexture(this.mTextures[1]);
        }
    }
}
