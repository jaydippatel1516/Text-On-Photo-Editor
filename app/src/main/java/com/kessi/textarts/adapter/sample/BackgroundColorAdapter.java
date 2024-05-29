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
import com.kessi.textarts.views.CircleImageView;
import java.util.List;

public class BackgroundColorAdapter extends RecyclerView.Adapter<BackgroundColorAdapter.ViewHolderColor> {
    Context context;
    ItemClickListener itemClickListener;
    List<ImgModel> sampleArrayList;

    public BackgroundColorAdapter(List<ImgModel> list, Context context2, ItemClickListener itemClickListener2) {
        this.sampleArrayList = list;
        this.context = context2;
        this.itemClickListener = itemClickListener2;
    }

    @Override
    public ViewHolderColor onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_color_background, viewGroup, false);
        ViewHolderColor viewHolderColor = new ViewHolderColor(inflate);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(v, viewHolderColor.getLayoutPosition());
            }
        });
        return viewHolderColor;
    }


    public void onBindViewHolder(ViewHolderColor viewHolderColor, int i) {
        Glide.with(this.context).load(Integer.valueOf(this.sampleArrayList.get(i).getImgModel())).thumbnail(0.1f).into(viewHolderColor.circleImageView);
    }

    @Override 
    public int getItemCount() {
        return this.sampleArrayList.size();
    }

    public static class ViewHolderColor extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;

        public ViewHolderColor(View view) {
            super(view);
            this.circleImageView = (CircleImageView) view.findViewById(R.id.img_background_circle);
        }
    }
}
