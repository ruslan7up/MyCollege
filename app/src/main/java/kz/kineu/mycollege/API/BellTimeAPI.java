package kz.kineu.mycollege.API;

import kz.kineu.mycollege.Entities.BellTime;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by ruslan on 21.02.2017.
 */

public interface BellTimeAPI {
    @POST("/bellTimeRest/getbt")
    Call<BellTime[]> getBellTime();
}
