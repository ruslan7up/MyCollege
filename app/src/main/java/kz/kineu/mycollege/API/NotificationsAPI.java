package kz.kineu.mycollege.API;

import kz.kineu.mycollege.Entities.News;
import kz.kineu.mycollege.Entities.Notification;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ruslan on 16.02.2017.
 */

public interface NotificationsAPI {
    @GET("/notificationRest/getLastNotifications")
    Call<Notification[]> getNews();

}
