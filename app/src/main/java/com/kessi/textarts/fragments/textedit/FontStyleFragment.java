package com.kessi.textarts.fragments.textedit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.kessi.textarts.R;
import com.kessi.textarts.adapter.FontAdapter;
import com.kessi.textarts.interfaces.FontFragmentListener;
import com.kessi.textarts.views.PickerLayoutManager;
import java.util.ArrayList;
import java.util.List;

public class FontStyleFragment extends Fragment implements FontAdapter.FontAdapterClickListener {
    FontAdapter fontAdapter;
    FontFragmentListener fontFragmentListener;
    List<String> fontList;
    RecyclerView recyclerView;

    @Override
    public void onFontItemSelected(String str) {
    }

    private List<String> loadFontList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("font5.TTF");
        arrayList.add("font5.TTF");
        arrayList.add("font2.ttf");
        arrayList.add("font3.ttf");
        arrayList.add("font4.TTF");
        arrayList.add("font1.ttf");
        arrayList.add("font6.ttf");
        arrayList.add("font7.TTF");
        arrayList.add("font8.TTF");
        arrayList.add("font9.TTF");
        arrayList.add("font10.TTF");
        arrayList.add("font11.otf");
        arrayList.add("font12.ttf");
        arrayList.add("font13.ttf");
        arrayList.add("font14.TTF");
        arrayList.add("font15.TTF");
        arrayList.add("font16.ttf");
        arrayList.add("font17.otf");
        arrayList.add("font18.otf");
        arrayList.add("font19.otf");
        arrayList.add("font20.otf");
        arrayList.add("font21.otf");
        arrayList.add("font22.otf");
        arrayList.add("font23.ttf");
        arrayList.add("font25.otf");
        arrayList.add("font26.otf");
        arrayList.add("font27.otf");
        arrayList.add("font28.ttf");
        arrayList.add("font29.ttf");
        arrayList.add("font30.otf");
        arrayList.add("font31.otf");
        arrayList.add("font32.otf");
        arrayList.add("font33.ttf");
        arrayList.add("font34.ttf");
        arrayList.add("font35.ttf");
        return arrayList;
    }

    public void setListener(FontFragmentListener fontFragmentListener2) {
        this.fontFragmentListener = fontFragmentListener2;
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_font_style, viewGroup, false);
        PickerLayoutManager pickerLayoutManager = new PickerLayoutManager(getActivity(), 0, false);
        pickerLayoutManager.setChangeAlpha(true);
        pickerLayoutManager.setScaleDownBy(0.4f);
        pickerLayoutManager.setScaleDownDistance(0.8f);
        this.fontList = loadFontList();
        recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_font);
        recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        fontAdapter= new FontAdapter(getActivity(), this, this.fontList);
        this.recyclerView.setAdapter(fontAdapter);
        new LinearSnapHelper().attachToRecyclerView(this.recyclerView);
        this.recyclerView.setLayoutManager(pickerLayoutManager);
        pickerLayoutManager.setOnScrollStopListener(new PickerLayoutManager.OnScrollStopListener() {
            @Override
            public void selectedView(int i) {
                if (fontFragmentListener != null) {
                    fontAdapter.currentPos = i;
                    fontAdapter.notifyDataSetChanged();
                    fontFragmentListener.onFontSelected(fontList.get(i));
                }
            }
        });
        return inflate;
    }

}
