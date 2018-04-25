package weather.android.example.com.weather_application.getWeather;

import weather.android.example.com.weather_application.MVP.BasedPresenter;
import weather.android.example.com.weather_application.MVP.BasedView;
import weather.android.example.com.weather_application.pojos.WeatherData;


public interface IContract_Weather {
    public interface IPresenter_DataWeather extends BasedPresenter {
        public void getData(String location);
    }

    public interface IView_DataWeather extends BasedView<IPresenter_DataWeather> {
        public void presentData(WeatherData weatherData);
        public void showProgressDialog();
        public void dismissProgressDialog();

    }
}
