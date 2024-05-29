package com.kessi.textarts.fragments.photoedit.stickertext;

import android.app.Dialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.kessi.textarts.R;
import com.kessi.textarts.interfaces.OverplayListener;
import java.util.Objects;

public class SayingsFragment extends BottomSheetDialogFragment {
    public OverplayListener listener;
    private final BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override 
        public void onSlide(View view, float f) {
        }

        @Override 
        public void onStateChanged(View view, int i) {
            if (i == 5) {
                SayingsFragment.this.dismiss();
            }
        }
    };

    public void setOverplayListener(OverplayListener overplayListener) {
        this.listener = overplayListener;
    }

    @Override 
    public void setupDialog(Dialog dialog, int i) {
        super.setupDialog(dialog, i);
        View inflate = View.inflate(getContext(), R.layout.fragment_bottom_sticker_emoji_dialog, null);
        dialog.setContentView(inflate);
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) ((View) inflate.getParent()).getLayoutParams()).getBehavior();
        if (behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(this.mBottomSheetBehaviorCallback);
        }
        ((View) inflate.getParent()).setBackgroundColor(ContextCompat.getColor((Context) Objects.requireNonNull(getContext()), 17170445));
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.rvEmoji);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(new SayAdapter());
    }

    public class SayAdapter extends RecyclerView.Adapter<SayAdapter.ViewHolder> {
        int[] sayingLists = {R.drawable.saying, R.drawable.saying0, R.drawable.saying1, R.drawable.saying2, R.drawable.saying3, R.drawable.saying4, R.drawable.saying5, R.drawable.saying6, R.drawable.saying7, R.drawable.saying8, R.drawable.saying9, R.drawable.saying10, R.drawable.saying11, R.drawable.saying12, R.drawable.saying13, R.drawable.saying14, R.drawable.saying15, R.drawable.saying16, R.drawable.saying17, R.drawable.saying18, R.drawable.saying19, R.drawable.saying20, R.drawable.saying21, R.drawable.saying22, R.drawable.saying23, R.drawable.saying24, R.drawable.saying25, R.drawable.saying26, R.drawable.saying27, R.drawable.saying28, R.drawable.saying29, R.drawable.saying30, R.drawable.saying31, R.drawable.saying32, R.drawable.saying33, R.drawable.saying34, R.drawable.saying35, R.drawable.saying36, R.drawable.saying37, R.drawable.saying38};

        public SayAdapter() {
        }

        @Override 
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_sticker, viewGroup, false));
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            Glide.with((FragmentActivity) Objects.requireNonNull(SayingsFragment.this.getActivity())).load(Integer.valueOf(this.sayingLists[i])).thumbnail(0.1f).into(viewHolder.imgSticker);
        }

        @Override 
        public int getItemCount() {
            return this.sayingLists.length;
        }

        
        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imgSticker;

            ViewHolder(View view) {
                super(view);
                this.imgSticker = (ImageView) view.findViewById(R.id.imgSticker);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (SayingsFragment.this.listener != null) {
                            SayingsFragment.this.listener.onOverplayClick(BitmapFactory.decodeResource(SayingsFragment.this.getResources(), SayAdapter.this.sayingLists[getLayoutPosition()]));
                        }
                        SayingsFragment.this.dismiss();
                    }
                });
            }

        }
    }
}
