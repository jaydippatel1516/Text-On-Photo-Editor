package com.kessi.textarts.adapter.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.kessi.textarts.R;
import com.kessi.textarts.interfaces.ItemClickListener;
import com.kessi.textarts.model.ImgModel;
import com.makeramen.roundedimageview.RoundedImageView;
import java.util.List;

public class BackgroundImageAdapter2 extends RecyclerView.Adapter<BackgroundImageAdapter2.ViewHolderImage2> {
    Context context;
    ItemClickListener itemClickListener;
    List<ImgModel> sampleArrayList;

    public BackgroundImageAdapter2(List<ImgModel> list, Context context2, ItemClickListener itemClickListener2) {
        this.sampleArrayList = list;
        this.context = context2;
        this.itemClickListener = itemClickListener2;
    }

    @Override 
    public ViewHolderImage2 onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_image_background2, viewGroup, false);
        ViewHolderImage2 viewHolderImage2 = new ViewHolderImage2(inflate);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(v, viewHolderImage2.getLayoutPosition());
            }
        });
        return viewHolderImage2;
    }
    public void onBindViewHolder(ViewHolderImage2 viewHolderImage2, int i) {
        Glide.with(this.context).load(Integer.valueOf(this.sampleArrayList.get(i).getImgModel())).thumbnail(0.1f).into(viewHolderImage2.roundedImageView);
    }

    @Override 
    public int getItemCount() {
        return this.sampleArrayList.size();
    }

    public static class ViewHolderImage2 extends RecyclerView.ViewHolder {
        RoundedImageView roundedImageView;

        public ViewHolderImage2(View view) {
            super(view);
            this.roundedImageView = (RoundedImageView) view.findViewById(R.id.img_background_2);
        }
    }
}
