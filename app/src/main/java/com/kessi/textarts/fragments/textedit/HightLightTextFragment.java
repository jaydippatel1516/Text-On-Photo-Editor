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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.kessi.textarts.R;
import com.kessi.textarts.adapter.ColorAdapter;
import com.kessi.textarts.interfaces.HightLightFragmentListener;
import com.kessi.textarts.photoeditor.RoundFrameLayout;
import java.util.Objects;

public class HightLightTextFragment extends Fragment implements ColorAdapter.ColorAdapterListener {
    ColorAdapter colorAdapter;
    String e = "28";
    HightLightFragmentListener hightLightFragmentListener;
    RecyclerView recyclerColorHighlight;
    RoundFrameLayout roundFrameLayout;
    SeekBar sbRadius;
    SeekBar sbtranparencyhighlight;

    public String changeprogress(int i) {
        switch (i) {
            case 0:
                return "00";
            case 1:
                return "01";
            case 2:
                return "02";
            case 3:
                return "03";
            case 4:
                return "04";
            case 5:
                return "05";
            case 6:
                return "06";
            case 7:
                return "07";
            case 8:
                return "08";
            case 9:
                return "09";
            case 10:
                return "0A";
            case 11:
                return "0B";
            case 12:
                return "0C";
            case 13:
                return "0D";
            case 14:
                return "0E";
            case 15:
                return "0F";
            default:
                return null;
        }
    }

    public void setListener(HightLightFragmentListener hightLightFragmentListener2) {
        this.hightLightFragmentListener = hightLightFragmentListener2;
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_text_highlight, viewGroup, false);
        this.sbtranparencyhighlight = (SeekBar) inflate.findViewById(R.id.sbTranparencyHighlight);
        this.sbRadius = (SeekBar) inflate.findViewById(R.id.sbRadius);
        recyclerColorHighlight = (RecyclerView) inflate.findViewById(R.id.recycler_color_highlight);
        recyclerColorHighlight.setHasFixedSize(true);
        this.recyclerColorHighlight.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        colorAdapter= new ColorAdapter(getActivity(), this);
        this.recyclerColorHighlight.setAdapter(colorAdapter);
        this.sbtranparencyhighlight.setProgress(0);
        this.sbtranparencyhighlight.setMax(255);
        this.sbtranparencyhighlight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (i < 16) {
                    HightLightTextFragment hightLightTextFragment = HightLightTextFragment.this;
                    hightLightTextFragment.e = hightLightTextFragment.changeprogress(i);
                } else {
                    HightLightTextFragment.this.e = Integer.toHexString(i);
                }
                if (HightLightTextFragment.this.hightLightFragmentListener != null) {
                    HightLightTextFragment.this.hightLightFragmentListener.onHightLightColorOpacityChangeListerner(HightLightTextFragment.this.e);
                }
            }
        });
        this.sbRadius.setMax(100);
        this.sbRadius.setProgress(0);
        this.sbRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (HightLightTextFragment.this.hightLightFragmentListener != null) {
                    HightLightTextFragment.this.hightLightFragmentListener.onHighLightRadius(i);
                }
            }
        });
        roundFrameLayout= (RoundFrameLayout) inflate.findViewById(R.id.btn_picker_color_highlight);
        roundFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder.with((Context) Objects.requireNonNull(getActivity())).setTitle("Select color").wheelType(ColorPickerView.WHEEL_TYPE.FLOWER).density(12).setPositiveButton("OK", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int i, Integer[] allColors) {
                        if (hightLightFragmentListener != null) {
                            hightLightFragmentListener.onHightLightColorSelected(i);
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
        return inflate;
    }
    

    @Override
    public void onColorItemSelected(int i) {
        if (hightLightFragmentListener != null) {
            hightLightFragmentListener.onHightLightColorSelected(i);
        }
    }
}
