package kz.kineu.mycollege.API;

import kz.kineu.mycollege.Entities.Schedule;
import kz.kineu.mycollege.Entities.Substitution;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ruslan on 25.05.2017.
 */

public interface SubstitutionAPI {
    @GET("/lessons/getubstitutions")
    Call<Substitution[]> getSchedule(@Query("date") String date);
}
