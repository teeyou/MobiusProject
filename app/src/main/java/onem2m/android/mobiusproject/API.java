package onem2m.android.mobiusproject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    public static final String BASEURL = "http://115.68.37.90/";

    @GET("/api/logs/latest")
    Call<Sensor> getAllData();

}
