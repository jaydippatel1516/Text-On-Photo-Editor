package com.kessi.textarts.fragments.textedit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.fragment.app.Fragment;

import com.kessi.textarts.R;
import com.kessi.textarts.interfaces.FormatTextFragmentListener;
import com.kessi.textarts.views.ToggleImageButton;

public class FormatTextFragment extends Fragment implements View.OnClickListener {
    ImageView btnAlignCenter;
    ImageView btnAlignLeft;
    ImageView btnAlignRight;
    ToggleImageButton btnAllCaps;
    ToggleImageButton btnBold;
    ToggleImageButton btnItalic;
    FormatTextFragmentListener formatTextFragmentListener;
    SeekBar sbPadding;
    SeekBar seekbarTextSize;

    public void setListener(FormatTextFragmentListener formatTextFragmentListener2) {
        this.formatTextFragmentListener = formatTextFragmentListener2;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_text_format, viewGroup, false);
        this.btnAlignLeft = (ImageView) inflate.findViewById(R.id.btn_align_left);
        this.btnAlignCenter = (ImageView) inflate.findViewById(R.id.btn_align_center);
        this.btnAlignRight = (ImageView) inflate.findViewById(R.id.btn_align_right);
        this.btnAlignLeft.setOnClickListener(this);
        this.btnAlignRight.setOnClickListener(this);
        this.btnAlignCenter.setOnClickListener(this);
        this.btnBold = (ToggleImageButton) inflate.findViewById(R.id.btn_bold);
        this.btnItalic = (ToggleImageButton) inflate.findViewById(R.id.btn_italic);
        this.btnAllCaps = (ToggleImageButton) inflate.findViewById(R.id.btn_all_caps);
        seekbarTextSize = (SeekBar) inflate.findViewById(R.id.seebar_text_size);
        seekbarTextSize.setMax(60);
        this.seekbarTextSize.setProgress(15);
        this.seekbarTextSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (FormatTextFragment.this.formatTextFragmentListener != null) {
                    FormatTextFragment.this.formatTextFragmentListener.onTextSize(i);
                }
            }
        });
        sbPadding = (SeekBar) inflate.findViewById(R.id.sbPadding);
        sbPadding.setMax(100);
        this.sbPadding.setProgress(5);
        this.sbPadding.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (FormatTextFragment.this.formatTextFragmentListener != null) {
                    FormatTextFragment.this.formatTextFragmentListener.onTextPadding(i);
                }
            }
        });
        this.btnBold.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean z) {
                if (formatTextFragmentListener != null) {
                    if (z) {
                        if (btnItalic.isChecked()) {
                            formatTextFragmentListener.onTextStyle(1);
                        } else {
                            formatTextFragmentListener.onTextStyle(2);
                        }
                    } else if (btnItalic.isChecked()) {
                        formatTextFragmentListener.onTextStyle(3);
                    } else {
                        formatTextFragmentListener.onTextStyle(4);
                    }
                }
            }
        });
        this.btnItalic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean z) {
                if (formatTextFragmentListener != null) {
                    if (z) {
                        if (btnBold.isChecked()) {
                            formatTextFragmentListener.onTextStyle(1);
                        } else if (!btnBold.isChecked()) {
                            formatTextFragmentListener.onTextStyle(3);
                        }
                    } else if (btnBold.isChecked()) {
                        formatTextFragmentListener.onTextStyle(2);
                    } else if (!btnBold.isChecked()) {
                        formatTextFragmentListener.onTextStyle(4);
                    }
                }
            }
        });
        this.btnAllCaps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean z) {
                if (formatTextFragmentListener != null) {
                    if (z) {
                        formatTextFragmentListener.onTextStyle(5);
                    } else {
                        formatTextFragmentListener.onTextStyle(6);
                    }
                }
            }
        });
        return inflate;
    }


    public void onClick(View view) {
        if (this.formatTextFragmentListener != null) {
            if (view.getId() == R.id.btn_align_center) {
                this.formatTextFragmentListener.onTextAlign(2);

            } else if (view.getId() == R.id.btn_align_left) {
                this.formatTextFragmentListener.onTextAlign(1);

            } else if (view.getId() == R.id.btn_align_right) {
                this.formatTextFragmentListener.onTextAlign(3);

            }

        }
    }
}
