package com.kessi.textarts.fragments.photoedit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import com.google.android.material.tabs.TabLayout;
import com.kessi.textarts.R;
import com.kessi.textarts.views.CustomSeekBar;
import java.util.Objects;

public class TuneFragment extends Fragment {
    public int currentBright;
    public int currentContrast;
    public int currentHue;
    public int currentSaturation;
    public CustomSeekBar seekBar;
    TabLayout tabLayout;
    public int tabSelected = 0;
    TuneFragmentListener tuneFragmentListener;
    public TextView tvProgress;

    public interface TuneFragmentListener {
        void onBrightnessChosse(int i);

        void onConstrastChosse(int i);

        void onHueChosee(int i);

        void onSaturationChosse(int i);
    }

    public void setTuneFragmentListener(TuneFragmentListener tuneFragmentListener2) {
        this.tuneFragmentListener = tuneFragmentListener2;
    }

    @Override 
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        TabLayout tabLayout2 = this.tabLayout;
        if (tabLayout2 != null) {
            bundle.putInt("selected", tabLayout2.getSelectedTabPosition());
        }
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_image_tune, viewGroup, false);
        this.seekBar = (CustomSeekBar) inflate.findViewById(R.id.seekbar_tune);
        this.tvProgress = (TextView) inflate.findViewById(R.id.tvTuneProgress);
        this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (TuneFragment.this.tuneFragmentListener == null) {
                    return;
                }
                if (TuneFragment.this.tabSelected == 0) {
                    TuneFragment.this.currentBright = i - 50;
                    TuneFragment.this.tvProgress.setText(String.valueOf(TuneFragment.this.currentBright));
                    TuneFragment.this.tuneFragmentListener.onBrightnessChosse(TuneFragment.this.currentBright);
                } else if (TuneFragment.this.tabSelected == 1) {
                    int i2 = i - 50;
                    TuneFragment.this.currentContrast = i2;
                    TuneFragment.this.tvProgress.setText(String.valueOf(i2));
                    TuneFragment.this.tuneFragmentListener.onConstrastChosse(i2);
                } else if (TuneFragment.this.tabSelected == 2) {
                    int i3 = i - 50;
                    TuneFragment.this.currentHue = i3;
                    TuneFragment.this.tvProgress.setText(String.valueOf(i3));
                    TuneFragment.this.tuneFragmentListener.onHueChosee(i3);
                } else {
                    int i4 = i - 50;
                    TuneFragment.this.currentSaturation = i4;
                    TuneFragment.this.tvProgress.setText(String.valueOf(i4));
                    TuneFragment.this.tuneFragmentListener.onSaturationChosse(i4);
                }
            }
        });
        TabLayout tabLayout2 = (TabLayout) inflate.findViewById(R.id.tablayoutTune);
        this.tabLayout = tabLayout2;
        tabLayout2.addTab(tabLayout2.newTab().setText("Brightness"));
        TabLayout tabLayout3 = this.tabLayout;
        tabLayout3.addTab(tabLayout3.newTab().setText(ExifInterface.TAG_CONTRAST));
        TabLayout tabLayout4 = this.tabLayout;
        tabLayout4.addTab(tabLayout4.newTab().setText("Hue"));
        TabLayout tabLayout5 = this.tabLayout;
        tabLayout5.addTab(tabLayout5.newTab().setText(ExifInterface.TAG_SATURATION));
        ((TabLayout.Tab) Objects.requireNonNull(this.tabLayout.getTabAt(this.tabSelected))).select();
        this.tabLayout.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new TabLayout.OnTabSelectedListener() {

            @Override 
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override 
            public void onTabSelected(TabLayout.Tab tab) {
                TuneFragment.this.tabSelected = tab.getPosition();
                Log.d("XXXXXXXX", "onTabSelected " + TuneFragment.this.tabSelected + " " + TuneFragment.this.currentBright);
                if (TuneFragment.this.tabSelected == 0) {
                    TuneFragment.this.seekBar.setProgress(TuneFragment.this.currentBright + 50);
                } else if (TuneFragment.this.tabSelected == 1) {
                    TuneFragment.this.seekBar.setProgress(TuneFragment.this.currentContrast + 50);
                } else if (TuneFragment.this.tabSelected == 2) {
                    TuneFragment.this.seekBar.setProgress(TuneFragment.this.currentHue + 50);
                } else {
                    TuneFragment.this.seekBar.setProgress(TuneFragment.this.currentSaturation + 50);
                }
            }

            @Override 
            public void onTabReselected(TabLayout.Tab tab) {
                TuneFragment.this.tabSelected = tab.getPosition();
                Log.d("XXXXXXXX", "onTabReselected " + TuneFragment.this.tabSelected + " " + TuneFragment.this.currentBright);
                if (TuneFragment.this.tabSelected == 0) {
                    TuneFragment.this.seekBar.setProgress(TuneFragment.this.currentBright + 50);
                } else if (TuneFragment.this.tabSelected == 1) {
                    TuneFragment.this.seekBar.setProgress(TuneFragment.this.currentContrast + 50);
                } else if (TuneFragment.this.tabSelected == 2) {
                    TuneFragment.this.seekBar.setProgress(TuneFragment.this.currentHue + 50);
                } else {
                    TuneFragment.this.seekBar.setProgress(TuneFragment.this.currentSaturation + 50);
                }
            }
        });
        return inflate;
    }
}
