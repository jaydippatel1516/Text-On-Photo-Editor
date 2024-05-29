package com.kessi.textarts.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.kessi.textarts.R;
import com.kessi.textarts.photoeditor.RoundFrameLayout;
import java.util.ArrayList;
import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {
    ColorAdapterListener colorAdapterListener;
    List<Integer> colorList;
    Context context;
    int index = -1;

    public interface ColorAdapterListener {
        void onColorItemSelected(int i);
    }

    public ColorAdapter(Context context2, ColorAdapterListener colorAdapterListener2) {
        this.context = context2;
        this.colorList = genColorList();
        this.colorAdapterListener = colorAdapterListener2;
    }

    @Override 
    public ColorViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ColorViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_color, viewGroup, false));
    }

    public void onBindViewHolder(ColorViewHolder colorViewHolder, int i) {
        if (this.index == i) {
            colorViewHolder.colorSection.getDelegate().setStrokeColor(ContextCompat.getColor(this.context, R.color.icChecked));
        } else {
            colorViewHolder.colorSection.getDelegate().setStrokeColor(17170445);
        }
        colorViewHolder.colorSection.getDelegate().setBackgroundColor(this.colorList.get(i).intValue());
    }

    @Override 
    public int getItemCount() {
        return this.colorList.size();
    }

    public class ColorViewHolder extends RecyclerView.ViewHolder {
        RoundFrameLayout colorSection;

        public ColorViewHolder(View view) {
            super(view);
            this.colorSection = (RoundFrameLayout) view.findViewById(R.id.color_section);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() < ColorAdapter.this.colorList.size()) {
                        ColorAdapter.this.colorAdapterListener.onColorItemSelected(ColorAdapter.this.colorList.get(getAdapterPosition()).intValue());
                        ColorAdapter.this.index = getAdapterPosition();
                        ColorAdapter.this.notifyDataSetChanged();
                    }
                }
            });
        }

    }

    private List<Integer> genColorList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(Color.parseColor("#FFFFFF")));
        arrayList.add(Integer.valueOf(Color.parseColor("#000000")));
        arrayList.add(Integer.valueOf(Color.parseColor("#0098F1")));
        arrayList.add(Integer.valueOf(Color.parseColor("#4CC259")));
        arrayList.add(Integer.valueOf(Color.parseColor("#FFC859")));
        arrayList.add(Integer.valueOf(Color.parseColor("#FF8523")));
        arrayList.add(Integer.valueOf(Color.parseColor("#FF3A4A")));
        arrayList.add(Integer.valueOf(Color.parseColor("#E90060")));
        arrayList.add(Integer.valueOf(Color.parseColor("#B300B6")));
        arrayList.add(Integer.valueOf(Color.parseColor("#FF0000")));
        arrayList.add(Integer.valueOf(Color.parseColor("#FF7E88")));
        arrayList.add(Integer.valueOf(Color.parseColor("#FFD0D1")));
        arrayList.add(Integer.valueOf(Color.parseColor("#FFDAB2")));
        arrayList.add(Integer.valueOf(Color.parseColor("#FFC07E")));
        arrayList.add(Integer.valueOf(Color.parseColor("#E18B42")));
        arrayList.add(Integer.valueOf(Color.parseColor("#a36138")));
        arrayList.add(Integer.valueOf(Color.parseColor("#4A2829")));
        arrayList.add(Integer.valueOf(Color.parseColor("#004C30")));
        arrayList.add(Integer.valueOf(Color.parseColor("#2C2C2C")));
        arrayList.add(Integer.valueOf(Color.parseColor("#393939")));
        arrayList.add(Integer.valueOf(Color.parseColor("#555555")));
        arrayList.add(Integer.valueOf(Color.parseColor("#727272")));
        arrayList.add(Integer.valueOf(Color.parseColor("#989898")));
        arrayList.add(Integer.valueOf(Color.parseColor("#B1B1B1")));
        arrayList.add(Integer.valueOf(Color.parseColor("#C7C7C7")));
        arrayList.add(Integer.valueOf(Color.parseColor("#DBDBDB")));
        arrayList.add(Integer.valueOf(Color.parseColor("#F0F0F0")));
        return arrayList;
    }
}
