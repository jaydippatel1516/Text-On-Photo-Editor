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

public class HalloweenFragment extends BottomSheetDialogFragment {
    public OverplayListener listener;
    private final BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override 
        public void onSlide(View view, float f) {
        }

        @Override 
        public void onStateChanged(View view, int i) {
            if (i == 5) {
                HalloweenFragment.this.dismiss();
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
        recyclerView.setAdapter(new HalloweenAdapter());
    }

    public class HalloweenAdapter extends RecyclerView.Adapter<HalloweenAdapter.ViewHolder> {
        int[] hallowList = {R.drawable.halloween, R.drawable.halloween0, R.drawable.halloween1, R.drawable.halloween2, R.drawable.halloween3, R.drawable.halloween4, R.drawable.halloween5, R.drawable.halloween6, R.drawable.halloween7, R.drawable.halloween8, R.drawable.halloween9, R.drawable.halloween10, R.drawable.halloween11, R.drawable.halloween12, R.drawable.halloween13, R.drawable.halloween14, R.drawable.halloween15, R.drawable.halloween16, R.drawable.halloween17, R.drawable.halloween18, R.drawable.halloween19, R.drawable.halloween20, R.drawable.halloween21, R.drawable.halloween22, R.drawable.halloween23, R.drawable.halloween24, R.drawable.halloween25, R.drawable.halloween26, R.drawable.halloween27, R.drawable.halloween28, R.drawable.halloween29, R.drawable.halloween30};

        public HalloweenAdapter() {
        }

        @Override 
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_sticker, viewGroup, false));
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            Glide.with((FragmentActivity) Objects.requireNonNull(HalloweenFragment.this.getActivity())).load(Integer.valueOf(this.hallowList[i])).thumbnail(0.1f).into(viewHolder.imgSticker);
        }

        @Override 
        public int getItemCount() {
            return this.hallowList.length;
        }

        
        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imgSticker;

            ViewHolder(View view) {
                super(view);
                this.imgSticker = (ImageView) view.findViewById(R.id.imgSticker);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (HalloweenFragment.this.listener != null) {
                            HalloweenFragment.this.listener.onOverplayClick(BitmapFactory.decodeResource(HalloweenFragment.this.getResources(), HalloweenAdapter.this.hallowList[getLayoutPosition()]));
                        }
                        HalloweenFragment.this.dismiss();
                    }
                });
            }

        }
    }
}
