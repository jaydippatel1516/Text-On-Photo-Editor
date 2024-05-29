package com.kessi.textarts.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.kessi.textarts.R;
import java.util.List;

public class FontAdapter extends RecyclerView.Adapter<FontAdapter.FontViewHolder> {
    Context context;
    public int currentPos = 1;
    FontAdapterClickListener fontAdapterClickListener;
    List<String> list;

    public interface FontAdapterClickListener {
        void onFontItemSelected(String str);
    }

    public FontAdapter(Context context2, FontAdapterClickListener fontAdapterClickListener2, List<String> list2) {
        this.context = context2;
        this.fontAdapterClickListener = fontAdapterClickListener2;
        this.list = list2;
    }

    @Override 
    public FontViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new FontViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_font, viewGroup, false));
    }

    public void onBindViewHolder(FontViewHolder fontViewHolder, int i) {
        AssetManager assets = this.context.getAssets();
        TextView textView = fontViewHolder.txtFontDemo;
        textView.setTypeface(Typeface.createFromAsset(assets, "font/" + this.list.get(i)));
        if (i == this.currentPos) {
            fontViewHolder.txtFontDemo.setBackgroundResource(R.drawable.t_gradient_bg);
        } else {
            fontViewHolder.txtFontDemo.setBackgroundResource(R.drawable.card_white);
        }
    }

    @Override 
    public int getItemCount() {
        return this.list.size();
    }

    public static class FontViewHolder extends RecyclerView.ViewHolder {
        FrameLayout fontSection;
        TextView txtFontDemo;

        public FontViewHolder(View view) {
            super(view);
            this.txtFontDemo = (TextView) view.findViewById(R.id.txt_font_demo);
            this.fontSection = (FrameLayout) view.findViewById(R.id.font_section);
        }
    }
}
