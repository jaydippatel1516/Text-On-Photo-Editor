package com.kessi.textarts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.kessi.textarts.R;
import com.kessi.textarts.interfaces.ItemClickListener;
import java.util.List;

public class ThoughtsAdapter extends RecyclerView.Adapter<ThoughtsAdapter.QuotesViewHolder> {
    Context context;
    ItemClickListener itemClickListener;
    List<Integer> list;

    public ThoughtsAdapter(List<Integer> list2, Context context2, ItemClickListener itemClickListener2) {
        this.list = list2;
        this.context = context2;
        this.itemClickListener = itemClickListener2;
    }

    @Override 
    public QuotesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_quotes, viewGroup, false);
        QuotesViewHolder quotesViewHolder = new QuotesViewHolder(inflate);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(v, quotesViewHolder.getLayoutPosition());
            }
        });
        return quotesViewHolder;
    }


    public void onBindViewHolder(QuotesViewHolder quotesViewHolder, int i) {
        quotesViewHolder.tvQuote.setText(this.list.get(i).intValue());
    }

    @Override 
    public int getItemCount() {
        return this.list.size();
    }

    public static class QuotesViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuote;

        public QuotesViewHolder(View view) {
            super(view);
            this.tvQuote = (TextView) view.findViewById(R.id.tvQuote);
        }
    }
}
