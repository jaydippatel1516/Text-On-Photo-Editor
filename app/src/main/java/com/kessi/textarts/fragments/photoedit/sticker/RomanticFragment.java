package com.kessi.textarts.fragments.photoedit.sticker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.kessi.textarts.R;
import com.kessi.textarts.fragments.photoedit.sticker.StickerAdapter;
import com.kessi.textarts.interfaces.StickerListener;
import com.kessi.textarts.model.ImgModel;
import java.util.ArrayList;
import java.util.List;

public class RomanticFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
    RecyclerView recyclerView;
    List<ImgModel> sampleArrayList;
    StickerListener stickerListener;

    public void setStickerListener(StickerListener stickerListener2) {
        this.stickerListener = stickerListener2;
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_sticker_recyclerview, viewGroup, false);
        recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerSticker);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        this.sampleArrayList = light();
        this.recyclerView.setAdapter(new StickerAdapter(this.sampleArrayList, getActivity(), this));
        return inflate;
    }

    public List<ImgModel> light() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ImgModel(R.drawable.roman01));
        arrayList.add(new ImgModel(R.drawable.roman02));
        arrayList.add(new ImgModel(R.drawable.roman03));
        arrayList.add(new ImgModel(R.drawable.roman04));
        arrayList.add(new ImgModel(R.drawable.roman05));
        arrayList.add(new ImgModel(R.drawable.roman06));
        arrayList.add(new ImgModel(R.drawable.roman07));
        arrayList.add(new ImgModel(R.drawable.roman08));
        arrayList.add(new ImgModel(R.drawable.roman09));
        arrayList.add(new ImgModel(R.drawable.roman10));
        arrayList.add(new ImgModel(R.drawable.roman11));
        arrayList.add(new ImgModel(R.drawable.roman12));
        arrayList.add(new ImgModel(R.drawable.roman13));
        arrayList.add(new ImgModel(R.drawable.roman14));
        arrayList.add(new ImgModel(R.drawable.roman15));
        arrayList.add(new ImgModel(R.drawable.roman16));
        arrayList.add(new ImgModel(R.drawable.roman17));
        arrayList.add(new ImgModel(R.drawable.roman18));
        arrayList.add(new ImgModel(R.drawable.roman19));
        arrayList.add(new ImgModel(R.drawable.roman20));
        return arrayList;
    }

    @Override 
    public void onStickerSelected(int i) {
        StickerListener stickerListener2 = this.stickerListener;
        if (stickerListener2 != null) {
            stickerListener2.onStickerClick(i);
        }
    }
}
