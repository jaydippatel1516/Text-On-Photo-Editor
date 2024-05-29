package com.kessi.textarts.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class RectLinear extends LinearLayout {
    public RectLinear(Context context) {
        super(context);
    }

    public RectLinear(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RectLinear(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        setMeasuredDimension(measuredWidth, measuredWidth / 2);
    }
}
