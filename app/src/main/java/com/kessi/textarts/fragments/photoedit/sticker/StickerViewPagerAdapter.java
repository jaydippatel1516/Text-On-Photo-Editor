package com.kessi.textarts.fragments.photoedit.sticker;

import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.kessi.textarts.interfaces.StickerListener;

public class StickerViewPagerAdapter extends FragmentPagerAdapter implements StickerListener {
    private final Fragment[] fragments;
    StickerViewPagerAdapterListener stickerViewPagerAdapterListener;

    public interface StickerViewPagerAdapterListener {
        void onSticker(int i);
    }

    @Override 
    public CharSequence getPageTitle(int i) {
        switch (i) {
            case 0:
                return "Cute";
            case 1:
                return "Love";
            case 2:
                return "Rainbow";
            case 3:
                return "Romantic";
            case 4:
                return "Heart";
            case 5:
                return "Birthday";
            case 6:
                return "Christmas";
            case 7:
                return "Flower";
            default:
                return "";
        }
    }

    public StickerViewPagerAdapter(FragmentManager fragmentManager, StickerViewPagerAdapterListener stickerViewPagerAdapterListener2) {
        super(fragmentManager, 1);
        CuteFragment cuteFragment = new CuteFragment();
        cuteFragment.setStickerListener(this);
        LoveFragment loveFragment = new LoveFragment();
        loveFragment.setStickerListener(this);
        RainbowFragment rainbowFragment = new RainbowFragment();
        rainbowFragment.setStickerListener(this);
        RomanticFragment romanticFragment = new RomanticFragment();
        romanticFragment.setStickerListener(this);
        HeartFragment heartFragment = new HeartFragment();
        heartFragment.setStickerListener(this);
        BirthdayFragment birthdayFragment = new BirthdayFragment();
        birthdayFragment.setStickerListener(this);
        ChristFragment christFragment = new ChristFragment();
        christFragment.setStickerListener(this);
        FlowersFragment flowersFragment = new FlowersFragment();
        flowersFragment.setStickerListener(this);
        this.fragments = new Fragment[]{cuteFragment, loveFragment, rainbowFragment, romanticFragment, heartFragment, birthdayFragment, christFragment, flowersFragment};
        this.stickerViewPagerAdapterListener = stickerViewPagerAdapterListener2;
    }

    @Override 
    public Fragment getItem(int i) {
        return this.fragments[i];
    }

    @Override 
    public int getCount() {
        return this.fragments.length;
    }

    @Override 
    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        super.setPrimaryItem(viewGroup, i, obj);
    }

    @Override
    public void onStickerClick(int i) {
        StickerViewPagerAdapterListener stickerViewPagerAdapterListener2 = this.stickerViewPagerAdapterListener;
        if (stickerViewPagerAdapterListener2 != null) {
            stickerViewPagerAdapterListener2.onSticker(i);
        }
    }
}
