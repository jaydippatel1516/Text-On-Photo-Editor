package com.kessi.textarts.mycreation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kessi.textarts.R;
import com.kessi.textarts.activities.ArtPreviewActivity;
import com.kessi.textarts.util.AdManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MyCreationActivity extends AppCompatActivity {
    public static ArrayList<String> videoPath = new ArrayList<>();
    int FLAG_VIDEO = 21;
    ImageView backIV;
    LinearLayout banner;
    RelativeLayout header;
    MyVideoAdapter videoAdapter;
    RecyclerView videoListView;

   
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.my_creation);
        this.header = (RelativeLayout) findViewById(R.id.header);
        videoLoader();
        backIV = (ImageView) findViewById(R.id.back);
        backIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


    public void videoLoader() {
        getFromStorage();
        this.videoListView = (RecyclerView) findViewById(R.id.recyclerView);
        this.videoAdapter = new MyVideoAdapter(videoPath, this, new CustomItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(MyCreationActivity.this, ArtPreviewActivity.class);
                intent.setData(Uri.fromFile(new File(videoPath.get(i))));
                startActivity(intent);
            }
        });
        this.videoListView.setLayoutManager(new GridLayoutManager(this, 2));
        this.videoListView.setItemAnimator(new DefaultItemAnimator());
        this.videoListView.setAdapter(this.videoAdapter);
    }

    public void getFromStorage() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/TextPhoto");
        videoPath = new ArrayList<>();
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            Arrays.sort(listFiles, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return Long.compare(((File) o2).lastModified(), ((File) o1).lastModified());
                }
            });
            for (File file2 : listFiles) {
                videoPath.add(file2.getAbsolutePath());
            }
        }
    }

   
    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.FLAG_VIDEO) {
            this.videoAdapter.notifyDataSetChanged();
            videoLoader();
        }
    }
}
