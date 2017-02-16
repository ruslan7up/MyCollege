package kz.kineu.mycollege.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import kz.kineu.mycollege.API.NotificationsAPI;
import kz.kineu.mycollege.Entities.Notification;
import kz.kineu.mycollege.R;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by ruslan on 16.02.2017.
 */

public class NotificationsAdapter extends ArrayAdapter<Notification> {


    private final String LOG_TAG = "MyCollegeApp";
    private String url = "http://78.46.123.237:7777";
    private Retrofit client;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    public NotificationsAdapter(Context context, int resource) {
        super(context, resource);
    }


    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient httpClient = builder.readTimeout(4, TimeUnit.SECONDS).writeTimeout(4,TimeUnit.SECONDS).connectTimeout(4,TimeUnit.SECONDS).build();
        client = new Retrofit.Builder().addConverterFactory(JacksonConverterFactory.create()).baseUrl(url).client(httpClient).build();
        loadNotifications();
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Notification notification = getItem(position);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.notification_item,  null);
        TextView tvMessage = (TextView) view.findViewById(R.id.tvNotificationMessage);
        TextView tvDate = (TextView) view.findViewById(R.id.tvNotificationDate);
        tvMessage.setText(notification.getNotifymessage());
        tvDate.setText(sdf.format(notification.getDate()));
        return view;
    }


    public void loadNotifications() {
        NotificationsAPI API = client.create(NotificationsAPI.class);
        Call<Notification[]> call = API.getNews();
        call.enqueue(new Callback<Notification[]>() {
            @Override
            public void onResponse(Call<Notification[]> call, Response<Notification[]> response) {
                addAll(Arrays.asList(response.body()));
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Notification[]> call, Throwable t) {
                Toast.makeText(getContext(),"Не удалось загрузить уведомления",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
