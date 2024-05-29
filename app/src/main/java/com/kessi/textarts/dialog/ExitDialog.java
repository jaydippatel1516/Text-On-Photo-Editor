package com.kessi.textarts.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.TextView;

import com.kessi.textarts.R;

public class ExitDialog extends Dialog {
    Activity context;
    TextView exit;
    TextView stay;

    public ExitDialog(Activity activity) {
        super(activity);
        this.context = activity;
        setContentView(R.layout.exit_dialog);
        addControls();
        addEvents();
    }

    private void addEvents() {
        this.exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });
        this.stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void addControls() {
        this.exit = (TextView) findViewById(R.id.tv_discard);
        this.stay = (TextView) findViewById(R.id.tv_keep);
        setCanceledOnTouchOutside(true);
    }
}
