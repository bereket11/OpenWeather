package weather.android.example.com.weather_application.getWeather;

import weather.android.example.com.weather_application.Constants.Constants;
import weather.android.example.com.weather_application.Fragments.Weather_view;
import weather.android.example.com.weather_application.Services.IData_Weather;
import weather.android.example.com.weather_application.Services.Weather_connection;
import weather.android.example.com.weather_application.pojos.WeatherData;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class Weather_Presenter implements weather.android.example.com.weather_application.getWeather.IContract_Weather.IPresenter_DataWeather {

    weather.android.example.com.weather_application.getWeather.IContract_Weather.IView_DataWeather iView_dataWeather;
    weather.android.example.com.weather_application.Services.IData_Weather iData_weather;

    public Weather_Presenter(weather.android.example.com.weather_application.getWeather.IContract_Weather.IView_DataWeather iView_dataWeather) {
        this.iView_dataWeather = iView_dataWeather;
    }


    @Override
    public void getData(String location) {
        iView_dataWeather.showProgressDialog();


        iData_weather= Weather_connection.getWeatherConnection();


        iData_weather.getWeatherData(location, "imperial",
                Constants.Api_key)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherData>() {
                    @Override
                    public void onCompleted() {
                        iView_dataWeather.dismissProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView_dataWeather.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(WeatherData weatherData) {
                        iView_dataWeather.presentData(weatherData);


                    }
                });
    }

    @Override
    public void start() {
        iView_dataWeather.setPresenter(this);
    }
}
