package com.kessi.textarts.fragments.photoedit;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.kessi.textarts.R;
import com.kessi.textarts.views.SquareImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PhotoFragment extends Fragment {
    public PhotoListener mPhotoListener;
    RecyclerView recyclerView;

    public interface PhotoListener {
        void onPhotoClick(String str);
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_image_photo, viewGroup, false);
        RecyclerView recyclerView2 = (RecyclerView) inflate.findViewById(R.id.recycler_photo);
        this.recyclerView = recyclerView2;
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        this.recyclerView.setAdapter(new PhotoAdapter());
        return inflate;
    }

    public void setPhotoListener(PhotoListener photoListener) {
        this.mPhotoListener = photoListener;
    }

    public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
        List<String> stringArrayList;

        public PhotoAdapter() {
            this.stringArrayList = PhotoFragment.this.getAllShownImagesPath(PhotoFragment.this.getActivity());
        }

        @Override 
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_photo_small, viewGroup, false));
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            Glide.with((FragmentActivity) Objects.requireNonNull(PhotoFragment.this.getActivity())).load(this.stringArrayList.get(i)).thumbnail(0.001f).into(viewHolder.squareImageView);
        }

        @Override 
        public int getItemCount() {
            return this.stringArrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            SquareImageView squareImageView;

            public ViewHolder(View view) {
                super(view);
                this.squareImageView = (SquareImageView) view.findViewById(R.id.imgPhoto);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (PhotoFragment.this.mPhotoListener != null) {
                            PhotoFragment.this.mPhotoListener.onPhotoClick(PhotoAdapter.this.stringArrayList.get(getLayoutPosition()));
                        }
                    }
                });
            }

        }
    }

    public List<String> getAllShownImagesPath(Activity activity) {
        ArrayList arrayList = new ArrayList();
        if (activity == null) {
            return arrayList;
        }
        Cursor query = activity.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "bucket_display_name"}, null, null, null);
        int columnIndexOrThrow = ((Cursor) Objects.requireNonNull(query)).getColumnIndexOrThrow("_data");
        query.getColumnIndexOrThrow("bucket_display_name");
        while (query.moveToNext()) {
            arrayList.add(query.getString(columnIndexOrThrow));
        }
        return arrayList;
    }
}
