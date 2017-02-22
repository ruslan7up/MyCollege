package kz.kineu.mycollege.API;

import kz.kineu.mycollege.Entities.Schedule;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ruslan on 22.02.2017.
 */

public interface ScheduleAPI {

    @POST("/scheduleRest/getByName")
    Call<Schedule> getSchedule( @Query("name") String name);
}
