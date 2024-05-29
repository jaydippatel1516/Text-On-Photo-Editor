package com.kessi.textarts.mycreation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.kessi.textarts.R;
import java.io.File;
import java.util.ArrayList;

public class MyVideoAdapter extends RecyclerView.Adapter<MyVideoAdapter.FileHolder> {
    Context context;
    CustomItemClickListener listener;
    ArrayList<String> videoFiles;

    public MyVideoAdapter(ArrayList<String> arrayList, Context context2, CustomItemClickListener customItemClickListener) {
        this.videoFiles = arrayList;
        this.context = context2;
        this.listener = customItemClickListener;
    }

    public class FileHolder extends RecyclerView.ViewHolder {
        ImageView delete;
        RelativeLayout lay;
        RelativeLayout videoLay;
        ImageView videoThumb;

        public FileHolder(View view) {
            super(view);
            this.videoThumb = (ImageView) view.findViewById(R.id.videoThumbIV);
            this.delete = (ImageView) view.findViewById(R.id.delete);
            this.videoLay = (RelativeLayout) view.findViewById(R.id.videoLay);
            this.lay = (RelativeLayout) view.findViewById(R.id.lay);
        }
    }

    @Override 
    public FileHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new FileHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.myvideolay, viewGroup, false));
    }

    public void onBindViewHolder(FileHolder fileHolder, int i) {
        Glide.with(this.context).load(this.videoFiles.get(i)).into(fileHolder.videoThumb);
        fileHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, i);
            }
        });
        fileHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new File(videoFiles.get(i)).delete();
                delete(i);
                Toast.makeText(context, "Delete Successfully!!!", 0).show();
            }
        });
    }


    @Override 
    public int getItemCount() {
        return this.videoFiles.size();
    }

    public void delete(int i) {
        this.videoFiles.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, this.videoFiles.size());
    }
}
