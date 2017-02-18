package kz.kineu.mycollege.Acitivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import kz.kineu.mycollege.R;

public class ImagesActivity extends AppCompatActivity {

    private ImageLoader mLoader;
    private DisplayImageOptions mOptions;
    private boolean isActionBarShowed;
    private String url = "http://78.46.123.237:7777";

    {
        mOptions = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).build();
        mLoader = ImageLoader.getInstance();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isActionBarShowed = false;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(WindowCompat.FEATURE_ACTION_BAR_OVERLAY);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#8C000000")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_images);

        Intent intent = getIntent();
        String imgurl = intent.getStringExtra("imgurl");
        ImageView imageView = (ImageView) findViewById(R.id.activity_images);
        mLoader.displayImage(url+imgurl, imageView, mOptions);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isActionBarShowed) {
                    getSupportActionBar().hide();
                    isActionBarShowed = false;
                } else {
                    getSupportActionBar().show();
                    isActionBarShowed = true;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
