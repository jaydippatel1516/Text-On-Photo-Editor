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

public class FlowersFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
    RecyclerView recyclerView;
    ArrayList<ImgModel> sampleArrayList;
    StickerListener stickerListener;

    public void setStickerListener(StickerListener stickerListener2) {
        this.stickerListener = stickerListener2;
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_sticker_recyclerview, viewGroup, false);
        recyclerView= (RecyclerView) inflate.findViewById(R.id.recyclerSticker);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        this.sampleArrayList = flowerList();
        this.recyclerView.setAdapter(new StickerAdapter(this.sampleArrayList, getActivity(), this));
        return inflate;
    }

    private ArrayList<ImgModel> flowerList() {
        ArrayList<ImgModel> arrayList = new ArrayList<>();
        arrayList.add(new ImgModel(R.drawable.sflower1));
        arrayList.add(new ImgModel(R.drawable.sflower2));
        arrayList.add(new ImgModel(R.drawable.sflower3));
        arrayList.add(new ImgModel(R.drawable.sflower4));
        arrayList.add(new ImgModel(R.drawable.sflower5));
        arrayList.add(new ImgModel(R.drawable.sflower6));
        arrayList.add(new ImgModel(R.drawable.sflower7));
        arrayList.add(new ImgModel(R.drawable.sflower8));
        arrayList.add(new ImgModel(R.drawable.sflower9));
        arrayList.add(new ImgModel(R.drawable.sflower10));
        arrayList.add(new ImgModel(R.drawable.sflower11));
        arrayList.add(new ImgModel(R.drawable.sflower12));
        arrayList.add(new ImgModel(R.drawable.sflower13));
        arrayList.add(new ImgModel(R.drawable.sflower14));
        arrayList.add(new ImgModel(R.drawable.sflower15));
        arrayList.add(new ImgModel(R.drawable.sflower16));
        arrayList.add(new ImgModel(R.drawable.sflower17));
        arrayList.add(new ImgModel(R.drawable.sflower18));
        arrayList.add(new ImgModel(R.drawable.sflower19));
        arrayList.add(new ImgModel(R.drawable.sflower20));
        arrayList.add(new ImgModel(R.drawable.sflower21));
        arrayList.add(new ImgModel(R.drawable.sflower22));
        arrayList.add(new ImgModel(R.drawable.sflower23));
        arrayList.add(new ImgModel(R.drawable.sflower24));
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
