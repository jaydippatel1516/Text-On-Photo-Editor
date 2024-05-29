package com.kessi.textarts.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.kessi.textarts.R;
import com.kessi.textarts.adapter.ColorAdapter;
import com.kessi.textarts.interfaces.ColorFragmentListener;
import com.kessi.textarts.photoeditor.RoundFrameLayout;
import java.util.Objects;

public class ColorFragment extends Fragment implements ColorAdapter.ColorAdapterListener {
    ColorAdapter colorAdapter;
    ColorFragmentListener colorFragmentListener;
    RecyclerView recyclerView;
    RoundFrameLayout roundFrameLayout;
    SeekBar seekBar;


    public void setListener(ColorFragmentListener colorFragmentListener2) {
        this.colorFragmentListener = colorFragmentListener2;
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_text_color, viewGroup, false);
        this.seekBar = (SeekBar) inflate.findViewById(R.id.sbTranparencyText);
        recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_color_text);
        recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        colorAdapter = new ColorAdapter(getActivity(), this);
        this.recyclerView.setAdapter(colorAdapter);
        roundFrameLayout = (RoundFrameLayout) inflate.findViewById(R.id.btn_picker_color_text);
        roundFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder.with((Context) Objects.requireNonNull(getActivity())).setTitle("Select color").wheelType(ColorPickerView.WHEEL_TYPE.FLOWER).density(12).setPositiveButton("OK", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int i, Integer[] allColors) {
                        if (colorFragmentListener != null) {
                            colorFragmentListener.onColorSelected(i);
                            roundFrameLayout.getDelegate().setBackgroundColor(i);
                            roundFrameLayout.getDelegate().setStrokeColor(ContextCompat.getColor((Context) Objects.requireNonNull(getContext()), R.color.icChecked));
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
        this.seekBar.setMax(255);
        this.seekBar.setProgress(255);
        this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (ColorFragment.this.colorFragmentListener != null) {
                    ColorFragment.this.colorFragmentListener.onColorOpacityChangeListerner(i);
                }
            }
        });
        return inflate;
    }


    @Override 
    public void onColorItemSelected(int i) {
        ColorFragmentListener colorFragmentListener2 = this.colorFragmentListener;
        if (colorFragmentListener2 != null) {
            colorFragmentListener2.onColorSelected(i);
        }
    }
}
