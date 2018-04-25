package weather.android.example.com.weather_application.Fragments;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Calendar;

import android.widget.ArrayAdapter;
import weather.android.example.com.weather_application.Adapters.WeatherAdaptor;
import weather.android.example.com.weather_application.R;
import weather.android.example.com.weather_application.Services.NetworkTest;
import weather.android.example.com.weather_application.getWeather.IContract_Weather;
import weather.android.example.com.weather_application.getWeather.Weather_Presenter;
import weather.android.example.com.weather_application.pojos.WeatherData;

import butterknife.BindView;
import butterknife.ButterKnife;
import weather.android.example.com.weather_application.pojos.WeatherInfo;

import static com.google.common.base.Preconditions.checkNotNull;

public class Weather_view extends Fragment implements IContract_Weather.IView_DataWeather {

    private ProgressDialog pDialog;
    private IContract_Weather.IPresenter_DataWeather iPresenter_dataWeather;
    private WeatherAdaptor weatherAdaptor= null;

    public static final String ZIP_CODE = "Searched_Zip";

    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    @BindView(R.id.current_condition) TextView mainCondition;
    @BindView(R.id.current_details) TextView descriptionCondition;
    @BindView(R.id.temperature) TextView temperature;
    @BindView(R.id.wind_speed) TextView windSpeed;
    @BindView(R.id.pressure) TextView pressure;
    @BindView(R.id.humidity) TextView humidity;
    @BindView(R.id.sunrise) TextView sunrise;
    @BindView(R.id.city_name) TextView cityName;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        iPresenter_dataWeather=new Weather_Presenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_weather_view, container, false);
        ButterKnife.bind(this,view);
        String zipcode= getArguments().getString("ZIPCODE");
        iPresenter_dataWeather.getData(zipcode);
        return view;
    }

    @Override
    public void setPresenter(IContract_Weather.IPresenter_DataWeather presenter) {
        this.iPresenter_dataWeather=checkNotNull(presenter);
    }

    @Override
    public void presentData(WeatherData weatherData) {

        descriptionCondition.setText(weatherData.getWeather().get(0).getDescription());
        String temperatu = String.valueOf(new DecimalFormat("#").format(weatherData.getMain().getTemp()));
        String temp = temperatu + "\u00b0F";
        temperature.setText(temp);
        cityName.setText(" " + weatherData.getName() + "," + "US");
        mainCondition.setText(weatherData.getWeather().get(0).getmain());
        windSpeed.setText(String.valueOf(weatherData.getWind().getSpeed().toString()) + " " + "m/s");
        pressure.setText(String.valueOf(weatherData.getMain().getPressure()) + " " + "hpa");
        humidity.setText(String.valueOf(weatherData.getMain().getHumidity()) + "%");
        Calendar sunriseCal = Calendar.getInstance();
        sunriseCal.setTimeInMillis(weatherData.getSys().getSunrise() * 1000);
        String suntime = DateFormat.format("hh:mm", sunriseCal).toString() + "AM";
        sunrise.setText(suntime);
    }


    @Override
    public void onResume() {
        super.onResume();
        iPresenter_dataWeather.start();
    }

    @Override
    public void showProgressDialog() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        if(pDialog.isShowing() || pDialog!=null){
            pDialog.dismiss();
        }
    }

}
