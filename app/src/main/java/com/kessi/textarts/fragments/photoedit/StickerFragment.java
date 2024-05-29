package com.kessi.textarts.fragments.photoedit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.kessi.textarts.R;
import com.kessi.textarts.fragments.photoedit.sticker.StickerViewPagerAdapter;

public class StickerFragment extends Fragment {
    StickerFragmentListener stickerFragmentListener;
    TabLayout tabLayout;
    ViewPager viewPager;

    public interface StickerFragmentListener {
        void onStickerFragmentClick(Bitmap bitmap);
    }

    public void setStickerFragmentListener(StickerFragmentListener stickerFragmentListener2) {
        this.stickerFragmentListener = stickerFragmentListener2;
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_image_sticker, viewGroup, false);
        this.tabLayout = (TabLayout) inflate.findViewById(R.id.tablayoutSticker);
        ViewPager viewPager2 = (ViewPager) inflate.findViewById(R.id.viewpagerSticker);
        this.viewPager = viewPager2;
        viewPager2.setAdapter(new StickerViewPagerAdapter(getChildFragmentManager(), new StickerViewPagerAdapter.StickerViewPagerAdapterListener() {
            @Override
            public void onSticker(int i) {
                if (stickerFragmentListener != null) {
                    stickerFragmentListener.onStickerFragmentClick(BitmapFactory.decodeResource(getResources(), i));
                }
            }
        }));
        this.tabLayout.setupWithViewPager(this.viewPager);
        return inflate;
    }

    
    public  void m4039x8b028987(int i) {

    }
}
