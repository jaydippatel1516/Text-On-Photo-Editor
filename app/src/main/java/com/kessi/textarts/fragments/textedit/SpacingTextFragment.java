package com.kessi.textarts.fragments.textedit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import androidx.fragment.app.Fragment;
import com.kessi.textarts.R;
import com.kessi.textarts.interfaces.SpacingFragmentListener;

public class SpacingTextFragment extends Fragment {
    SeekBar sbLetterSpacing;
    SeekBar sbLineHeight;
    SpacingFragmentListener spacingFragmentListener;

    public float getConvertedValue(int i) {
        return (((float) i) * 0.5f) / 10.0f;
    }

    public void setListener(SpacingFragmentListener spacingFragmentListener2) {
        this.spacingFragmentListener = spacingFragmentListener2;
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_text_spacing, viewGroup, false);
        this.sbLetterSpacing = (SeekBar) inflate.findViewById(R.id.sbLetterSpacing);
        this.sbLineHeight = (SeekBar) inflate.findViewById(R.id.sbLineHeight);
        this.sbLetterSpacing.setMax(90);
        this.sbLetterSpacing.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (SpacingTextFragment.this.spacingFragmentListener != null) {
                    SpacingTextFragment.this.spacingFragmentListener.onSpacingLetter(SpacingTextFragment.this.getConvertedValue(i));
                }
            }
        });
        this.sbLineHeight.setMax(250);
        this.sbLineHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (SpacingTextFragment.this.spacingFragmentListener != null) {
                    SpacingTextFragment.this.spacingFragmentListener.onLineHeight(i);
                }
            }
        });
        return inflate;
    }
}
