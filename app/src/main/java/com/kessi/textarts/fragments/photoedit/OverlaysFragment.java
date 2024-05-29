package com.kessi.textarts.fragments.photoedit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.kessi.textarts.R;
import com.kessi.textarts.interfaces.OverlaysFragmentListener;

public class OverlaysFragment extends Fragment implements View.OnClickListener {
    LinearLayout icChristmas;
    LinearLayout icEmoticons;
    LinearLayout icFitness;
    LinearLayout icFood;
    LinearLayout icGeometry;
    LinearLayout icHalloween;
    LinearLayout icLove;
    LinearLayout icMotivation;
    LinearLayout icNative;
    LinearLayout icPhrases;
    LinearLayout icSayings;
    LinearLayout icSummer;
    LinearLayout icTravel;
    LinearLayout icWinter;
    OverlaysFragmentListener overlaysFragmentListener;

    public void setListener(OverlaysFragmentListener overlaysFragmentListener2) {
        this.overlaysFragmentListener = overlaysFragmentListener2;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_image_overlays, viewGroup, false);
        this.icPhrases =  inflate.findViewById(R.id.ic_phrases);
        this.icFood =  inflate.findViewById(R.id.ic_food);
        this.icLove =  inflate.findViewById(R.id.ic_love);
        this.icChristmas =  inflate.findViewById(R.id.ic_christmas);
        this.icSayings =  inflate.findViewById(R.id.ic_sayings);
        this.icNative =  inflate.findViewById(R.id.ic_native);
        this.icSummer =  inflate.findViewById(R.id.ic_summer);
        this.icWinter =  inflate.findViewById(R.id.ic_winter);
        this.icTravel =  inflate.findViewById(R.id.ic_travel);
        this.icEmoticons =  inflate.findViewById(R.id.ic_emoticons);
        this.icMotivation =  inflate.findViewById(R.id.ic_motivation);
        this.icFitness =  inflate.findViewById(R.id.ic_fitness);
        this.icGeometry =  inflate.findViewById(R.id.ic_geometry);
        this.icHalloween =  inflate.findViewById(R.id.ic_halloween);
        this.icPhrases.setOnClickListener(this);
        this.icFood.setOnClickListener(this);
        this.icLove.setOnClickListener(this);
        this.icChristmas.setOnClickListener(this);
        this.icSayings.setOnClickListener(this);
        this.icNative.setOnClickListener(this);
        this.icSummer.setOnClickListener(this);
        this.icWinter.setOnClickListener(this);
        this.icTravel.setOnClickListener(this);
        this.icEmoticons.setOnClickListener(this);
        this.icMotivation.setOnClickListener(this);
        this.icFitness.setOnClickListener(this);
        this.icGeometry.setOnClickListener(this);
        this.icHalloween.setOnClickListener(this);
        return inflate;
    }

    public void onClick(View view) {
        if (this.overlaysFragmentListener != null) {
            if (view.getId() == R.id.ic_christmas) {
                this.overlaysFragmentListener.onChristmas();
            } else if (view.getId() == R.id.ic_emoticons) {
                this.overlaysFragmentListener.onEmoticons();
            } else if (view.getId() == R.id.ic_fitness) {
                this.overlaysFragmentListener.onFitness();
            } else if (view.getId() == R.id.ic_food) {
                this.overlaysFragmentListener.onFood();
            } else if (view.getId() == R.id.ic_geometry) {
                this.overlaysFragmentListener.onGeometry();
            } else if (view.getId() == R.id.ic_halloween) {
                this.overlaysFragmentListener.onHalloween();
            } else if (view.getId() == R.id.ic_love) {
                this.overlaysFragmentListener.onLove();
            } else if (view.getId() == R.id.ic_motivation) {
                this.overlaysFragmentListener.onMotivation();
            } else if (view.getId() == R.id.ic_native) {
                this.overlaysFragmentListener.onNative();
            } else if (view.getId() == R.id.ic_phrases) {
                this.overlaysFragmentListener.onPhrases();
            } else if (view.getId() == R.id.ic_sayings) {
                this.overlaysFragmentListener.onSayings();
            } else if (view.getId() == R.id.ic_summer) {
                this.overlaysFragmentListener.onSummer();
            } else if (view.getId() == R.id.ic_travel) {
                this.overlaysFragmentListener.onTravel();
            } else if (view.getId() == R.id.ic_winter) {
                this.overlaysFragmentListener.onWinter();
            }
        }
    }
}
