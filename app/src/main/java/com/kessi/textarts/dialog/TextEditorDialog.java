package com.kessi.textarts.dialog;

import android.app.Dialog;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.kessi.textarts.R;
import com.kessi.textarts.activities.TextArtActivity;
import com.kessi.textarts.fragments.TextEditorFragment;
import com.kessi.textarts.fragments.ThoughtsFragment;

import java.util.Random;

public class TextEditorDialog extends Dialog implements View.OnClickListener, ThoughtsFragment.ThoughtsFragmentListener {
    TextArtActivity editPhotoActivity;
    EditText editText;
    TextEditorFragment.TextFragmentListener textFragmentListener;
    int[] thoughts = {R.string.thought1, R.string.thought2, R.string.thought3, R.string.thought4, R.string.thought5, R.string.thought6, R.string.thought7, R.string.thought8, R.string.thought9, R.string.thought10, R.string.thought11, R.string.thought12, R.string.thought13, R.string.thought14, R.string.thought15, R.string.thought16, R.string.thought17, R.string.thought18, R.string.thought19, R.string.thought20, R.string.thought21, R.string.thought22, R.string.thought23, R.string.thought24, R.string.thought25, R.string.thought26, R.string.thought27, R.string.thought28, R.string.thought29, R.string.thought30, R.string.thought31, R.string.thought32, R.string.thought33, R.string.thought34, R.string.thought35, R.string.thought36, R.string.thought37, R.string.thought38, R.string.thought39, R.string.thought40, R.string.thought41, R.string.thought42, R.string.thought43, R.string.thought44, R.string.thought45, R.string.thought46, R.string.thought47, R.string.thought48, R.string.thought49, R.string.thought50, R.string.thought51, R.string.thought52, R.string.thought53, R.string.thought54, R.string.thought55, R.string.thought56, R.string.thought57, R.string.thought58, R.string.thought59, R.string.thought60, R.string.thought61, R.string.thought62, R.string.thought63, R.string.thought64, R.string.thought65, R.string.thought66, R.string.thought67, R.string.thought68, R.string.thought69, R.string.thought70, R.string.thought71, R.string.thought72, R.string.thought73, R.string.thought74, R.string.thought75, R.string.thought76, R.string.thought77, R.string.thought78, R.string.thought79, R.string.thought80, R.string.thought81, R.string.thought82, R.string.thought83, R.string.thought84, R.string.thought85, R.string.thought86, R.string.thought87, R.string.thought88, R.string.thought89, R.string.thought90};

    public void setTextListener(TextEditorFragment.TextFragmentListener textFragmentListener2) {
        this.textFragmentListener = textFragmentListener2;
    }

    public TextEditorDialog(TextArtActivity textArtActivity, String str) {
        super(textArtActivity);
        this.editPhotoActivity = textArtActivity;
        requestWindowFeature(1);
        setContentView(R.layout.fragment_text_editor);
        getWindow().setLayout(-1, -2);
        findViewById(R.id.btnCancel).setOnClickListener(this);
        findViewById(R.id.btnDone).setOnClickListener(this);
        this.editText = (EditText) findViewById(R.id.edtQuotes);
        findViewById(R.id.btnRandom).setOnClickListener(this);
        findViewById(R.id.btnMoreQuotes).setOnClickListener(this);
        if (!str.equals(textArtActivity.getString(R.string.double_tap))) {
            this.editText.setText(str);
        }
        if (this.editText.requestFocus()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ((InputMethodManager) textArtActivity.getSystemService("input_method")).showSoftInput(editText, 1);

                }
            }, 200);
        }
    }


    public void onClick(View view) {
        if (view.getId() == R.id.btnCancel) {
            this.editText.setText("");
        } else if (view.getId() == R.id.btnDone) {
            String obj = this.editText.getText().toString();
            if (obj.equals("") || obj.isEmpty()) {
                dismiss();
                return;
            }
            TextEditorFragment.TextFragmentListener textFragmentListener2 = this.textFragmentListener;
            if (textFragmentListener2 != null) {
                textFragmentListener2.onText(obj);
                dismiss();
                return;
            }
        } else if (view.getId() == R.id.btnMoreQuotes) {
            dismiss();
            ThoughtsFragment thoughtsFragment = new ThoughtsFragment();
            thoughtsFragment.setThoughtListener(this);
            this.editPhotoActivity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right).add(R.id.frameLayoutEditMai, thoughtsFragment, "QUOTES").addToBackStack("QUOTES").commit();
            View currentFocus = this.editPhotoActivity.getCurrentFocus();
            if (currentFocus != null) {
                ((InputMethodManager) this.editPhotoActivity.getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
                return;
            }
        } else if (view.getId() == R.id.btnRandom) {
            this.editText.setText(this.thoughts[new Random().nextInt(this.thoughts.length)]);

        }

    }

    @Override
    public void onThought(int i) {
        this.editText.setText(i);
        show();
    }
}
