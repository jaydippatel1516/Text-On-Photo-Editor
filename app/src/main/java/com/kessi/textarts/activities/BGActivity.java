package com.kessi.textarts.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kessi.textarts.R;
import com.kessi.textarts.adapter.sample.BackgroundColorAdapter;
import com.kessi.textarts.adapter.sample.BackgroundImageAdapter;
import com.kessi.textarts.adapter.sample.BackgroundImageAdapter2;
import com.kessi.textarts.adapter.sample.GenDataBackGround;
import com.kessi.textarts.interfaces.ItemClickListener;
import com.kessi.textarts.model.ImgModel;
import com.kessi.textarts.util.AdManager;

public class BGActivity extends AppCompatActivity {
    LinearLayout adContainer;
    RelativeLayout containerNative;
    private RecyclerView recyclerAbstract;
    private RecyclerView recyclerAnimal;
    private RecyclerView recyclerCartoon;
    private RecyclerView recyclerColors;
    private RecyclerView recyclerLove;
    private RecyclerView recyclerNature;
    private RecyclerView recyclerVehicle;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_bg);
        findViewById(R.id.back).setOnClickListener(v -> onBackPressed());
        this.recyclerColors = findViewById(R.id.recyclerColor);
        setRecyclerColors();
        this.recyclerNature = findViewById(R.id.recyclerNature);
        setRecyclerNature();
        this.recyclerAbstract = findViewById(R.id.recyclerAbstract);
        setRecyclerAbstract();
        this.recyclerLove = findViewById(R.id.recyclerLove);
        setRecyclerLove();
        this.recyclerAnimal = findViewById(R.id.recyclerAnimal);
        setRecyclerNightSky();
        this.recyclerCartoon = findViewById(R.id.recyclerCartoon);
        setRecyclerCartoon();
        this.recyclerVehicle = findViewById(R.id.recyclerVehicle);
        setRecyclerVehicle();
       
    }
    
    private void setRecyclerColors() {
        this.recyclerColors.setHasFixedSize(true);
        this.recyclerColors.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.recyclerColors.setAdapter(new BackgroundColorAdapter(GenDataBackGround.colorList(), this, new ItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                sendData(GenDataBackGround.colorList().get(i));
            }
        }));
    }

    private void setRecyclerAbstract() {
        this.recyclerAbstract.setHasFixedSize(true);
        this.recyclerAbstract.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.recyclerAbstract.setAdapter(new BackgroundImageAdapter(GenDataBackGround.abstractList(), this, new ItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                sendData(GenDataBackGround.abstractList().get(i));
            }
        }));
    }
    private void setRecyclerNature() {
        this.recyclerNature.setHasFixedSize(true);
        this.recyclerNature.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.recyclerNature.setAdapter(new BackgroundImageAdapter(GenDataBackGround.natureList(), this, new ItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                sendData(GenDataBackGround.natureList().get(i));
            }
        }));
    }


    private void setRecyclerLove() {
        this.recyclerLove.setHasFixedSize(true);
        this.recyclerLove.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.recyclerLove.setAdapter(new BackgroundImageAdapter2(GenDataBackGround.loveList(), this, new ItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                sendData(GenDataBackGround.loveList().get(i));

            }
        }));
    }

    private void setRecyclerNightSky() {
        this.recyclerAnimal.setHasFixedSize(true);
        this.recyclerAnimal.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.recyclerAnimal.setAdapter(new BackgroundImageAdapter(GenDataBackGround.animalList(), this, new ItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                sendData(GenDataBackGround.animalList().get(i));

            }
        }));
    }

    private void setRecyclerCartoon() {
        this.recyclerCartoon.setHasFixedSize(true);
        this.recyclerCartoon.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.recyclerCartoon.setAdapter(new BackgroundImageAdapter2(GenDataBackGround.cartoonList(), this, new ItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                sendData(GenDataBackGround.cartoonList().get(i));
            }
        }));
    }

    private void setRecyclerVehicle() {
        this.recyclerVehicle.setHasFixedSize(true);
        this.recyclerVehicle.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.recyclerVehicle.setAdapter(new BackgroundImageAdapter2(GenDataBackGround.vehicleList(), this, new ItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                sendData(GenDataBackGround.vehicleList().get(i));

            }
        }));
    }


    public void sendData(ImgModel imgModel) {
        Intent intent = new Intent(this, TextArtActivity.class);
        intent.putExtra("SampleBackground", imgModel.getImgModel());
        startActivity(intent);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
