package com.kessi.textarts.fragments.photoedit;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.kessi.textarts.R;
import com.kessi.textarts.photoeditor.PhotoEditor;
import java.util.List;
import java.util.Objects;

public class EmojiFragment extends Fragment {
    public EmojiListener mEmojiListener;
    RecyclerView recyclerView;

    public interface EmojiListener {
        void onEmojiClick(String str);
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_image_emoji, viewGroup, false);
        RecyclerView recyclerView2 = (RecyclerView) inflate.findViewById(R.id.recyclerEmoji);
        this.recyclerView = recyclerView2;
        recyclerView2.setLayoutManager(new GridLayoutManager(getActivity(), 7));
        this.recyclerView.setAdapter(new EmojiAdapter());
        return inflate;
    }

    public void setEmojiListener(EmojiListener emojiListener) {
        this.mEmojiListener = emojiListener;
    }

    public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.ViewHolder> {
        List<String> emojis;

        public EmojiAdapter() {
            this.emojis = PhotoEditor.getEmojis((Context) Objects.requireNonNull(EmojiFragment.this.getActivity()));
        }

        @Override 
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_emoji, viewGroup, false));
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.textView.setText(this.emojis.get(i));
        }

        @Override 
        public int getItemCount() {
            return this.emojis.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public ViewHolder(View view) {
                super(view);
                this.textView = (TextView) view.findViewById(R.id.txtEmoji);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (EmojiFragment.this.mEmojiListener != null) {
                            EmojiFragment.this.mEmojiListener.onEmojiClick(EmojiAdapter.this.emojis.get(getLayoutPosition()));
                        }
                    }
                });
            }

        }
    }
}
