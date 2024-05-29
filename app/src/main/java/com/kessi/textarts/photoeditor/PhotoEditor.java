package com.kessi.textarts.photoeditor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.kessi.textarts.R;
import com.kessi.textarts.photoeditor.MultiTouchListener;
import com.kessi.textarts.photoeditor.SaveSettings;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PhotoEditor implements BrushViewChangeListener {
    private static final String TAG = "PhotoEditor";
    private final List<View> addedViews;
    private final View alignView;
    private final BrushDrawingView brushDrawingView;
    private final Context context;
    private final View deleteView;
    private RoundFrameLayout frBorder;
    Handler handler;
    private final ImageView imageView;
    private final boolean isTextPinchZoomable;
    private final Typeface mDefaultEmojiTypeface;
    private final Typeface mDefaultTextTypeface;
    private final LayoutInflater mLayoutInflater;
    public OnPhotoEditorListener mOnPhotoEditorListener;
    public PointF midPoint;
    public PhotoEditorView parentView;
    private final List<View> redoViews;
    private final PointF startPoint;
    private final View zoomView;

    public interface OnSaveListener {
        void onFailure(Exception exc);

        void onSuccess(String str);
    }

    private PhotoEditor(Builder builder) {
        this.handler = new Handler();
        this.midPoint = new PointF();
        this.startPoint = new PointF();
        Context context2 = builder.context;
        this.context = context2;
        this.parentView = builder.parentView;
        this.imageView = builder.imageView;
        this.deleteView = builder.deleteView;
        this.alignView = builder.alignView;
        this.zoomView = builder.zoomView;
        BrushDrawingView brushDrawingView2 = builder.brushDrawingView;
        this.brushDrawingView = brushDrawingView2;
        this.isTextPinchZoomable = builder.isTextPinchZoomable;
        this.mDefaultTextTypeface = builder.textTypeface;
        this.mDefaultEmojiTypeface = builder.emojiTypeface;
        this.mLayoutInflater = (LayoutInflater) context2.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        brushDrawingView2.setBrushViewChangeListener(this);
        this.addedViews = new ArrayList();
        this.redoViews = new ArrayList();
    }

    public void addImage(Bitmap bitmap) {
        final View layout = getLayout(ViewType.IMAGE);
        final ImageView imageView2 = (ImageView) layout.findViewById(R.id.imgPhotoEditorImage);
        final FrameLayout frameLayout = (FrameLayout) layout.findViewById(R.id.frmBorder);
        final View findViewById = layout.findViewById(R.id.imgPhotoEditorClose);
        final View findViewById2 = layout.findViewById(R.id.imgPhotoEditorZoom);
        imageView2.setImageBitmap(bitmap);
        final Runnable r6 = new Runnable() {

            public void run() {
                findViewById.setVisibility(View.GONE);
                findViewById2.setVisibility(View.GONE);
                frameLayout.setBackgroundResource(0);
            }
        };
        findViewById.setVisibility(View.VISIBLE);
        findViewById2.setVisibility(View.VISIBLE);
        frameLayout.setBackgroundResource(R.drawable.rounded_border_tv);
        this.handler.postDelayed(r6, 2500);
        MultiTouchListener multiTouchListener = getMultiTouchListener();
        multiTouchListener.setOnGestureControl(new MultiTouchListener.OnGestureControl() {

            @Override 
            public void onDoubleClick() {
            }

            @Override 
            public void onLongClick() {
            }

            @Override 
            public void onClick() {
                findViewById.setVisibility(View.VISIBLE);
                findViewById2.setVisibility(View.VISIBLE);
                frameLayout.setBackgroundResource(R.drawable.rounded_border_tv);
                PhotoEditor.this.handler.removeCallbacks(r6);
                PhotoEditor.this.handler.postDelayed(r6, 2500);
            }

            @Override 
            public void onSingleTap() {
                findViewById.setVisibility(View.VISIBLE);
                findViewById2.setVisibility(View.VISIBLE);
                frameLayout.setBackgroundResource(R.drawable.rounded_border_tv);
                PhotoEditor.this.handler.removeCallbacks(r6);
                PhotoEditor.this.handler.postDelayed(r6, 2500);
                if (PhotoEditor.this.mOnPhotoEditorListener != null) {
                    PhotoEditor.this.mOnPhotoEditorListener.onClickGetImageViewListener(imageView2, layout);
                }
            }
        });
        layout.setOnTouchListener(multiTouchListener);
        addViewToParent(layout, ViewType.IMAGE);
    }

    public void addText(String str, int i) {
        addText(null, str, i);
    }

    public void addText(Typeface typeface, String str, int i) {
        this.brushDrawingView.setBrushDrawingMode(false);
        final View layout = getLayout(ViewType.TEXT);
        final StrokeTextView strokeTextView = (StrokeTextView) layout.findViewById(R.id.tvPhotoEditorText);
        final View findViewById = layout.findViewById(R.id.imgPhotoEditorClose);
        final View findViewById2 = layout.findViewById(R.id.imgPhotoEditorZoom);
        final RoundFrameLayout roundFrameLayout = (RoundFrameLayout) layout.findViewById(R.id.frmBorder_highlight);
        final FrameLayout frameLayout = (FrameLayout) layout.findViewById(R.id.frmBorder);
        strokeTextView.setText(str);
        strokeTextView.setTextColor(i);
        final Runnable r6 = new Runnable() {

            public void run() {
                findViewById.setVisibility(View.GONE);
                findViewById2.setVisibility(View.GONE);
                frameLayout.setBackgroundResource(0);
            }
        };
        findViewById.setVisibility(View.VISIBLE);
        findViewById2.setVisibility(View.VISIBLE);
        frameLayout.setBackgroundResource(R.drawable.rounded_border_tv);
        this.handler.removeCallbacks(r6);
        this.handler.postDelayed(r6, 2500);
        if (typeface != null) {
            strokeTextView.setTypeface(typeface);
        }
        MultiTouchListener multiTouchListener = getMultiTouchListener();
        multiTouchListener.setOnGestureControl(new MultiTouchListener.OnGestureControl() {

            @Override 
            public void onLongClick() {
            }

            @Override 
            public void onClick() {
                findViewById.setVisibility(View.VISIBLE);
                findViewById2.setVisibility(View.VISIBLE);
                frameLayout.setBackgroundResource(R.drawable.rounded_border_tv);
                PhotoEditor.this.handler.removeCallbacks(r6);
                PhotoEditor.this.handler.postDelayed(r6, 2500);
            }

            @Override 
            public void onDoubleClick() {
                String charSequence = strokeTextView.getText().toString();
                int currentTextColor = strokeTextView.getCurrentTextColor();
                if (PhotoEditor.this.mOnPhotoEditorListener != null) {
                    PhotoEditor.this.mOnPhotoEditorListener.onEditTextChangeListener(layout, charSequence, currentTextColor);
                }
            }

            @Override 
            public void onSingleTap() {
                findViewById.setVisibility(View.VISIBLE);
                findViewById2.setVisibility(View.VISIBLE);
                frameLayout.setBackgroundResource(R.drawable.rounded_border_tv);
                PhotoEditor.this.handler.removeCallbacks(r6);
                PhotoEditor.this.handler.postDelayed(r6, 2500);
                if (PhotoEditor.this.mOnPhotoEditorListener != null) {
                    PhotoEditor.this.mOnPhotoEditorListener.onClickGetEditTextChangeListener(strokeTextView, roundFrameLayout);
                }
            }
        });
        layout.setOnTouchListener(multiTouchListener);
        addViewToParent(layout, ViewType.TEXT);
        this.mOnPhotoEditorListener.onAdded(strokeTextView, roundFrameLayout);
        String charSequence = strokeTextView.getText().toString();
        int currentTextColor = strokeTextView.getCurrentTextColor();
        OnPhotoEditorListener onPhotoEditorListener = this.mOnPhotoEditorListener;
        if (onPhotoEditorListener != null) {
            onPhotoEditorListener.onEditTextChangeListener(layout, charSequence, currentTextColor);
        }
    }

    public void editText(View view, String str, int i) {
        editText(view, null, str, i);
    }

    public void editText(View view, Typeface typeface, String str, int i) {
        TextView textView = (TextView) view.findViewById(R.id.tvPhotoEditorText);
        if (textView != null && this.addedViews.contains(view) && !TextUtils.isEmpty(str)) {
            textView.setText(str);
            if (typeface != null) {
                textView.setTypeface(typeface);
            }
            textView.setTextColor(i);
            this.parentView.updateViewLayout(view, view.getLayoutParams());
            int indexOf = this.addedViews.indexOf(view);
            if (indexOf > -1) {
                this.addedViews.set(indexOf, view);
            }
        }
    }

    public void addEmoji(String str) {
        addEmoji(null, str);
    }

    public void addEmoji(Typeface typeface, String str) {
        this.brushDrawingView.setBrushDrawingMode(false);
        View layout = getLayout(ViewType.EMOJI);
        TextView textView = (TextView) layout.findViewById(R.id.tvPhotoEditorText);
        final FrameLayout frameLayout = (FrameLayout) layout.findViewById(R.id.frmBorder);
        final View findViewById = layout.findViewById(R.id.imgPhotoEditorClose);
        final View findViewById2 = layout.findViewById(R.id.imgPhotoEditorZoom);
        if (typeface != null) {
            textView.setTypeface(typeface);
        }
        final Runnable r9 = new Runnable() {

            public void run() {
                findViewById.setVisibility(View.GONE);
                findViewById2.setVisibility(View.GONE);
                frameLayout.setBackgroundResource(0);
            }
        };
        findViewById.setVisibility(View.VISIBLE);
        findViewById2.setVisibility(View.VISIBLE);
        frameLayout.setBackgroundResource(R.drawable.rounded_border_tv);
        this.handler.postDelayed(r9, 2500);
        textView.setTextSize(56.0f);
        textView.setText(str);
        MultiTouchListener multiTouchListener = getMultiTouchListener();
        multiTouchListener.setOnGestureControl(new MultiTouchListener.OnGestureControl() {

            @Override 
            public void onDoubleClick() {
            }

            @Override 
            public void onLongClick() {
            }

            @Override 
            public void onClick() {
                findViewById.setVisibility(View.VISIBLE);
                findViewById2.setVisibility(View.VISIBLE);
                frameLayout.setBackgroundResource(R.drawable.rounded_border_tv);
                PhotoEditor.this.handler.postDelayed(r9, 2500);
            }

            @Override 
            public void onSingleTap() {
                findViewById.setVisibility(View.VISIBLE);
                findViewById2.setVisibility(View.VISIBLE);
                frameLayout.setBackgroundResource(R.drawable.rounded_border_tv);
                PhotoEditor.this.handler.postDelayed(r9, 2500);
            }
        });
        layout.setOnTouchListener(multiTouchListener);
        addViewToParent(layout, ViewType.EMOJI);
    }

    private void addViewToParent(View view, ViewType viewType) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13, -1);
        this.parentView.addView(view, layoutParams);
        this.addedViews.add(view);
        OnPhotoEditorListener onPhotoEditorListener = this.mOnPhotoEditorListener;
        if (onPhotoEditorListener != null) {
            onPhotoEditorListener.onAddViewListener(viewType, this.addedViews.size());
        }
    }

    private MultiTouchListener getMultiTouchListener() {
        return new MultiTouchListener(this.context, this.parentView, this.imageView, this.isTextPinchZoomable, this.mOnPhotoEditorListener);
    }

    

    private View getLayout(final ViewType viewType) {
        int i = viewType.ordinal();
        View view = null;
        if (i == 1) {
            view = this.mLayoutInflater.inflate(R.layout.view_photo_editor_text, (ViewGroup) null);
            TextView textView = (TextView) view.findViewById(R.id.tvPhotoEditorText);
            if (!(textView == null || this.mDefaultTextTypeface == null)) {
                textView.setGravity(17);
                if (this.mDefaultEmojiTypeface != null) {
                    textView.setTypeface(this.mDefaultTextTypeface);
                }
            }
        } else if (i == 2) {
            view = this.mLayoutInflater.inflate(R.layout.view_photo_editor_image, (ViewGroup) null);
        } else if (i == 3) {
            View inflate = this.mLayoutInflater.inflate(R.layout.view_photo_editor_emoji, (ViewGroup) null);
            TextView textView2 = (TextView) inflate.findViewById(R.id.tvPhotoEditorText);
            if (textView2 != null) {
                Typeface typeface = this.mDefaultEmojiTypeface;
                if (typeface != null) {
                    textView2.setTypeface(typeface);
                }
                textView2.setGravity(17);
                textView2.setLayerType(1, null);
            }
            view = inflate;
        }
        if (view != null) {
            view.setTag(viewType);
            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.frmBorder);
            final View findViewById = view.findViewById(R.id.imgPhotoEditorClose);
            if (findViewById != null) {
                findViewById.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View view) {
                        PhotoEditor.this.viewUndo(view, viewType);
                    }
                });
            }
            final View findViewById2 = view.findViewById(R.id.imgPhotoEditorZoom);
            if (findViewById2 != null) {
                findViewById2.setOnTouchListener(new View.OnTouchListener() {
                    /* class com.kessi.textarts.photoeditor.PhotoEditor.AnonymousClass8 */
                    float rotation = 0.0f;
                    float scaleX = 1.0f;

                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        Log.d("XXXXXXXX", "setOnTouchListener " + motionEvent.getRawX() + " " + motionEvent.getRawY());
                        int action = motionEvent.getAction();
                        if (action == 0) {
                            this.scaleX = ((View) view.getParent()).getScaleX();
                            this.rotation = ((View) view.getParent()).getRotation();
                            PhotoEditor.this.getPointF(view, motionEvent);
                            Log.d("XXXXXXXX", "ACTION_DOWN " + this.scaleX + " " + this.rotation + " mid " + PhotoEditor.this.midPoint.x + " " + PhotoEditor.this.midPoint.y);
                            return true;
                        } else if (action != 1 && action != 2) {
                            return false;
                        } else {
                            PhotoEditor.this.zoomAndRotateSticker((View) view.getParent(), motionEvent, frameLayout, findViewById, findViewById2, this.scaleX, this.rotation);
                            return false;
                        }
                    }
                });
            }
        }
        return view;
    }

    public PointF getPointF(View view, MotionEvent motionEvent) {
        View view2 = (View) view.getParent();
        this.startPoint.set(motionEvent.getRawX(), motionEvent.getRawY());
        this.midPoint.set(view2.getX() + ((float) (view2.getWidth() / 2)), view2.getY() + ((float) (view2.getHeight() / 2)));
        return this.midPoint;
    }

    public void zoomAndRotateSticker(View view, MotionEvent motionEvent, FrameLayout frameLayout, View view2, View view3, float f, float f2) {
        if (view != null) {
            float floatA = (getFloatA(this.midPoint.x, this.midPoint.y, motionEvent.getRawX(), motionEvent.getRawY()) / getFloatA(this.startPoint.x, this.startPoint.y, this.midPoint.x, this.midPoint.y)) * f;
            view.setPivotX((float) (view.getWidth() / 2));
            view.setPivotY((float) (view.getHeight() / 2));
            view.setScaleX(floatA);
            view.setScaleY(floatA);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) frameLayout.getLayoutParams();
            int dimension = (int) (this.context.getResources().getDimension(R.dimen.frame_margin) / floatA);
            layoutParams.setMargins(dimension, dimension, dimension, dimension);
            frameLayout.setLayoutParams(layoutParams);
            view2.setPivotX(0.0f);
            view2.setPivotY(0.0f);
            view3.setPivotX((float) view3.getWidth());
            view3.setPivotY((float) view3.getHeight());
            float f3 = 1.0f / floatA;
            view2.setScaleX(f3);
            view2.setScaleY(f3);
            view3.setScaleX(f3);
            view3.setScaleY(f3);
            float degrees = f2 + ((float) Math.toDegrees(Math.atan2((double) (motionEvent.getRawY() - this.midPoint.y), (double) (motionEvent.getRawX() - this.midPoint.x)) - Math.atan2((double) (this.startPoint.y - this.midPoint.y), (double) (this.startPoint.x - this.midPoint.x))));
            view.setRotation(degrees);
            view.requestLayout();
            Log.d("XXXXXXXX", "ACTION_MOVE  " + floatA + " " + degrees + " " + motionEvent.getRawX() + " " + motionEvent.getRawY());
        }
    }

    public float getFloatA(float f, float f2, float f3, float f4) {
        double d = (double) (f - f3);
        double d2 = (double) (f2 - f4);
        return (float) Math.sqrt((d * d) + (d2 * d2));
    }

    public void setBrushDrawingMode(boolean z) {
        BrushDrawingView brushDrawingView2 = this.brushDrawingView;
        if (brushDrawingView2 != null) {
            brushDrawingView2.setBrushDrawingMode(z);
        }
    }

    public Boolean getBrushDrawableMode() {
        BrushDrawingView brushDrawingView2 = this.brushDrawingView;
        return Boolean.valueOf(brushDrawingView2 != null && brushDrawingView2.getBrushDrawingMode());
    }

    public void setBrushSize(float f) {
        BrushDrawingView brushDrawingView2 = this.brushDrawingView;
        if (brushDrawingView2 != null) {
            brushDrawingView2.setBrushSize(f);
        }
    }

    public void setOpacity(int i) {
        BrushDrawingView brushDrawingView2 = this.brushDrawingView;
        if (brushDrawingView2 != null) {
            brushDrawingView2.setOpacity((int) ((((double) i) / 100.0d) * 255.0d));
        }
    }

    public void setBrushColor(int i) {
        BrushDrawingView brushDrawingView2 = this.brushDrawingView;
        if (brushDrawingView2 != null) {
            brushDrawingView2.setBrushColor(i);
        }
    }

    public void setBrushEraserSize(float f) {
        BrushDrawingView brushDrawingView2 = this.brushDrawingView;
        if (brushDrawingView2 != null) {
            brushDrawingView2.setBrushEraserSize(f);
        }
    }

    public float getEraserSize() {
        BrushDrawingView brushDrawingView2 = this.brushDrawingView;
        if (brushDrawingView2 != null) {
            return brushDrawingView2.getEraserSize();
        }
        return 0.0f;
    }

    public float getBrushSize() {
        BrushDrawingView brushDrawingView2 = this.brushDrawingView;
        if (brushDrawingView2 != null) {
            return brushDrawingView2.getBrushSize();
        }
        return 0.0f;
    }

    public int getBrushColor() {
        BrushDrawingView brushDrawingView2 = this.brushDrawingView;
        if (brushDrawingView2 != null) {
            return brushDrawingView2.getBrushColor();
        }
        return 0;
    }

    public void brushEraser() {
        BrushDrawingView brushDrawingView2 = this.brushDrawingView;
        if (brushDrawingView2 != null) {
            brushDrawingView2.brushEraser();
        }
    }

    public void viewUndo(View view, ViewType viewType) {
        if (this.addedViews.size() > 0 && this.addedViews.contains(view)) {
            this.parentView.removeView(view);
            this.addedViews.remove(view);
            this.redoViews.add(view);
            OnPhotoEditorListener onPhotoEditorListener = this.mOnPhotoEditorListener;
            if (onPhotoEditorListener != null) {
                onPhotoEditorListener.onRemoveViewListener(this.addedViews.size());
                this.mOnPhotoEditorListener.onRemoveViewListener(viewType, this.addedViews.size());
            }
        }
    }

    public boolean undo() {
        if (this.addedViews.size() > 0) {
            List<View> list = this.addedViews;
            View view = list.get(list.size() - 1);
            if (view instanceof BrushDrawingView) {
                BrushDrawingView brushDrawingView2 = this.brushDrawingView;
                if (brushDrawingView2 == null || !brushDrawingView2.undo()) {
                    return false;
                }
                return true;
            }
            List<View> list2 = this.addedViews;
            list2.remove(list2.size() - 1);
            this.parentView.removeView(view);
            this.redoViews.add(view);
            OnPhotoEditorListener onPhotoEditorListener = this.mOnPhotoEditorListener;
            if (onPhotoEditorListener != null) {
                onPhotoEditorListener.onRemoveViewListener(this.addedViews.size());
                Object tag = view.getTag();
                if (tag != null && (tag instanceof ViewType)) {
                    this.mOnPhotoEditorListener.onRemoveViewListener((ViewType) tag, this.addedViews.size());
                }
            }
        }
        if (this.addedViews.size() != 0) {
            return true;
        }
        return false;
    }

    public boolean redo() {
        if (this.redoViews.size() > 0) {
            List<View> list = this.redoViews;
            View view = list.get(list.size() - 1);
            if (view instanceof BrushDrawingView) {
                BrushDrawingView brushDrawingView2 = this.brushDrawingView;
                if (brushDrawingView2 == null || !brushDrawingView2.redo()) {
                    return false;
                }
                return true;
            }
            List<View> list2 = this.redoViews;
            list2.remove(list2.size() - 1);
            this.parentView.addView(view);
            this.addedViews.add(view);
            Object tag = view.getTag();
            OnPhotoEditorListener onPhotoEditorListener = this.mOnPhotoEditorListener;
            if (!(onPhotoEditorListener == null || tag == null || !(tag instanceof ViewType))) {
                onPhotoEditorListener.onAddViewListener((ViewType) tag, this.addedViews.size());
            }
        }
        if (this.redoViews.size() != 0) {
            return true;
        }
        return false;
    }

    private void clearBrushAllViews() {
        BrushDrawingView brushDrawingView2 = this.brushDrawingView;
        if (brushDrawingView2 != null) {
            brushDrawingView2.clearAll();
        }
    }

    public void clearAllViews() {
        for (int i = 0; i < this.addedViews.size(); i++) {
            this.parentView.removeView(this.addedViews.get(i));
        }
        if (this.addedViews.contains(this.brushDrawingView)) {
            this.parentView.addView(this.brushDrawingView);
        }
        this.addedViews.clear();
        this.redoViews.clear();
        clearBrushAllViews();
    }

    public void clearHelperBox() {
        for (int i = 0; i < this.parentView.getChildCount(); i++) {
            View childAt = this.parentView.getChildAt(i);
            FrameLayout frameLayout = (FrameLayout) childAt.findViewById(R.id.frmBorder);
            if (frameLayout != null) {
                frameLayout.setBackgroundResource(0);
                View findViewById = childAt.findViewById(R.id.imgPhotoEditorClose);
                if (findViewById != null) {
                    findViewById.setVisibility(View.GONE);
                }
                View findViewById2 = childAt.findViewById(R.id.imgPhotoEditorZoom);
                if (findViewById2 != null) {
                    findViewById2.setVisibility(View.GONE);
                }
            }
        }
    }

    public void setFilterEffect(CustomEffect customEffect) {
        this.parentView.setFilterEffect(customEffect);
    }

    public void setFilterEffect(PhotoFilter photoFilter) {
        this.parentView.setFilterEffect(photoFilter);
    }

    public void saveImage(String str, OnSaveListener onSaveListener) {
        saveAsFile(str, onSaveListener);
    }

    public void saveAsFile(String str, OnSaveListener onSaveListener) {
        saveAsFile(str, new SaveSettings.Builder().build(), onSaveListener);
    }

    public void saveAsFile(final String str, final SaveSettings saveSettings, final OnSaveListener onSaveListener) {
        Log.d(TAG, "Image Path: " + str);
        this.parentView.saveFilter(new OnSaveBitmap() {
            /* class com.kessi.textarts.photoeditor.PhotoEditor.AnonymousClass9 */

            @Override // com.kessi.textarts.photoeditor.OnSaveBitmap
            public void onBitmapReady(Bitmap bitmap) {
                new AsyncTask<String, String, Exception>() {
                    /* class com.kessi.textarts.photoeditor.PhotoEditor.AnonymousClass9.AnonymousClass1 */

                    public void onPreExecute() {
                        super.onPreExecute();
                        PhotoEditor.this.clearHelperBox();
                        PhotoEditor.this.parentView.setDrawingCacheEnabled(false);
                    }

                    public Exception doInBackground(String... strArr) {
                        Bitmap bitmap;
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(new File(str), false);
                            if (PhotoEditor.this.parentView != null) {
                                PhotoEditor.this.parentView.setDrawingCacheEnabled(true);
                                if (saveSettings.isTransparencyEnabled()) {
                                    bitmap = BitmapUtil.removeTransparency(PhotoEditor.this.parentView.getDrawingCache());
                                } else {
                                    bitmap = PhotoEditor.this.parentView.getDrawingCache();
                                }
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                            }
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            Log.d(PhotoEditor.TAG, "Filed Saved Successfully");
                            return null;
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d(PhotoEditor.TAG, "Failed to save File");
                            return e;
                        }
                    }

                    public void onPostExecute(Exception exc) {
                        super.onPostExecute((Exception) exc);
                        if (exc == null) {
                            if (saveSettings.isClearViewsEnabled()) {
                                PhotoEditor.this.clearAllViews();
                            }
                            onSaveListener.onSuccess(str);
                            return;
                        }
                        onSaveListener.onFailure(exc);
                    }
                }.execute(new String[0]);
            }

            @Override // com.kessi.textarts.photoeditor.OnSaveBitmap
            public void onFailure(Exception exc) {
                onSaveListener.onFailure(exc);
            }
        });
    }

    public void saveAsBitmap(OnSaveBitmap onSaveBitmap) {
        saveAsBitmap(new SaveSettings.Builder().build(), onSaveBitmap);
    }

    public void saveAsBitmap(final SaveSettings saveSettings, final OnSaveBitmap onSaveBitmap) {
        this.parentView.saveFilter(new OnSaveBitmap() {
            /* class com.kessi.textarts.photoeditor.PhotoEditor.AnonymousClass10 */

            @Override // com.kessi.textarts.photoeditor.OnSaveBitmap
            public void onBitmapReady(Bitmap bitmap) {
                new AsyncTask<String, String, Bitmap>() {
                    /* class com.kessi.textarts.photoeditor.PhotoEditor.AnonymousClass10.AnonymousClass1 */

                    public void onPreExecute() {
                        super.onPreExecute();
                        PhotoEditor.this.clearHelperBox();
                        PhotoEditor.this.parentView.setDrawingCacheEnabled(false);
                    }

                    public Bitmap doInBackground(String... strArr) {
                        if (PhotoEditor.this.parentView == null) {
                            return null;
                        }
                        PhotoEditor.this.parentView.setDrawingCacheEnabled(true);
                        if (saveSettings.isTransparencyEnabled()) {
                            return BitmapUtil.removeTransparency(PhotoEditor.this.parentView.getDrawingCache());
                        }
                        return PhotoEditor.this.parentView.getDrawingCache();
                    }

                    public void onPostExecute(Bitmap bitmap) {
                        super.onPostExecute((Bitmap) bitmap);
                        if (bitmap != null) {
                            if (saveSettings.isClearViewsEnabled()) {
                                PhotoEditor.this.clearAllViews();
                            }
                            onSaveBitmap.onBitmapReady(bitmap);
                            return;
                        }
                        onSaveBitmap.onFailure(new Exception("Failed to load the bitmap"));
                    }
                }.execute(new String[0]);
            }

            @Override // com.kessi.textarts.photoeditor.OnSaveBitmap
            public void onFailure(Exception exc) {
                onSaveBitmap.onFailure(exc);
            }
        });
    }

    private static String convertEmoji(String str) {
        try {
            return new String(Character.toChars(Integer.parseInt(str.substring(2), 16)));
        } catch (NumberFormatException unused) {
            return "";
        }
    }

    public void setOnPhotoEditorListener(OnPhotoEditorListener onPhotoEditorListener) {
        this.mOnPhotoEditorListener = onPhotoEditorListener;
    }

    public boolean isCacheEmpty() {
        return this.addedViews.size() == 0 && this.redoViews.size() == 0;
    }

    @Override // com.kessi.textarts.photoeditor.BrushViewChangeListener
    public void onViewAdd(BrushDrawingView brushDrawingView2) {
        if (this.redoViews.size() > 0) {
            List<View> list = this.redoViews;
            list.remove(list.size() - 1);
        }
        this.addedViews.add(brushDrawingView2);
        OnPhotoEditorListener onPhotoEditorListener = this.mOnPhotoEditorListener;
        if (onPhotoEditorListener != null) {
            onPhotoEditorListener.onAddViewListener(ViewType.BRUSH_DRAWING, this.addedViews.size());
        }
    }

    @Override // com.kessi.textarts.photoeditor.BrushViewChangeListener
    public void onViewRemoved(BrushDrawingView brushDrawingView2) {
        if (this.addedViews.size() > 0) {
            List<View> list = this.addedViews;
            View remove = list.remove(list.size() - 1);
            if (!(remove instanceof BrushDrawingView)) {
                this.parentView.removeView(remove);
            }
            this.redoViews.add(remove);
        }
        OnPhotoEditorListener onPhotoEditorListener = this.mOnPhotoEditorListener;
        if (onPhotoEditorListener != null) {
            onPhotoEditorListener.onRemoveViewListener(this.addedViews.size());
            this.mOnPhotoEditorListener.onRemoveViewListener(ViewType.BRUSH_DRAWING, this.addedViews.size());
        }
    }

    @Override // com.kessi.textarts.photoeditor.BrushViewChangeListener
    public void onStartDrawing() {
        OnPhotoEditorListener onPhotoEditorListener = this.mOnPhotoEditorListener;
        if (onPhotoEditorListener != null) {
            onPhotoEditorListener.onStartViewChangeListener(ViewType.BRUSH_DRAWING);
        }
    }

    @Override // com.kessi.textarts.photoeditor.BrushViewChangeListener
    public void onStopDrawing() {
        OnPhotoEditorListener onPhotoEditorListener = this.mOnPhotoEditorListener;
        if (onPhotoEditorListener != null) {
            onPhotoEditorListener.onStopViewChangeListener(ViewType.BRUSH_DRAWING);
        }
    }

    public static class Builder {
        public View alignView;
        public BrushDrawingView brushDrawingView;
        public Context context;
        public View deleteView;
        public Typeface emojiTypeface;
        public ImageView imageView;
        public boolean isTextPinchZoomable = true;
        public PhotoEditorView parentView;
        public Typeface textTypeface;
        public View zoomView;

        public Builder(Context context2, PhotoEditorView photoEditorView) {
            this.context = context2;
            this.parentView = photoEditorView;
            this.imageView = photoEditorView.getSource();
            this.brushDrawingView = photoEditorView.getBrushDrawingView();
        }

        public Builder setDefaultTextTypeface(Typeface typeface) {
            this.textTypeface = typeface;
            return this;
        }

        public Builder setDefaultEmojiTypeface(Typeface typeface) {
            this.emojiTypeface = typeface;
            return this;
        }

        public Builder setPinchTextScalable(boolean z) {
            this.isTextPinchZoomable = z;
            return this;
        }

        public PhotoEditor build() {
            return new PhotoEditor(this);
        }
    }

    public static List<String> getEmojis(Context context2) {
        ArrayList arrayList = new ArrayList();
        for (String str : context2.getResources().getStringArray(R.array.photo_editor_emoji)) {
            arrayList.add(convertEmoji(str));
        }
        return arrayList;
    }
}
