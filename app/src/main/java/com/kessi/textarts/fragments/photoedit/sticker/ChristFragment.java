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

public class ChristFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
    List<ImgModel> chistmasList;
    RecyclerView recyclerView;
    StickerListener stickerListener;

    public void setStickerListener(StickerListener stickerListener2) {
        this.stickerListener = stickerListener2;
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_sticker_recyclerview, viewGroup, false);
        recyclerView= (RecyclerView) inflate.findViewById(R.id.recyclerSticker);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        this.chistmasList = chistmasList();
        this.recyclerView.setAdapter(new StickerAdapter(this.chistmasList, getActivity(), this));
        return inflate;
    }

    public List<ImgModel> chistmasList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ImgModel(R.drawable.chris0));
        arrayList.add(new ImgModel(R.drawable.chris1));
        arrayList.add(new ImgModel(R.drawable.chris2));
        arrayList.add(new ImgModel(R.drawable.chris3));
        arrayList.add(new ImgModel(R.drawable.chris4));
        arrayList.add(new ImgModel(R.drawable.chris5));
        arrayList.add(new ImgModel(R.drawable.chris6));
        arrayList.add(new ImgModel(R.drawable.chris7));
        arrayList.add(new ImgModel(R.drawable.chris8));
        arrayList.add(new ImgModel(R.drawable.chris9));
        arrayList.add(new ImgModel(R.drawable.chris11));
        arrayList.add(new ImgModel(R.drawable.chris12));
        arrayList.add(new ImgModel(R.drawable.chris13));
        arrayList.add(new ImgModel(R.drawable.chris14));
        arrayList.add(new ImgModel(R.drawable.chris15));
        arrayList.add(new ImgModel(R.drawable.chris16));
        arrayList.add(new ImgModel(R.drawable.chris17));
        arrayList.add(new ImgModel(R.drawable.chris18));
        arrayList.add(new ImgModel(R.drawable.chris19));
        arrayList.add(new ImgModel(R.drawable.chris20));
        arrayList.add(new ImgModel(R.drawable.chris6));
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
