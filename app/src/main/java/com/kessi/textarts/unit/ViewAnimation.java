package com.kessi.textarts.unit;

import android.animation.Animator;
import android.view.View;
import android.view.ViewAnimationUtils;

public class ViewAnimation {
    public static void animationView(View view) {
        int width = view.getWidth() / 2;
        int height = view.getHeight() / 2;
        Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(view, width, height, 0.0f, (float) Math.hypot((double) width, (double) height));
        view.setVisibility(View.VISIBLE);
        createCircularReveal.start();
    }
}
