package kz.kineu.mycollege.API;

import kz.kineu.mycollege.Entities.News;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ruslan on 10.02.2017.
 */

public interface NewsAPI {
    @GET("/getNews/{page}")
    Call<News[]> getNews(@Path("page") String page);

    @GET("/getNumberOfPages")
    Call<Integer> getNOP();
}
