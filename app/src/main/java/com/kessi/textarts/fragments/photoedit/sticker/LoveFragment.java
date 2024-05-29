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

public class LoveFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
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
        arrayList.add(new ImgModel(R.drawable.slove1));
        arrayList.add(new ImgModel(R.drawable.slove2));
        arrayList.add(new ImgModel(R.drawable.slove3));
        arrayList.add(new ImgModel(R.drawable.slove4));
        arrayList.add(new ImgModel(R.drawable.slove5));
        arrayList.add(new ImgModel(R.drawable.slove6));
        arrayList.add(new ImgModel(R.drawable.slove7));
        arrayList.add(new ImgModel(R.drawable.slove8));
        arrayList.add(new ImgModel(R.drawable.slove9));
        arrayList.add(new ImgModel(R.drawable.slove10));
        arrayList.add(new ImgModel(R.drawable.slove11));
        arrayList.add(new ImgModel(R.drawable.slove12));
        arrayList.add(new ImgModel(R.drawable.slove13));
        arrayList.add(new ImgModel(R.drawable.slove14));
        arrayList.add(new ImgModel(R.drawable.slove15));
        arrayList.add(new ImgModel(R.drawable.slove16));
        arrayList.add(new ImgModel(R.drawable.slove17));
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
