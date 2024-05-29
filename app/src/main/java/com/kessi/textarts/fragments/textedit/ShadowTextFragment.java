package com.kessi.textarts.fragments.textedit;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.kessi.textarts.R;
import com.kessi.textarts.adapter.ColorAdapter;
import com.kessi.textarts.interfaces.ShadowFragmentListener;
import com.kessi.textarts.photoeditor.RoundFrameLayout;
import java.util.Objects;

public class ShadowTextFragment extends Fragment implements ColorAdapter.ColorAdapterListener {
    RoundFrameLayout btnPickerColorShadow;
    SeekBar sbDRadiusShadow;
    SeekBar sbDyShadow;
    ShadowFragmentListener shadowFragmentListener;


    public void setListener(ShadowFragmentListener shadowFragmentListener2) {
        this.shadowFragmentListener = shadowFragmentListener2;
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_text_shadow, viewGroup, false);
        this.sbDyShadow = (SeekBar) inflate.findViewById(R.id.sb_Dy_shadow);
        this.sbDRadiusShadow = (SeekBar) inflate.findViewById(R.id.sb_dRadius_shadow);
        btnPickerColorShadow= (RoundFrameLayout) inflate.findViewById(R.id.btn_picker_color_shadow);
        btnPickerColorShadow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder.with((Context) Objects.requireNonNull(getActivity())).setTitle("Select color").wheelType(ColorPickerView.WHEEL_TYPE.FLOWER).density(12).setPositiveButton("OK", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int i, Integer[] allColors) {
                        if (shadowFragmentListener != null) {
                            shadowFragmentListener.onShadowColorSelected(i);
                            btnPickerColorShadow.getDelegate().setBackgroundColor(i);
                            btnPickerColorShadow.getDelegate().setStrokeColor(ContextCompat.getColor((Context) Objects.requireNonNull(getContext()), R.color.icChecked));
                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).build().show();

            }
        });
        this.sbDRadiusShadow.setMax(100);
        this.sbDRadiusShadow.setProgress(0);
        this.sbDRadiusShadow.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (ShadowTextFragment.this.shadowFragmentListener != null) {
                    ShadowTextFragment.this.shadowFragmentListener.onRadiusChangeListener(i / 10);
                }
            }
        });
        this.sbDyShadow.setMax(150);
        this.sbDyShadow.setProgress(0);
        this.sbDyShadow.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (ShadowTextFragment.this.shadowFragmentListener != null) {
                    ShadowTextFragment.this.shadowFragmentListener.onDyShadowChangeListener(i / 10);
                }
            }
        });
        return inflate;
    }


    @Override 
    public void onColorItemSelected(int i) {
        if (shadowFragmentListener != null) {
            shadowFragmentListener.onShadowColorSelected(i);
        }
    }
}
