package com.kessi.textarts.util;

import android.app.Activity;
import android.content.Intent;

public class AdManager {


    public static void startActivity(Activity activity, Intent intent, int i) {
        if (intent != null) {
            activity.startActivityForResult(intent, i);
        }
    }

}
