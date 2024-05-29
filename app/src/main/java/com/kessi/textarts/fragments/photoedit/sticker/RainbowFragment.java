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

public class RainbowFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
    RecyclerView recyclerView;
    List<ImgModel> sampleArrayList;
    StickerListener stickerListener;

    public void setStickerListener(StickerListener stickerListener2) {
        this.stickerListener = stickerListener2;
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_sticker_recyclerview, viewGroup, false);
        recyclerView= (RecyclerView) inflate.findViewById(R.id.recyclerSticker);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        this.sampleArrayList = newyear();
        this.recyclerView.setAdapter(new StickerAdapter(this.sampleArrayList, getActivity(), this));
        return inflate;
    }

    public List<ImgModel> newyear() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ImgModel(R.drawable.rainbow01));
        arrayList.add(new ImgModel(R.drawable.rainbow02));
        arrayList.add(new ImgModel(R.drawable.rainbow03));
        arrayList.add(new ImgModel(R.drawable.rainbow04));
        arrayList.add(new ImgModel(R.drawable.rainbow05));
        arrayList.add(new ImgModel(R.drawable.rainbow06));
        arrayList.add(new ImgModel(R.drawable.rainbow07));
        arrayList.add(new ImgModel(R.drawable.rainbow08));
        arrayList.add(new ImgModel(R.drawable.rainbow09));
        arrayList.add(new ImgModel(R.drawable.rainbow10));
        arrayList.add(new ImgModel(R.drawable.rainbow11));
        arrayList.add(new ImgModel(R.drawable.rainbow12));
        arrayList.add(new ImgModel(R.drawable.rainbow13));
        arrayList.add(new ImgModel(R.drawable.rainbow14));
        arrayList.add(new ImgModel(R.drawable.rainbow15));
        arrayList.add(new ImgModel(R.drawable.rainbow16));
        arrayList.add(new ImgModel(R.drawable.rainbow17));
        arrayList.add(new ImgModel(R.drawable.rainbow18));
        arrayList.add(new ImgModel(R.drawable.rainbow19));
        arrayList.add(new ImgModel(R.drawable.rainbow20));
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
