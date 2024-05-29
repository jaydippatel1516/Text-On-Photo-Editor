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

public class TravelFragment extends BottomSheetDialogFragment {
    public OverplayListener listener;
    private final BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override 
        public void onSlide(View view, float f) {
        }

        @Override 
        public void onStateChanged(View view, int i) {
            if (i == 5) {
                TravelFragment.this.dismiss();
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
        recyclerView.setAdapter(new TravelAdapter());
    }

    public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.ViewHolder> {
        int[] travelList = {R.drawable.travel, R.drawable.travel0, R.drawable.travel1, R.drawable.travel2, R.drawable.travel3, R.drawable.travel4, R.drawable.travel5, R.drawable.travel6, R.drawable.travel7, R.drawable.travel8, R.drawable.travel9, R.drawable.travel10, R.drawable.travel11, R.drawable.travel12, R.drawable.travel13, R.drawable.travel14, R.drawable.travel15, R.drawable.travel16, R.drawable.travel17, R.drawable.travel18, R.drawable.travel19, R.drawable.travel20, R.drawable.travel21, R.drawable.travel22, R.drawable.travel23, R.drawable.travel24, R.drawable.travel25, R.drawable.travel26, R.drawable.travel27};

        public TravelAdapter() {
        }

        @Override 
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_sticker, viewGroup, false));
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            Glide.with((FragmentActivity) Objects.requireNonNull(TravelFragment.this.getActivity())).load(Integer.valueOf(this.travelList[i])).thumbnail(0.1f).into(viewHolder.imgSticker);
        }

        @Override 
        public int getItemCount() {
            return this.travelList.length;
        }

        
        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imgSticker;

            ViewHolder(View view) {
                super(view);
                this.imgSticker = (ImageView) view.findViewById(R.id.imgSticker);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TravelFragment.this.listener != null) {
                            TravelFragment.this.listener.onOverplayClick(BitmapFactory.decodeResource(TravelFragment.this.getResources(), TravelAdapter.this.travelList[getLayoutPosition()]));
                        }
                        TravelFragment.this.dismiss();
                    }
                });
            }

        }
    }
}
