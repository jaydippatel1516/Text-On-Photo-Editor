package com.kessi.textarts.dialog;

import android.app.Activity;
import android.app.Dialog;
import com.kessi.textarts.R;

public class LoadingDialog extends Dialog {
    Activity ac;

    public LoadingDialog(Activity activity) {
        super(activity);
        this.ac = activity;
        setContentView(R.layout.loading_dialog);
        addControls();
    }

    private void addControls() {
        getWindow().setBackgroundDrawableResource(17170445);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }
}
