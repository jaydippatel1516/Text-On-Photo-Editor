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

public class CuteFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
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
        this.sampleArrayList = light();
        this.recyclerView.setAdapter(new StickerAdapter(this.sampleArrayList, getActivity(), this));
        return inflate;
    }

    public List<ImgModel> light() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ImgModel(R.drawable.cute1));
        arrayList.add(new ImgModel(R.drawable.cute2));
        arrayList.add(new ImgModel(R.drawable.cute3));
        arrayList.add(new ImgModel(R.drawable.cute4));
        arrayList.add(new ImgModel(R.drawable.cute5));
        arrayList.add(new ImgModel(R.drawable.cute6));
        arrayList.add(new ImgModel(R.drawable.cute7));
        arrayList.add(new ImgModel(R.drawable.cute8));
        arrayList.add(new ImgModel(R.drawable.cute9));
        arrayList.add(new ImgModel(R.drawable.cute10));
        arrayList.add(new ImgModel(R.drawable.cute11));
        arrayList.add(new ImgModel(R.drawable.cute12));
        arrayList.add(new ImgModel(R.drawable.cute13));
        arrayList.add(new ImgModel(R.drawable.cute14));
        arrayList.add(new ImgModel(R.drawable.cute15));
        arrayList.add(new ImgModel(R.drawable.cute16));
        arrayList.add(new ImgModel(R.drawable.cute17));
        arrayList.add(new ImgModel(R.drawable.cute18));
        arrayList.add(new ImgModel(R.drawable.cute19));
        arrayList.add(new ImgModel(R.drawable.cute20));
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
