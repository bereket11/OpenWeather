package weather.android.example.com.weather_application.Services;

import weather.android.example.com.weather_application.pojos.WeatherData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface IData_Weather {

    @GET("weather")
    Observable<WeatherData> getWeatherData
            (@Query("zip") String zip,
             @Query("units") String units,
             @Query("appid") String appid);
}
