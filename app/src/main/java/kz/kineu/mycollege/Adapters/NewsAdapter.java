package kz.kineu.mycollege.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import kz.kineu.mycollege.API.NewsAPI;
import kz.kineu.mycollege.Entities.News;
import kz.kineu.mycollege.R;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by ruslan on 11.02.2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    private Context context;
    private final String LOG_TAG = "MyCollegeApp";
    private Integer pagesCount;
    private Integer currentpage;
    private Retrofit client;
    private String url = "http://78.46.123.237:7777";
    private ImageLoader mLoader;
    private DisplayImageOptions options;

    {
        mLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient httpClient = builder.readTimeout(4, TimeUnit.SECONDS).writeTimeout(4,TimeUnit.SECONDS).connectTimeout(4,TimeUnit.SECONDS).build();
        client = new Retrofit.Builder().addConverterFactory(JacksonConverterFactory.create()).baseUrl(url).client(httpClient).build();
        currentpage = 0;
        pagesCount = 0;
        getNOP();
        loadNews();
    }


    public NewsAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.news_item, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.tvReadTitle);
        TextView tvText = (TextView) view.findViewById(R.id.tvText);
        ImageView ivImage = (ImageView) view.findViewById(R.id.ivImage);
        tvTitle.setText(news.getTitle());
        String text = news.getText();
        if(text.length()<51) {
            tvText.setText(text);
        } else {
            tvText.setText(text.substring(0,51)+"...");
        }
        if(!news.getLinks().isEmpty()) {
            mLoader.displayImage(url+ news.getLinks().get(0).getLink(),ivImage, options);
        } else {
            ivImage.setImageResource(R.drawable.ic_no_photos);
        }
        if(isEnd(position) && currentpage<pagesCount) {
            loadNews();
        }

        return view;
    }


    private void loadNews() {
        NewsAPI newsAPI = client.create(NewsAPI.class);
        Call<News[]> call = newsAPI.getNews(Integer.toString(currentpage+1));
        call.enqueue(new Callback<News[]>() {
            @Override
            public void onResponse(Call<News[]> call, Response<News[]> response) {
                if(response.isSuccessful()) {
                    Log.d(LOG_TAG,"NEWS LOAD SUCCESSFUL");
                    addAll((Arrays.asList(response.body())));
                    notifyDataSetChanged();
                    currentpage++;
                    getNOP();
                }
            }

            @Override
            public void onFailure(Call<News[]> call, Throwable t) {
                Log.d(LOG_TAG,"NEWS LOAD ERROR "+t.getMessage());

            }
        });
    }

    private void getNOP() {
        NewsAPI newsAPI = client.create(NewsAPI.class);
        Call<Integer> call = newsAPI.getNOP();
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()) {
                    pagesCount = response.body();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(context,"При загрузке новостей возникла ошибка! Проверьте соеденение с интернетом",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isEnd(int position) {
        return position == getCount() - 1;
    }

    public void refreshNews() {
        currentpage = 0;
        getNOP();
        loadNews();
    }


}
