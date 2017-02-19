package kz.kineu.mycollege.Acitivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import kz.kineu.mycollege.Entities.Link;
import kz.kineu.mycollege.R;


public class ImagesActivity extends AppCompatActivity {

    private ImageLoader mLoader;
    private DisplayImageOptions mOptions;
    private boolean isActionBarShowed;
    private String url = "http://78.46.123.237:7777";
    private int currentimg;
    private List<Link> imgurl;
    private ImageView imageView;
    {
        mOptions = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).build();
        mLoader = ImageLoader.getInstance();
        currentimg = 0;
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
        imgurl = intent.getParcelableArrayListExtra("imgurl");
        imageView = (ImageView) findViewById(R.id.activity_images);
        currentimg = intent.getIntExtra("selectedimg",0);
        mLoader.displayImage(url+imgurl.get(currentimg).getLink(), imageView, mOptions);
        final GestureDetectorCompat gd = new GestureDetectorCompat(ImagesActivity.this,new GestureListener());
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gd.onTouchEvent(event);
                return true;
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



    private static final int DISTANCE = 100;
    private static final int VELOCITY = 200;

    private class GestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if(isActionBarShowed) {
                getSupportActionBar().hide();
                isActionBarShowed = false;
            } else {
                getSupportActionBar().show();
                isActionBarShowed = true;
            }
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getX() - e2.getX() > DISTANCE && Math.abs(velocityX) > VELOCITY) {
                if(currentimg+1<imgurl.size()) {
                    currentimg++;
                    mLoader.displayImage(url + imgurl.get(currentimg).getLink(), imageView, mOptions);
                }
                return true;
            }  else if (e2.getX() - e1.getX() > DISTANCE && Math.abs(velocityX) > VELOCITY) {
                if(currentimg-1>-1) {
                    currentimg--;
                    mLoader.displayImage(url + imgurl.get(currentimg).getLink(), imageView, mOptions);
                }
                return true;
            }
            return false;
        }
    }
}
