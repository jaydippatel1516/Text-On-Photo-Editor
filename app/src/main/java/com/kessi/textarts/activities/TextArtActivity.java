package com.kessi.textarts.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.android.material.tabs.TabLayout;

import com.kessi.textarts.R;
import com.kessi.textarts.adapter.ColorAdapter;
import com.kessi.textarts.adapter.ViewPagerAdapter;
import com.kessi.textarts.dialog.ExitDialog;
import com.kessi.textarts.dialog.LoadingDialog;
import com.kessi.textarts.dialog.TextEditorDialog;
import com.kessi.textarts.fragments.ColorFragment;
import com.kessi.textarts.fragments.TextEditorFragment;
import com.kessi.textarts.fragments.photoedit.EmojiFragment;
import com.kessi.textarts.fragments.photoedit.OverlaysFragment;
import com.kessi.textarts.fragments.photoedit.PhotoFragment;
import com.kessi.textarts.fragments.photoedit.StickerFragment;
import com.kessi.textarts.fragments.photoedit.TuneFragment;
import com.kessi.textarts.fragments.photoedit.stickertext.ChristmasFragment;
import com.kessi.textarts.fragments.photoedit.stickertext.EmoticonsFragment;
import com.kessi.textarts.fragments.photoedit.stickertext.FitnessFragment;
import com.kessi.textarts.fragments.photoedit.stickertext.FoodFragment;
import com.kessi.textarts.fragments.photoedit.stickertext.GeometryFragment;
import com.kessi.textarts.fragments.photoedit.stickertext.HalloweenFragment;
import com.kessi.textarts.fragments.photoedit.stickertext.LoveFragment;
import com.kessi.textarts.fragments.photoedit.stickertext.MotivationFragment;
import com.kessi.textarts.fragments.photoedit.stickertext.NativeFragment;
import com.kessi.textarts.fragments.photoedit.stickertext.PhrasesFragment;
import com.kessi.textarts.fragments.photoedit.stickertext.SayingsFragment;
import com.kessi.textarts.fragments.photoedit.stickertext.SummerFragment;
import com.kessi.textarts.fragments.photoedit.stickertext.TravelFragment;
import com.kessi.textarts.fragments.photoedit.stickertext.WinterFragment;
import com.kessi.textarts.fragments.textedit.FontStyleFragment;
import com.kessi.textarts.fragments.textedit.FormatTextFragment;
import com.kessi.textarts.fragments.textedit.HightLightTextFragment;
import com.kessi.textarts.fragments.textedit.ShadowTextFragment;
import com.kessi.textarts.fragments.textedit.SpacingTextFragment;
import com.kessi.textarts.interfaces.ColorFragmentListener;
import com.kessi.textarts.interfaces.FontFragmentListener;
import com.kessi.textarts.interfaces.FormatTextFragmentListener;
import com.kessi.textarts.interfaces.HightLightFragmentListener;
import com.kessi.textarts.interfaces.OverlaysFragmentListener;
import com.kessi.textarts.interfaces.OverplayListener;
import com.kessi.textarts.interfaces.ShadowFragmentListener;
import com.kessi.textarts.interfaces.SpacingFragmentListener;
import com.kessi.textarts.interfaces.StrokeFragmentListener;
import com.kessi.textarts.photoeditor.OnPhotoEditorListener;
import com.kessi.textarts.photoeditor.PhotoEditor;
import com.kessi.textarts.photoeditor.PhotoEditorView;
import com.kessi.textarts.photoeditor.RoundFrameLayout;
import com.kessi.textarts.photoeditor.RoundViewDelegate;
import com.kessi.textarts.photoeditor.SaveSettings;
import com.kessi.textarts.photoeditor.StrokeTextView;
import com.kessi.textarts.photoeditor.ViewType;
import com.kessi.textarts.unit.BitmapProcess;
import com.kessi.textarts.unit.ColorFilterGenerator;
import com.kessi.textarts.unit.ViewAnimation;
import com.kessi.textarts.util.AdManager;
import com.kessi.textarts.util.Utils;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class TextArtActivity extends AppCompatActivity implements View.OnClickListener, TextEditorFragment.TextFragmentListener, EmojiFragment.EmojiListener, PhotoFragment.PhotoListener, StickerFragment.StickerFragmentListener, TuneFragment.TuneFragmentListener, ColorFragmentListener, FontFragmentListener, FormatTextFragmentListener, HightLightFragmentListener, OverlaysFragmentListener, OverplayListener, ShadowFragmentListener, SpacingFragmentListener, StrokeFragmentListener, OnPhotoEditorListener {
    private RoundFrameLayout border;
    private int brightnessFinal = 0;
    private RoundFrameLayout btnColorPicker;
    public ImageView btnRedo;
    public ImageView btnSave;
    public ImageView btnUndo;
    private int colorBackground;
    private int colorTextShadow = Color.parseColor("#000000");
    private int constrantFinal = 1;
    private int countMain = 0;
    private int countOverplay = 0;
    private int countPhoto = 0;
    private int countText = 0;
    public int currentTabTool = 0;
    ExitDialog discardDialog;
    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private int hueFinal = 1;
    public ImageView imageViewMain;
    private PhotoEditorView imgPhotoEditor;
    private ChristmasFragment mChristmasFragment;
    private EmoticonsFragment mEmoticonsFragment;
    private FitnessFragment mFitnessFragment;
    private FoodFragment mFoodFragment;
    private GeometryFragment mGeometryFragment;
    private HalloweenFragment mHalloweenFragment;
    private LoveFragment mLoveFragment;
    private MotivationFragment mMotivationFragment;
    private NativeFragment mNativeFragment;
    public PhotoEditor mPhotoEditor;
    private PhrasesFragment mPhrasesFragment;
    private SayingsFragment mSayingFragment;
    private SummerFragment mSummerFragment;
    private TravelFragment mTravelFragment;
    private WinterFragment mWinterFragment;
    public int numberAddedView;
    private String opticalBackground = "66";
    private int opticalText = 255;
    public ProgressBar progressBarLoading;
    private int rRadius = 0;
    private int rY = 0;
    private RecyclerView recyclerPhotoColor;
    public Bitmap resourceGraphic;
    private RelativeLayout rlColorPhoto;
    public RelativeLayout rlMainTool;
    private RelativeLayout rlPhotoTools;
    private RelativeLayout rlTextTool;
    private int saturationFinal = 1;
    private SeekBar sbRotatePhoto;
    private SeekBar sbTranparencyPhoto;
    private int styleText;
    private TabLayout tabLayoutTextTools;
    public TabLayout tablayoutImageTools;
    private StrokeTextView textViewMain;
    public View viewMain;
    private ViewPager viewPagerImageTools;
    private ViewPager viewPagerTextTools;


    @Override 
    public void onStartViewChangeListener(ViewType viewType) {
    }

    @Override 
    public void onStopViewChangeListener(ViewType viewType) {
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_text_art);
        addControls();
        addPhotoColor();
        setImageTranparency();
        PhotoEditor build = new PhotoEditor.Builder(this, this.imgPhotoEditor).setPinchTextScalable(true).setDefaultEmojiTypeface(Typeface.createFromAsset(getAssets(), "font/font5.TTF")).build();
        this.mPhotoEditor = build;
        build.setOnPhotoEditorListener(this);
        getData();
        setupViewPager(this.viewPagerTextTools);
        this.tabLayoutTextTools.setupWithViewPager(this.viewPagerTextTools);
        setupViewPagerImageTools(this.viewPagerImageTools);
        this.tablayoutImageTools.setupWithViewPager(this.viewPagerImageTools);
        setupTabIconsTextTool();
        setupTabIconsImageTool();
    }
    private void setImageTranparency() {
        this.sbTranparencyPhoto.setMax(255);
        this.sbTranparencyPhoto.setProgress(255);
        this.sbTranparencyPhoto.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (TextArtActivity.this.imageViewMain != null) {
                    TextArtActivity.this.imageViewMain.setImageAlpha(i);
                }
            }
        });
        this.sbRotatePhoto.setMax(360);
        this.sbRotatePhoto.setProgress(0);
        this.sbRotatePhoto.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (TextArtActivity.this.viewMain != null) {
                    TextArtActivity.this.viewMain.setRotation((float) i);
                }
            }
        });
    }

    private void addPhotoColor() {
        this.recyclerPhotoColor.setHasFixedSize(true);
        this.recyclerPhotoColor.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.recyclerPhotoColor.setAdapter(new ColorAdapter(this, new ColorAdapter.ColorAdapterListener() {
            @Override
            public void onColorItemSelected(int i) {
                BitmapProcess.changeBitmapColor(resourceGraphic, imageViewMain, i);
                
            }
        }));
        this.btnColorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder.with(TextArtActivity.this).setTitle("Select color").wheelType(ColorPickerView.WHEEL_TYPE.FLOWER).density(12).setPositiveButton("OK", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int i, Integer[] allColors) {
                        BitmapProcess.changeBitmapColor(resourceGraphic, imageViewMain, i);

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).build().show();
            }
        });
    }
    private void setupTabIconsTextTool() {
        TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        textView.setText("Font");
        textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_font_style, 0, 0);
        ((TabLayout.Tab) Objects.requireNonNull(this.tabLayoutTextTools.getTabAt(0))).setCustomView(textView);
        TextView textView2 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        textView2.setText("Format");
        textView2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_font_format, 0, 0);
        ((TabLayout.Tab) Objects.requireNonNull(this.tabLayoutTextTools.getTabAt(1))).setCustomView(textView2);
        TextView textView3 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        textView3.setText("Color");
        textView3.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_font_color, 0, 0);
        ((TabLayout.Tab) Objects.requireNonNull(this.tabLayoutTextTools.getTabAt(2))).setCustomView(textView3);
        TextView textView4 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        textView4.setText("Highlight");
        textView4.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_font_bg, 0, 0);
        ((TabLayout.Tab) Objects.requireNonNull(this.tabLayoutTextTools.getTabAt(3))).setCustomView(textView4);
        TextView textView5 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        textView5.setText("Shadow");
        textView5.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_font_shadow, 0, 0);
        ((TabLayout.Tab) Objects.requireNonNull(this.tabLayoutTextTools.getTabAt(4))).setCustomView(textView5);
        TextView textView6 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        textView6.setText("Spacing");
        textView6.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_font_spacing, 0, 0);
        ((TabLayout.Tab) Objects.requireNonNull(this.tabLayoutTextTools.getTabAt(5))).setCustomView(textView6);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        FontStyleFragment fontStyleFragment = new FontStyleFragment();
        fontStyleFragment.setListener(this);
        viewPagerAdapter.addFrag(fontStyleFragment, "Font");
        FormatTextFragment formatTextFragment = new FormatTextFragment();
        viewPagerAdapter.addFrag(formatTextFragment, "Format");
        formatTextFragment.setListener(this);
        ColorFragment colorFragment = new ColorFragment();
        viewPagerAdapter.addFrag(colorFragment, "Color");
        colorFragment.setListener(this);
        HightLightTextFragment hightLightTextFragment = new HightLightTextFragment();
        viewPagerAdapter.addFrag(hightLightTextFragment, "Highlight");
        hightLightTextFragment.setListener(this);
        ShadowTextFragment shadowTextFragment = new ShadowTextFragment();
        viewPagerAdapter.addFrag(shadowTextFragment, "Shadow");
        shadowTextFragment.setListener(this);
        SpacingTextFragment spacingTextFragment = new SpacingTextFragment();
        viewPagerAdapter.addFrag(spacingTextFragment, "Spacing");
        spacingTextFragment.setListener(this);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(7);
    }

    private void setupTabIconsImageTool() {
        TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        textView.setText("Add");
        textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_add_text, 0, 0);
        ((TabLayout.Tab) Objects.requireNonNull(this.tablayoutImageTools.getTabAt(0))).setCustomView(textView);
        TextView textView2 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        textView2.setText("Sticker");
        textView2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_add_sticker, 0, 0);
        ((TabLayout.Tab) Objects.requireNonNull(this.tablayoutImageTools.getTabAt(1))).setCustomView(textView2);
        TextView textView3 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        textView3.setText("Overlays");
        textView3.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_add_overlay, 0, 0);
        ((TabLayout.Tab) Objects.requireNonNull(this.tablayoutImageTools.getTabAt(2))).setCustomView(textView3);
        TextView textView4 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        textView4.setText("Emoji");
        textView4.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_add_emoji, 0, 0);
        ((TabLayout.Tab) Objects.requireNonNull(this.tablayoutImageTools.getTabAt(3))).setCustomView(textView4);
        TextView textView5 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        textView5.setText("Photo");
        textView5.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_add_photo, 0, 0);
        ((TabLayout.Tab) Objects.requireNonNull(this.tablayoutImageTools.getTabAt(4))).setCustomView(textView5);
        TextView textView6 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        textView6.setText("Tune");
        textView6.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_add_tune, 0, 0);
        ((TabLayout.Tab) Objects.requireNonNull(this.tablayoutImageTools.getTabAt(4 + 1))).setCustomView(textView6);
        if ((getResources().getConfiguration().screenLayout & 15) == 4) {
            this.tablayoutImageTools.setTabMode(1);
        }
        this.tablayoutImageTools.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new TabLayout.OnTabSelectedListener() {
            

            @Override 
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override 
            public void onTabSelected(TabLayout.Tab tab) {
                TextArtActivity.this.currentTabTool = tab.getPosition();
                if (tab.getPosition() != 0) {
                    tab.getPosition();
                } else if (TextArtActivity.this.numberAddedView < 6) {
                    TextArtActivity.this.mPhotoEditor.addText(TextArtActivity.this.getString(R.string.double_tap), ContextCompat.getColor(TextArtActivity.this, 17170443));
                } else {
                    Toast.makeText(TextArtActivity.this, (int) R.string.max_item, 0).show();
                }
            }

            @Override 
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    if (TextArtActivity.this.numberAddedView < 6) {
                        TextArtActivity.this.mPhotoEditor.addText(TextArtActivity.this.getString(R.string.double_tap), ContextCompat.getColor(TextArtActivity.this, 17170443));
                    } else {
                        Toast.makeText(TextArtActivity.this, (int) R.string.max_item, 0).show();
                    }
                }
            }
        });
    }

    private void setupViewPagerImageTools(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFrag(new Fragment(), "Add");
        StickerFragment stickerFragment = new StickerFragment();
        viewPagerAdapter.addFrag(stickerFragment, "Sticker");
        stickerFragment.setStickerFragmentListener(this);
        OverlaysFragment overlaysFragment = new OverlaysFragment();
        viewPagerAdapter.addFrag(overlaysFragment, "Overlays");
        overlaysFragment.setListener(this);
        EmojiFragment emojiFragment = new EmojiFragment();
        viewPagerAdapter.addFrag(emojiFragment, "Emoji");
        emojiFragment.setEmojiListener(this);
        PhotoFragment photoFragment = new PhotoFragment();
        viewPagerAdapter.addFrag(photoFragment, "Photo");
        photoFragment.setPhotoListener(this);
        TuneFragment tuneFragment = new TuneFragment();
        viewPagerAdapter.addFrag(tuneFragment, "Tunes");
        tuneFragment.setTuneFragmentListener(this);
        viewPager.setAdapter(viewPagerAdapter);
        Log.d("@@", "setupViewPagerImageTools " + ((PagerAdapter) Objects.requireNonNull(viewPager.getAdapter())).getCount());
    }

    private void getData() {
        SystemClock.currentThreadTimeMillis();
        Intent intent = getIntent();
        Uri data = intent.getData();
        if (data != null) {
            try {
                Glide.with((FragmentActivity) this).load(BitmapProcess.handleSamplingAndRotationBitmap(this, data)).into(this.imgPhotoEditor.getSource());
                this.mPhotoEditor.addText(getString(R.string.double_tap), ContextCompat.getColor(this, 17170443));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int intExtra = intent.getIntExtra("SampleBackground", 0);
        if (intExtra != 0) {
            Glide.with((FragmentActivity) this).load(Integer.valueOf(intExtra)).into(this.imgPhotoEditor.getSource());
            this.mPhotoEditor.addText(getString(R.string.double_tap), ContextCompat.getColor(this, 17170443));
        }
    }

    private void addControls() {
        this.btnUndo = (ImageView) findViewById(R.id.btnUndo);
        this.btnRedo = (ImageView) findViewById(R.id.btnRedo);
        this.btnSave = (ImageView) findViewById(R.id.btnSave);
        this.rlTextTool = (RelativeLayout) findViewById(R.id.rl_text_tool);
        this.rlMainTool = (RelativeLayout) findViewById(R.id.rl_main_tool);
        this.rlPhotoTools = (RelativeLayout) findViewById(R.id.rl_photo_tool);
        this.rlColorPhoto = (RelativeLayout) findViewById(R.id.rl_color_photo);
        this.imgPhotoEditor = (PhotoEditorView) findViewById(R.id.imgPhotoEditor);
        this.viewPagerTextTools = (ViewPager) findViewById(R.id.viewpagerTextTools);
        this.tabLayoutTextTools = (TabLayout) findViewById(R.id.tablayoutTextTools);
        this.viewPagerImageTools = (ViewPager) findViewById(R.id.viewpagerImageTools);
        this.tablayoutImageTools = (TabLayout) findViewById(R.id.tablayoutImageTools);
        this.progressBarLoading = (ProgressBar) findViewById(R.id.progress_circular_loading);
        ChristmasFragment christmasFragment = new ChristmasFragment();
        this.mChristmasFragment = christmasFragment;
        christmasFragment.setOverplayListener(this);
        EmoticonsFragment emoticonsFragment = new EmoticonsFragment();
        this.mEmoticonsFragment = emoticonsFragment;
        emoticonsFragment.setOverplayListener(this);
        FitnessFragment fitnessFragment = new FitnessFragment();
        this.mFitnessFragment = fitnessFragment;
        fitnessFragment.setOverplayListener(this);
        FoodFragment foodFragment = new FoodFragment();
        this.mFoodFragment = foodFragment;
        foodFragment.setOverplayListener(this);
        GeometryFragment geometryFragment = new GeometryFragment();
        this.mGeometryFragment = geometryFragment;
        geometryFragment.setOverplayListener(this);
        HalloweenFragment halloweenFragment = new HalloweenFragment();
        this.mHalloweenFragment = halloweenFragment;
        halloweenFragment.setOverplayListener(this);
        LoveFragment loveFragment = new LoveFragment();
        this.mLoveFragment = loveFragment;
        loveFragment.setOverplayListener(this);
        MotivationFragment motivationFragment = new MotivationFragment();
        this.mMotivationFragment = motivationFragment;
        motivationFragment.setOverplayListener(this);
        NativeFragment nativeFragment = new NativeFragment();
        this.mNativeFragment = nativeFragment;
        nativeFragment.setOverplayListener(this);
        PhrasesFragment phrasesFragment = new PhrasesFragment();
        this.mPhrasesFragment = phrasesFragment;
        phrasesFragment.setOverplayListener(this);
        SayingsFragment sayingsFragment = new SayingsFragment();
        this.mSayingFragment = sayingsFragment;
        sayingsFragment.setOverplayListener(this);
        SummerFragment summerFragment = new SummerFragment();
        this.mSummerFragment = summerFragment;
        summerFragment.setOverplayListener(this);
        TravelFragment travelFragment = new TravelFragment();
        this.mTravelFragment = travelFragment;
        travelFragment.setOverplayListener(this);
        WinterFragment winterFragment = new WinterFragment();
        this.mWinterFragment = winterFragment;
        winterFragment.setOverplayListener(this);
        ((ImageView) findViewById(R.id.btnBack)).setOnClickListener(this);
        ((ImageView) findViewById(R.id.btnBackTextTools)).setOnClickListener(this);
        this.btnUndo.setOnClickListener(this);
        this.btnRedo.setOnClickListener(this);
        this.btnSave.setOnClickListener(this);
        this.imgPhotoEditor.setOnClickListener(this);
        ((ImageView) findViewById(R.id.btnAddText_toolbar)).setOnClickListener(this);
        this.recyclerPhotoColor = (RecyclerView) findViewById(R.id.recycler_color_photo);
        this.sbTranparencyPhoto = (SeekBar) findViewById(R.id.seekbar_photo_transparency);
        this.sbRotatePhoto = (SeekBar) findViewById(R.id.seekbar_rotate);
        this.btnColorPicker = (RoundFrameLayout) findViewById(R.id.btn_picker_color_photo);
    }

    public void onClick(View view) {
        if(view.getId()==R.id.btnAddText_toolbar){
            if (this.numberAddedView < 6) {
                this.mPhotoEditor.addText(getString(R.string.double_tap), ContextCompat.getColor(this, 17170443));
                return;
            } else {
                Toast.makeText(this, (int) R.string.max_item, 0).show();
                return;
            }
        }else if(view.getId()==R.id.btnBack){
            discardDialog = new ExitDialog(this);
            ((Window) Objects.requireNonNull(this.discardDialog.getWindow())).setBackgroundDrawable(new ColorDrawable(0));
            try {
                this.discardDialog.show();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }else if(view.getId()==R.id.btnBackTextTools){
            if (this.countMain == 0) {
                ViewAnimation.animationView(this.rlMainTool);
                this.rlTextTool.setVisibility(View.GONE);
                this.rlPhotoTools.setVisibility(View.GONE);
                ((TabLayout.Tab) Objects.requireNonNull(this.tablayoutImageTools.getTabAt(1))).select();
                this.countText = 0;
                this.countMain++;
                this.countOverplay = 0;
                this.countPhoto = 0;
                return;
            }
        }else if(view.getId()==R.id.btnRedo){
            mPhotoEditor.redo();
        }else if(view.getId()==R.id.btnUndo){
            mPhotoEditor.undo();
        }else if(view.getId()==R.id.btnSave){
            saveTextArt();
        }else if(view.getId()==R.id.imgPhotoEditor){
            if (this.countMain == 0) {
                this.mPhotoEditor.clearHelperBox();
                ViewAnimation.animationView(this.rlMainTool);
                this.rlTextTool.setVisibility(View.GONE);
                this.rlPhotoTools.setVisibility(View.GONE);
                if (this.currentTabTool == 0) {
                    this.currentTabTool = 1;
                }
                ((TabLayout.Tab) Objects.requireNonNull(this.tablayoutImageTools.getTabAt(this.currentTabTool))).select();
                this.countText = 0;
                this.countMain++;
                this.countPhoto = 0;
                this.countOverplay = 0;
                return;
            }
        }
    }

    private void saveTextArt() {
        final LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.show();
        File outputMediaFile = Utils.getOutputMediaFile();
        Log.e("outputMediaFile", "" + outputMediaFile);
        if (outputMediaFile != null) {
            try {
                Boolean valueOf = Boolean.valueOf(outputMediaFile.createNewFile());
            } catch (IOException e) {
                e.printStackTrace();
                loadingDialog.dismiss();
                return;
            }
        }
        SaveSettings build = new SaveSettings.Builder().setClearViewsEnabled(false).setTransparencyEnabled(true).build();
        Log.e("build", "" + build);
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_MEDIA_IMAGES") != 0 || outputMediaFile == null) {
            this.mPhotoEditor.saveAsFile(outputMediaFile.getAbsolutePath(), build, new PhotoEditor.OnSaveListener() {
                

                @Override 
                public void onFailure(Exception exc) {
                    loadingDialog.dismiss();
                }

                @Override 
                public void onSuccess(String str) {
                    loadingDialog.dismiss();
                    Uri fromFile = Uri.fromFile(new File(str));
                    Log.e("fromFile", "" + fromFile);
                    Intent intent = new Intent(TextArtActivity.this, ArtPreviewActivity.class);
                    intent.setData(fromFile);
                    AdManager.startActivity(TextArtActivity.this, intent, 0);
                }
            });
        } else {
            this.mPhotoEditor.saveAsFile(outputMediaFile.getAbsolutePath(), build, new PhotoEditor.OnSaveListener() {
                

                @Override 
                public void onFailure(Exception exc) {
                    loadingDialog.dismiss();
                }

                @Override 
                public void onSuccess(String str) {
                    loadingDialog.dismiss();
                    Uri fromFile = Uri.fromFile(new File(str));
                    Log.e("fromFile", "" + fromFile);
                    Intent intent = new Intent(TextArtActivity.this, ArtPreviewActivity.class);
                    intent.setData(fromFile);
                    AdManager.startActivity(TextArtActivity.this, intent, 0);
                }
            });
        }
    }

    @Override 
    public void onEditTextChangeListener(View view, String str, int i) {
        showTextEditorFragment(str);
    }

    private void showTextEditorFragment(String str) {
        TextEditorDialog textEditorDialog = new TextEditorDialog(this, str);
        textEditorDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                View currentFocus = getCurrentFocus();
                if (currentFocus != null) {
                    ((InputMethodManager) getSystemService(android.content.Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
                }
                if (rlMainTool != null && rlMainTool.getVisibility() == 0) {
                    ((TabLayout.Tab) Objects.requireNonNull(tablayoutImageTools.getTabAt(1))).select();
                }
            }
        });
        textEditorDialog.setTextListener(this);
        textEditorDialog.show();
    }
    
    
    @Override 
    public void onAdded(StrokeTextView strokeTextView, RoundFrameLayout roundFrameLayout) {
        this.textViewMain = strokeTextView;
        this.border = roundFrameLayout;
    }

    @Override 
    public void onClickGetEditTextChangeListener(StrokeTextView strokeTextView, RoundFrameLayout roundFrameLayout) {
        this.textViewMain = strokeTextView;
        this.border = roundFrameLayout;
        if (this.countText == 0) {
            ViewAnimation.animationView(this.rlTextTool);
            this.rlMainTool.setVisibility(View.GONE);
            this.rlPhotoTools.setVisibility(View.GONE);
            this.countMain = 0;
            this.countText++;
            this.countPhoto = 0;
            this.countOverplay = 0;
        }
    }

    @Override 
    public void onClickGetImageViewListener(ImageView imageView, View view) {
        this.imageViewMain = imageView;
        this.viewMain = view;
        if (this.countPhoto == 0) {
            ViewAnimation.animationView(this.rlPhotoTools);
            this.rlMainTool.setVisibility(View.GONE);
            this.rlTextTool.setVisibility(View.GONE);
            this.rlColorPhoto.setVisibility(View.GONE);
            this.countPhoto++;
            this.countMain = 0;
            this.countText = 0;
            this.countOverplay = 0;
        }
    }

    @Override 
    public void onClickGetGraphicViewListener(ImageView imageView, View view, View view2) {
        this.imageViewMain = imageView;
        this.viewMain = view;
        if (this.countOverplay == 0) {
            ViewAnimation.animationView(this.rlPhotoTools);
            this.rlMainTool.setVisibility(View.GONE);
            this.rlTextTool.setVisibility(View.GONE);
            this.rlColorPhoto.setVisibility(View.VISIBLE);
            this.countPhoto = 0;
            this.countOverplay++;
            this.countMain = 0;
            this.countText = 0;
        }
    }

    @Override 
    public void onClickGetBitmaoOverlay(Bitmap bitmap) {
        this.resourceGraphic = bitmap;
    }

    @Override 
    public void onAddViewListener(ViewType viewType, int i) {
        this.numberAddedView = i;
    }

    @Override 
    public void onRemoveViewListener(int i) {
        this.numberAddedView = i;
    }

    @Override 
    public void onRemoveViewListener(ViewType viewType, int i) {
        ViewAnimation.animationView(this.rlMainTool);
        this.rlColorPhoto.setVisibility(View.GONE);
        this.rlTextTool.setVisibility(View.GONE);
        this.rlPhotoTools.setVisibility(View.GONE);
        if (this.currentTabTool == 0) {
            this.currentTabTool = 1;
        }
        ((TabLayout.Tab) Objects.requireNonNull(this.tablayoutImageTools.getTabAt(this.currentTabTool))).select();
        this.countText = 0;
        this.countMain++;
        this.countOverplay = 0;
        this.countPhoto = 0;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag("QUOTES") != null) {
            getSupportFragmentManager().popBackStack("EDIT", 0);
        } else if (getSupportFragmentManager().findFragmentByTag("EDIT") != null) {
            this.fragmentManager.popBackStack((String) null, 1);
        } else {
            ExitDialog exitDialog = new ExitDialog(this);
            ((Window) Objects.requireNonNull(exitDialog.getWindow())).setBackgroundDrawable(new ColorDrawable(0));
            exitDialog.show();
        }
    }

    @Override 
    public void onTextAlign(int i) {
        if (i == 1) {
            this.textViewMain.setGravity(3);
        } else if (i == 2) {
            this.textViewMain.setGravity(17);
        } else if (i == 3) {
            this.textViewMain.setGravity(5);
            StrokeTextView strokeTextView = this.textViewMain;
            strokeTextView.setTypeface(strokeTextView.getTypeface(), 2);
        }
    }

    @Override 
    public void onTextStyle(int i) {
        switch (i) {
            case 1:
                StrokeTextView strokeTextView = this.textViewMain;
                strokeTextView.setTypeface(strokeTextView.getTypeface(), 3);
                this.styleText = i;
                return;
            case 2:
                StrokeTextView strokeTextView2 = this.textViewMain;
                strokeTextView2.setTypeface(strokeTextView2.getTypeface(), 1);
                this.styleText = i;
                return;
            case 3:
                StrokeTextView strokeTextView3 = this.textViewMain;
                strokeTextView3.setTypeface(strokeTextView3.getTypeface(), 2);
                this.styleText = i;
                return;
            case 4:
                StrokeTextView strokeTextView4 = this.textViewMain;
                strokeTextView4.setTypeface(Typeface.create(strokeTextView4.getTypeface(), 0));
                this.styleText = i;
                return;
            case 5:
                this.textViewMain.setAllCaps(true);
                return;
            case 6:
                this.textViewMain.setAllCaps(false);
                return;
            default:
                return;
        }
    }

    @Override 
    public void onTextSize(int i) {
        this.textViewMain.setTextSize((float) i);
        Log.d("TEXTTTTT", "onTextSize " + i);
    }

    @Override 
    public void onTextPadding(int i) {
        this.border.setPadding(i, i, i, i);
    }

    @Override 
    public void onFontSelected(String str) {
        AssetManager assets = getAssets();
        Typeface createFromAsset = Typeface.createFromAsset(assets, "font/" + str);
        int i = this.styleText;
        if (i == 1) {
            this.textViewMain.setTypeface(createFromAsset, 3);
        } else if (i == 2) {
            this.textViewMain.setTypeface(createFromAsset, 1);
        } else if (i != 3) {
            this.textViewMain.setTypeface(createFromAsset, 0);
        } else {
            this.textViewMain.setTypeface(createFromAsset, 2);
        }
    }

    @Override 
    public void onHightLightColorSelected(int i) {
        this.colorBackground = i;
        String format = String.format("%06X", Integer.valueOf(i & ViewCompat.MEASURED_SIZE_MASK));
        RoundViewDelegate delegate = this.border.getDelegate();
        delegate.setBackgroundColor(Color.parseColor("#" + this.opticalBackground + format));
    }

    @Override 
    public void onHightLightColorOpacityChangeListerner(String str) {
        String format = String.format("%06X", Integer.valueOf(this.colorBackground & ViewCompat.MEASURED_SIZE_MASK));
        this.opticalBackground = str;
        RoundViewDelegate delegate = this.border.getDelegate();
        delegate.setBackgroundColor(Color.parseColor("#" + this.opticalBackground + format));
    }

    @Override 
    public void onHighLightRadius(int i) {
        this.border.getDelegate().setCornerRadius(i);
    }

    @Override 
    public void onColorSelected(int i) {
        this.colorTextShadow = i;
        this.textViewMain.getPaint().setShader(null);
        this.textViewMain.setTextColor(i);
        StrokeTextView strokeTextView = this.textViewMain;
        strokeTextView.setTextColor(strokeTextView.getTextColors().withAlpha(this.opticalText));
    }

    @Override 
    public void onColorOpacityChangeListerner(int i) {
        this.opticalText = i;
        StrokeTextView strokeTextView = this.textViewMain;
        strokeTextView.setTextColor(strokeTextView.getTextColors().withAlpha(i));
    }

    @Override 
    public void onLineHeight(int i) {
        setLineGeight(this.textViewMain, i);
    }

    private void setLineGeight(TextView textView, int i) {
        textView.setLineSpacing((float) (dpToPixel((float) i) - textView.getPaint().getFontMetricsInt(null)), 1.0f);
    }

    public int dpToPixel(float f) {
        return (int) (f * (((float) getResources().getDisplayMetrics().densityDpi) / 160.0f));
    }

    @Override 
    public void onSpacingLetter(float f) {
        this.textViewMain.setLetterSpacing(f);
    }

    @Override 
    public void onStrokeColorSelected(int i) {
        this.textViewMain.setStrokeColor(i);
    }

    @Override 
    public void onStrokeWidthChangeListener(int i) {
        this.textViewMain.setStrokeWidth(i);
    }

    @Override 
    public void onDyShadowChangeListener(int i) {
        this.rY = i;
        this.textViewMain.setStrokeWidth(0);
        float f = (float) i;
        this.textViewMain.setShadowLayer((float) this.rRadius, f, f, this.colorTextShadow);
    }

    @Override 
    public void onRadiusChangeListener(int i) {
        this.rRadius = i;
        this.textViewMain.setStrokeWidth(0);
        float f = (float) this.rY;
        this.textViewMain.setShadowLayer((float) this.rRadius, f, f, this.colorTextShadow);
    }

    @Override 
    public void onShadowColorSelected(int i) {
        this.colorTextShadow = i;
        this.textViewMain.setStrokeWidth(0);
        float f = (float) this.rY;
        this.textViewMain.setShadowLayer((float) this.rRadius, f, f, this.colorTextShadow);
    }

    @Override 
    public void onEmojiClick(String str) {
        if (this.numberAddedView < 6) {
            this.mPhotoEditor.addEmoji(str);
        } else {
            Toast.makeText(this, (int) R.string.max_item, 0).show();
        }
    }

    @Override 
    public void onPhotoClick(String str) {
        if (this.numberAddedView < 6) {
            this.mPhotoEditor.addImage(BitmapProcess.getBitmapFromLocalPath(str, 4));
        } else {
            Toast.makeText(this, (int) R.string.max_item, 0).show();
        }
    }

    @Override 
    public void onStickerFragmentClick(Bitmap bitmap) {
        if (this.numberAddedView < 6) {
            this.mPhotoEditor.addImage(bitmap);
        } else {
            Toast.makeText(this, (int) R.string.max_item, 0).show();
        }
    }

    @Override 
    public void onOverplayClick(Bitmap bitmap) {
        int i;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i2 = 300;
        if (width > height) {
            i = (int) ((300.0f / ((float) width)) * ((float) height));
        } else {
            i2 = (int) ((300.0f / ((float) height)) * ((float) width));
            i = 300;
        }
        if (this.numberAddedView < 6) {
            this.mPhotoEditor.addImage(Bitmap.createScaledBitmap(bitmap, i2, i, false));
        } else {
            Toast.makeText(this, (int) R.string.max_item, 0).show();
        }
    }

    @Override 
    public void onPhrases() {
        if (!this.mPhrasesFragment.isAdded()) {
            this.mPhrasesFragment.show(getSupportFragmentManager(), this.mPhrasesFragment.getTag());
        }
    }

    @Override
    public void onFood() {
        if (!this.mFoodFragment.isAdded()) {
            this.mFoodFragment.show(getSupportFragmentManager(), this.mFoodFragment.getTag());
        }
    }

    @Override 
    public void onLove() {
        if (!this.mLoveFragment.isAdded()) {
            this.mLoveFragment.show(getSupportFragmentManager(), this.mLoveFragment.getTag());
        }
    }

    @Override 
    public void onChristmas() {
        if (!this.mChristmasFragment.isAdded()) {
            this.mChristmasFragment.show(getSupportFragmentManager(), this.mChristmasFragment.getTag());
        }
    }

    @Override 
    public void onSayings() {
        if (!this.mSayingFragment.isAdded()) {
            this.mSayingFragment.show(getSupportFragmentManager(), this.mSayingFragment.getTag());
        }
    }

    @Override 
    public void onNative() {
        if (!this.mNativeFragment.isAdded()) {
            this.mNativeFragment.show(getSupportFragmentManager(), this.mNativeFragment.getTag());
        }
    }

    @Override 
    public void onSummer() {
        if (!this.mSummerFragment.isAdded()) {
            this.mSummerFragment.show(getSupportFragmentManager(), this.mSummerFragment.getTag());
        }
    }

    @Override 
    public void onWinter() {
        if (!this.mWinterFragment.isAdded()) {
            this.mWinterFragment.show(getSupportFragmentManager(), this.mWinterFragment.getTag());
        }
    }

    @Override 
    public void onTravel() {
        if (!this.mTravelFragment.isAdded()) {
            this.mTravelFragment.show(getSupportFragmentManager(), this.mTravelFragment.getTag());
        }
    }

    @Override 
    public void onEmoticons() {
        if (!this.mEmoticonsFragment.isAdded()) {
            this.mEmoticonsFragment.show(getSupportFragmentManager(), this.mEmoticonsFragment.getTag());
        }
    }

    @Override 
    public void onMotivation() {
        if (!this.mMotivationFragment.isAdded()) {
            this.mMotivationFragment.show(getSupportFragmentManager(), this.mMotivationFragment.getTag());
        }
    }

    @Override 
    public void onFitness() {
        if (!this.mFitnessFragment.isAdded()) {
            this.mFitnessFragment.show(getSupportFragmentManager(), this.mFitnessFragment.getTag());
        }
    }

    @Override 
    public void onGeometry() {
        if (!this.mGeometryFragment.isAdded()) {
            this.mGeometryFragment.show(getSupportFragmentManager(), this.mGeometryFragment.getTag());
        }
    }

    @Override 
    public void onHalloween() {
        if (!this.mHalloweenFragment.isAdded()) {
            this.mHalloweenFragment.show(getSupportFragmentManager(), this.mHalloweenFragment.getTag());
        }
    }

    @Override
    public void onBrightnessChosse(int i) {
        this.brightnessFinal = i;
        this.imgPhotoEditor.getSource().setColorFilter(ColorFilterGenerator.adjustColor(this.brightnessFinal, this.saturationFinal, this.constrantFinal, this.hueFinal));
    }

    @Override
    public void onConstrastChosse(int i) {
        this.constrantFinal = i;
        this.imgPhotoEditor.getSource().setColorFilter(ColorFilterGenerator.adjustColor(this.brightnessFinal, this.saturationFinal, this.constrantFinal, this.hueFinal));
    }

    @Override
    public void onHueChosee(int i) {
        this.hueFinal = i;
        this.imgPhotoEditor.getSource().setColorFilter(ColorFilterGenerator.adjustColor(this.brightnessFinal, this.saturationFinal, this.constrantFinal, this.hueFinal));
    }

    @Override
    public void onSaturationChosse(int i) {
        this.saturationFinal = i;
        this.imgPhotoEditor.getSource().setColorFilter(ColorFilterGenerator.adjustColor(this.brightnessFinal, this.saturationFinal, this.constrantFinal, this.hueFinal));
    }

    @Override
    public void onText(String str) {
        this.textViewMain.setText(str);
        if (this.countText == 0) {
            ViewAnimation.animationView(this.rlTextTool);
            this.rlMainTool.setVisibility(View.GONE);
            this.rlPhotoTools.setVisibility(View.GONE);
            this.countMain = 0;
            this.countText++;
            this.countPhoto = 0;
            this.countOverplay = 0;
        }
    }

   
    @Override 
    public void onDestroy() {
        if (discardDialog != null) {
            discardDialog.dismiss();
        }
        super.onDestroy();
    }
}
