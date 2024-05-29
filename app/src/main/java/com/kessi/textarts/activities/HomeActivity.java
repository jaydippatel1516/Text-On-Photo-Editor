package com.kessi.textarts.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;

import com.kessi.textarts.R;
import com.kessi.textarts.mycreation.MyCreationActivity;
import com.kessi.textarts.util.AdManager;
import com.kessi.textarts.util.Utils;
import com.yalantis.ucrop.UCrop;
import java.io.File;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "HomeActivity";
    ImageView btnCreation;
    ImageView btn_sample;
    ImageView btn_start;
    ImageView img_logo;
    ImageView mBg;
    public int requestMode = 1;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home);
        this.img_logo = (ImageView) findViewById(R.id.img_logo);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.main_logo)).into(this.img_logo);
        this.mBg = (ImageView) findViewById(R.id.mBg);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.main_bg)).into(this.mBg);
        initViews();


    }

    private void initViews() {
        this.btn_sample = (ImageView) findViewById(R.id.btnTemplate);
        this.btn_start = (ImageView) findViewById(R.id.btnTextArt);
        this.btnCreation = (ImageView) findViewById(R.id.btnCreation);
        this.btn_start.setOnClickListener(this);
        this.btn_sample.setOnClickListener(this);
        this.btnCreation.setOnClickListener(this);
        findViewById(R.id.bt_share).setOnClickListener(this);
        findViewById(R.id.bt_rate).setOnClickListener(this);
        findViewById(R.id.bt_more).setOnClickListener(this);
        findViewById(R.id.bt_privacy).setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.btnCreation) {
            if(id==R.id.bt_more){
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/dev?id=7081479513420377164&hl=en")));
            }else if(id==R.id.bt_privacy){
                startActivity(new Intent(this, PrivacyActivity.class));
            }else if(id==R.id.bt_rate){
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName())));
                    return;
                } catch (ActivityNotFoundException unused) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                    return;
                }
            }else if(id==R.id.bt_share){
                shareApp();
            }else if(id==R.id.btnTemplate){
                if (Build.VERSION.SDK_INT >= 33 && Utils.hasPermissions(this, Utils.permissionsList13)) {
                    ActivityCompat.requestPermissions(this, Utils.permissionsList13, 211);
                    return;
                } else if (Build.VERSION.SDK_INT >= 33 || !Utils.hasPermissions(this, Utils.permissions)) {
                    navigate(new Intent(this, BGActivity.class));
                    return;
                } else {
                    ActivityCompat.requestPermissions(this, Utils.permissions, Utils.perRequest);
                    return;
                }
            }else if(id==R.id.btnTextArt){
                pickFromGalery();
            }
        } else {
            if (Build.VERSION.SDK_INT >= 33 && Utils.hasPermissions(this, Utils.permissionsList13)) {
                ActivityCompat.requestPermissions(this, Utils.permissionsList13, 211);
            } else if (Build.VERSION.SDK_INT >= 33 || !Utils.hasPermissions(this, Utils.permissions)) {
                navigate(new Intent(this, MyCreationActivity.class));
            } else {
                ActivityCompat.requestPermissions(this, Utils.permissions, Utils.perRequest);
            }
        }
    }

    
    public void navigate(Intent intent) {
        AdManager.startActivity(this, intent, 0);

    }

    private void shareApp() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", "Download this awesome app\n https://play.google.com/store/apps/details?id=" + getPackageName() + " \n");
        startActivity(intent);
    }

    public void pickFromGalery() {
        if (Build.VERSION.SDK_INT >= 33 && Utils.hasPermissions(this, Utils.permissionsList13)) {
            ActivityCompat.requestPermissions(this, Utils.permissionsList13, 211);
        } else if (Build.VERSION.SDK_INT >= 33 || !Utils.hasPermissions(this, Utils.permissions)) {
            Intent addCategory = new Intent("android.intent.action.GET_CONTENT").setType("image/*").addCategory("android.intent.category.OPENABLE");
            Log.e("addCategory", "" + addCategory);
            addCategory.putExtra("android.intent.extra.MIME_TYPES", new String[]{"image/jpeg", "image/png"});
            startActivityForResult(Intent.createChooser(addCategory, "Select Picture"), this.requestMode);
        } else {
            ActivityCompat.requestPermissions(this, Utils.permissions, Utils.perRequest);
        }
    }

    @Override 
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            if (i == this.requestMode) {
                Uri data = intent != null ? intent.getData() : null;
                if (data != null) {
                    startCrop(data);
                } else {
                    Toast.makeText(this, "Select image again", 0).show();
                }
            } else if (i == 69) {
                if (intent != null) {
                    handleCropResult(intent);
                } else {
                    return;
                }
            }
        }
        if (i2 == 96 && intent != null) {
            handleCropError(intent);
        }
    }

    private void handleCropError(Intent intent) {
        Throwable error = UCrop.getError(intent);
        if (error != null) {
            Log.e(TAG, "handleCropError: ", error);
            Toast.makeText(this, error.getMessage(), 1).show();
            return;
        }
        Toast.makeText(this, "Unexpected error", 0).show();
    }

    private void handleCropResult(Intent intent) {
        Uri output = UCrop.getOutput(intent);
        if (output != null) {
            Intent intent2 = new Intent(this, TextArtActivity.class);
            intent2.setData(output);
            navigate(intent2);
            return;
        }
        Toast.makeText(this, "Cannot retrieve cropped image", 0).show();
    }

    private void startCrop(Uri uri) {
        UCrop of = UCrop.of(uri, Uri.fromFile(new File(getCacheDir(), "SampleCropImage")));
        of.useSourceImageAspectRatio();
        of.useSourceImageAspectRatio();
        UCrop.Options options = new UCrop.Options();
        options.setToolbarTitle("Crop Image");
        options.setToolbarColor(getResources().getColor(R.color.main_bg));
        options.setToolbarWidgetColor(getResources().getColor(R.color.white));
        options.setRootViewBackgroundColor(getResources().getColor(R.color.main_bg));
        options.setStatusBarColor(getResources().getColor(R.color.main_bg));
        options.setLogoColor(getResources().getColor(R.color.white));
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        options.setFreeStyleCropEnabled(true);
        of.withOptions(options);
        of.start(this);
    }

    @Override 
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override 
    public void onDestroy() {
        super.onDestroy();
    }
}
