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

public class HeartFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
    RecyclerView recyclerView;
    ArrayList<ImgModel> sampleArrayList;
    StickerListener stickerListener;

    public void setStickerListener(StickerListener stickerListener2) {
        this.stickerListener = stickerListener2;
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_sticker_recyclerview, viewGroup, false);
        recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerSticker);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        this.sampleArrayList = heartList();
        this.recyclerView.setAdapter(new StickerAdapter(this.sampleArrayList, getActivity(), this));
        return inflate;
    }

    private ArrayList<ImgModel> heartList() {
        ArrayList<ImgModel> arrayList = new ArrayList<>();
        arrayList.add(new ImgModel(R.drawable.heart01));
        arrayList.add(new ImgModel(R.drawable.heart02));
        arrayList.add(new ImgModel(R.drawable.heart03));
        arrayList.add(new ImgModel(R.drawable.heart04));
        arrayList.add(new ImgModel(R.drawable.heart05));
        arrayList.add(new ImgModel(R.drawable.heart06));
        arrayList.add(new ImgModel(R.drawable.heart07));
        arrayList.add(new ImgModel(R.drawable.heart08));
        arrayList.add(new ImgModel(R.drawable.heart09));
        arrayList.add(new ImgModel(R.drawable.heart10));
        arrayList.add(new ImgModel(R.drawable.heart11));
        arrayList.add(new ImgModel(R.drawable.heart12));
        arrayList.add(new ImgModel(R.drawable.heart13));
        arrayList.add(new ImgModel(R.drawable.heart14));
        arrayList.add(new ImgModel(R.drawable.heart15));
        arrayList.add(new ImgModel(R.drawable.heart16));
        arrayList.add(new ImgModel(R.drawable.heart18));
        arrayList.add(new ImgModel(R.drawable.heart19));
        arrayList.add(new ImgModel(R.drawable.heart20));
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
