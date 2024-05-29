package com.kessi.textarts.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.kessi.textarts.R;

public class SplashActivity extends AppCompatActivity {
    ImageView icon;
    ImageView mBg;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
        this.icon = (ImageView) findViewById(R.id.icon);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.sp_logo)).into(this.icon);
        this.mBg = (ImageView) findViewById(R.id.mBg);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.sp_bg)).into(this.mBg);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            }
        }, 3000);
    }

}
