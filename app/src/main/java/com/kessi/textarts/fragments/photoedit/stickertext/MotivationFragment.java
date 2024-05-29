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

public class MotivationFragment extends BottomSheetDialogFragment {
    public OverplayListener listener;
    private final BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override 
        public void onSlide(View view, float f) {
        }

        @Override 
        public void onStateChanged(View view, int i) {
            if (i == 5) {
                MotivationFragment.this.dismiss();
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
        recyclerView.setAdapter(new MotivationAdapter());
    }

    public class MotivationAdapter extends RecyclerView.Adapter<MotivationAdapter.ViewHolder> {
        int[] motivationList = {R.drawable.motivation, R.drawable.motivation0, R.drawable.motivation1, R.drawable.motivation2, R.drawable.motivation3, R.drawable.motivation4, R.drawable.motivation5, R.drawable.motivation6, R.drawable.motivation7, R.drawable.motivation8, R.drawable.motivation9, R.drawable.motivation10, R.drawable.motivation11, R.drawable.motivation12, R.drawable.motivation13, R.drawable.motivation14, R.drawable.motivation15, R.drawable.motivation16, R.drawable.motivation17, R.drawable.motivation18, R.drawable.motivation19, R.drawable.motivation20, R.drawable.motivation21, R.drawable.motivation22, R.drawable.motivation23, R.drawable.motivation24, R.drawable.motivation25, R.drawable.motivation26, R.drawable.motivation27, R.drawable.motivation28, R.drawable.motivation29, R.drawable.motivation30, R.drawable.motivation31, R.drawable.motivation32, R.drawable.motivation33, R.drawable.motivation34, R.drawable.motivation35, R.drawable.motivation36, R.drawable.motivation37};

        public MotivationAdapter() {
        }

        @Override 
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_sticker, viewGroup, false));
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            Glide.with((FragmentActivity) Objects.requireNonNull(MotivationFragment.this.getActivity())).load(Integer.valueOf(this.motivationList[i])).thumbnail(0.1f).into(viewHolder.imgSticker);
        }

        @Override 
        public int getItemCount() {
            return this.motivationList.length;
        }

        
        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imgSticker;

            ViewHolder(View view) {
                super(view);
                this.imgSticker = (ImageView) view.findViewById(R.id.imgSticker);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (MotivationFragment.this.listener != null) {
                            MotivationFragment.this.listener.onOverplayClick(BitmapFactory.decodeResource(MotivationFragment.this.getResources(), MotivationAdapter.this.motivationList[getLayoutPosition()]));
                        }
                        MotivationFragment.this.dismiss();
                    }
                });
            }

        }
    }
}
