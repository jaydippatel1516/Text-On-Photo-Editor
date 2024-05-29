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

public class BirthdayFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
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
        this.sampleArrayList = fireWorks();
        this.recyclerView.setAdapter(new StickerAdapter(this.sampleArrayList, getActivity(), this));
        return inflate;
    }

    public List<ImgModel> fireWorks() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ImgModel(R.drawable.bday01));
        arrayList.add(new ImgModel(R.drawable.bday02));
        arrayList.add(new ImgModel(R.drawable.bday03));
        arrayList.add(new ImgModel(R.drawable.bday04));
        arrayList.add(new ImgModel(R.drawable.bday05));
        arrayList.add(new ImgModel(R.drawable.bday06));
        arrayList.add(new ImgModel(R.drawable.bday07));
        arrayList.add(new ImgModel(R.drawable.bday08));
        arrayList.add(new ImgModel(R.drawable.bday09));
        arrayList.add(new ImgModel(R.drawable.bday10));
        arrayList.add(new ImgModel(R.drawable.bday11));
        arrayList.add(new ImgModel(R.drawable.bday12));
        arrayList.add(new ImgModel(R.drawable.bday13));
        arrayList.add(new ImgModel(R.drawable.bday14));
        arrayList.add(new ImgModel(R.drawable.bday15));
        arrayList.add(new ImgModel(R.drawable.bday16));
        arrayList.add(new ImgModel(R.drawable.bday17));
        arrayList.add(new ImgModel(R.drawable.bday18));
        arrayList.add(new ImgModel(R.drawable.bday19));
        arrayList.add(new ImgModel(R.drawable.bday20));
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
