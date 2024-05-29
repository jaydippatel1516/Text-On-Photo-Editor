package com.kessi.textarts.fragments.photoedit.sticker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.kessi.textarts.R;
import com.kessi.textarts.model.ImgModel;
import com.kessi.textarts.views.SquareImageView;
import java.util.List;

public class StickerAdapter extends RecyclerView.Adapter<StickerAdapter.ViewHolderSticker> {
    int currentPos = -1;
    public StickerAdaperListener listener;
    private final Context mContext;
    public List<ImgModel> stickerArrayList;

    public interface StickerAdaperListener {
        void onStickerSelected(int i);
    }

    public StickerAdapter(List<ImgModel> list, Context context, StickerAdaperListener stickerAdaperListener) {
        this.stickerArrayList = list;
        this.mContext = context;
        this.listener = stickerAdaperListener;
    }

    @Override 
    public ViewHolderSticker onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolderSticker(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sticker, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolderSticker viewHolderSticker, int i) {
        Glide.with(this.mContext).load(Integer.valueOf(this.stickerArrayList.get(i).getImgModel())).thumbnail(0.1f).into(viewHolderSticker.imgSticker);
        if (i == this.currentPos) {
            viewHolderSticker.imgSticker.setBackgroundResource(R.drawable.t_gradient_bg);
        } else {
            viewHolderSticker.imgSticker.setBackgroundResource(R.drawable.card_white);
        }
        viewHolderSticker.imgSticker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    currentPos = i;
                    notifyDataSetChanged();
                    listener.onStickerSelected(stickerArrayList.get(i).getImgModel());
                }
            }
        });
    }


    @Override 
    public int getItemCount() {
        return this.stickerArrayList.size();
    }

    public class ViewHolderSticker extends RecyclerView.ViewHolder {
        SquareImageView imgSticker;

        public ViewHolderSticker(View view) {
            super(view);
            this.imgSticker = (SquareImageView) view.findViewById(R.id.imgSticker);
        }
    }
}
