package com.kessi.textarts.photoeditor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kessi.textarts.R;

public class PhotoEditorView extends RelativeLayout {
    private static final String TAG = "PhotoEditorView";
    private BrushDrawingView mBrushDrawingView;
    public ImageFilterView mImageFilterView;
    public FilterImageView mImgSource;

    public PhotoEditorView(Context context) {
        super(context);
        init(null);
    }

    public PhotoEditorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public PhotoEditorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    public PhotoEditorView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        Drawable drawable;
        FilterImageView filterImageView = new FilterImageView(getContext());
        this.mImgSource = filterImageView;
        filterImageView.setId(1);
        this.mImgSource.setAdjustViewBounds(true);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(13, -1);
        int[] iArr = {R.attr.photo_src};
        if (!(attributeSet == null || (drawable = getContext().obtainStyledAttributes(attributeSet, iArr).getDrawable(0)) == null)) {
            this.mImgSource.setImageDrawable(drawable);
        }
        BrushDrawingView brushDrawingView = new BrushDrawingView(getContext());
        this.mBrushDrawingView = brushDrawingView;
        brushDrawingView.setVisibility(View.GONE);
        this.mBrushDrawingView.setId(2);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(13, -1);
        layoutParams2.addRule(6, 1);
        layoutParams2.addRule(8, 1);
        ImageFilterView imageFilterView = new ImageFilterView(getContext());
        this.mImageFilterView = imageFilterView;
        imageFilterView.setId(3);
        this.mImageFilterView.setVisibility(View.GONE);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(13, -1);
        layoutParams3.addRule(6, 1);
        layoutParams3.addRule(8, 1);
        this.mImgSource.setOnImageChangedListener(new FilterImageView.OnImageChangedListener() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap) {
                mImageFilterView.setFilterEffect(PhotoFilter.NONE);
                mImageFilterView.setSourceBitmap(bitmap);
            }
        });
        addView(this.mImgSource, layoutParams);
        addView(this.mImageFilterView, layoutParams3);
        addView(this.mBrushDrawingView, layoutParams2);
    }


    public ImageView getSource() {
        return this.mImgSource;
    }

    public BrushDrawingView getBrushDrawingView() {
        return this.mBrushDrawingView;
    }

    public void saveFilter(final OnSaveBitmap onSaveBitmap) {
        if (this.mImageFilterView.getVisibility() == 0) {
            this.mImageFilterView.saveBitmap(new OnSaveBitmap() {
                /* class com.kessi.textarts.photoeditor.PhotoEditorView.AnonymousClass1 */

                @Override // com.kessi.textarts.photoeditor.OnSaveBitmap
                public void onBitmapReady(Bitmap bitmap) {
                    Log.e(PhotoEditorView.TAG, "saveFilter: " + bitmap);
                    PhotoEditorView.this.mImgSource.setImageBitmap(bitmap);
                    PhotoEditorView.this.mImageFilterView.setVisibility(View.GONE);
                    onSaveBitmap.onBitmapReady(bitmap);
                }

                @Override // com.kessi.textarts.photoeditor.OnSaveBitmap
                public void onFailure(Exception exc) {
                    onSaveBitmap.onFailure(exc);
                }
            });
        } else {
            onSaveBitmap.onBitmapReady(this.mImgSource.getBitmap());
        }
    }

    public void setFilterEffect(PhotoFilter photoFilter) {
        this.mImageFilterView.setVisibility(View.VISIBLE);
        this.mImageFilterView.setSourceBitmap(this.mImgSource.getBitmap());
        this.mImageFilterView.setFilterEffect(photoFilter);
    }

    public void setFilterEffect(CustomEffect customEffect) {
        this.mImageFilterView.setVisibility(View.VISIBLE);
        this.mImageFilterView.setSourceBitmap(this.mImgSource.getBitmap());
        this.mImageFilterView.setFilterEffect(customEffect);
    }
}
