package com.kessi.textarts.util;

import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import androidx.core.content.ContextCompat;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static int perRequest = 1;
    public static String[] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
    public static String[] permissionsList13 = {"android.permission.READ_MEDIA_IMAGES"};

    public static void setColorFilter(Drawable drawable, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            drawable.setColorFilter(new BlendModeColorFilter(i, BlendMode.SRC_ATOP));
        } else {
            drawable.setColorFilter(i, PorterDuff.Mode.SRC_ATOP);
        }
    }

    public static File getOutputMediaFile() {
        File file = new File(Environment.getExternalStorageDirectory() + "/Download/TextPhoto");
        if (!file.exists() && !file.mkdirs()) {
            return null;
        }
        return new File(file.getPath() + File.separator + "MI_" + new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date()) + ".jpg");
    }

    public static boolean hasPermissions(Context context, String... strArr) {
        if (strArr != null) {
            for (String str : strArr) {
                if (ContextCompat.checkSelfPermission(context, str) != 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
