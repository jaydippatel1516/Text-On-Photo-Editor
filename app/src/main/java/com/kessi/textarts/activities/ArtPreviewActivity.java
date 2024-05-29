package com.kessi.textarts.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import com.kessi.textarts.R;
import com.kessi.textarts.util.AdManager;
import com.kessi.textarts.util.PacList;

public class ArtPreviewActivity extends AppCompatActivity implements View.OnClickListener {
    Bitmap bitmap = null;
    RelativeLayout containerNative;
    ImageView imageView;
    RelativeLayout relativeLayout;
    Uri uri;

    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_art_preview);
        inItUI();
        Uri data = getIntent().getData();
        this.uri = data;
        if (data != null) {
            ((RequestBuilder) ((RequestBuilder) Glide.with((FragmentActivity) this).asBitmap().load(this.uri).diskCacheStrategy(DiskCacheStrategy.NONE)).skipMemoryCache(true)).into(new CustomTarget<Bitmap>() {

                @Override 
                public void onLoadCleared(Drawable drawable) {
                }


                public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                    ArtPreviewActivity.this.imageView.setImageBitmap(bitmap);
                    ArtPreviewActivity.this.bitmap = bitmap;
                }
            });
        }
    }

    private void inItUI() {
        this.imageView = (ImageView) findViewById(R.id.img_final);
        findViewById(R.id.btnMessage).setOnClickListener(this);
        this.relativeLayout = (RelativeLayout) findViewById(R.id.relativeShare);
        findViewById(R.id.btnBack).setOnClickListener(this);
        findViewById(R.id.btn_home).setOnClickListener(this);
        findViewById(R.id.btnShareMore).setOnClickListener(this);
        findViewById(R.id.btnInstagram).setOnClickListener(this);
        findViewById(R.id.btnFacebook).setOnClickListener(this);
        findViewById(R.id.btnTelegram).setOnClickListener(this);
        findViewById(R.id.btnGmail).setOnClickListener(this);
        findViewById(R.id.btnWhatsApp).setOnClickListener(this);
        findViewById(R.id.btnTwitter).setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onClick(View view) {
        if (view != null) {
            if(view.getId()==R.id.btnBack){
                super.onBackPressed();
            }else if(view.getId()==R.id.btnFacebook){
                sharePhoto(PacList.FACE);
            }else if(view.getId()==R.id.btnGmail){
                sharePhoto(PacList.GMAIL);
            }else if(view.getId()==R.id.btnInstagram){
                sharePhoto(PacList.INSTA);
            }else if(view.getId()==R.id.btnMessage){
                sharePhoto(PacList.MESSAGE);
            }else if(view.getId()==R.id.btnShareMore){
                shareMore(this.uri.getPath());
            }else if(view.getId()==R.id.btnTelegram){
                sharePhoto(PacList.TELEGRAM);
            }else if(view.getId()==R.id.btnTwitter){
                sharePhoto(PacList.TWITTER);
            }else if(view.getId()==R.id.btnWhatsApp){
                sharePhoto(PacList.WHATSAPP);
            }else if(view.getId()==R.id.btn_home){
                Intent intent = new Intent(this, HomeActivity.class);
                intent.setFlags(67108864);
                startActivity(intent);
            }
        }
    }

    public void shareMore(String str) {
        MediaScannerConnection.scanFile(this, new String[]{str}, null, new MediaScannerConnection.OnScanCompletedListener() {
            @Override
            public void onScanCompleted(String path, Uri uri) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("image/*");
                intent.putExtra("android.intent.extra.SUBJECT", str);
                intent.putExtra("android.intent.extra.TITLE", str);
                intent.putExtra("android.intent.extra.STREAM", uri);
                intent.addFlags(524288);
                startActivity(Intent.createChooser(intent, "Share with Friends"));
            }
        });
    }

    public void sharePhoto(final String str) {
        if (isPackageInstalled(this, str)) {
            MediaScannerConnection.scanFile(this, new String[]{this.uri.getPath()}, null, new MediaScannerConnection.OnScanCompletedListener() {

                public void onScanCompleted(String str, Uri uri) {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("image/*");
                    intent.putExtra("android.intent.extra.SUBJECT", str);
                    intent.putExtra("android.intent.extra.TITLE", str);
                    intent.putExtra("android.intent.extra.STREAM", uri);
                    intent.addFlags(524288);
                    intent.setPackage(str);
                    ArtPreviewActivity.this.startActivity(intent);
                }
            });
            return;
        }
        Toast.makeText(this, "Can't find this App, please download and try it again", 0).show();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://details?id=" + str));
        startActivity(intent);
    }

    public static boolean isPackageInstalled(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 128);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
