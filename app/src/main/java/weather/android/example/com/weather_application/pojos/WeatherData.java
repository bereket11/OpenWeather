package weather.android.example.com.weather_application.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import weather.android.example.com.weather_application.pojos.Wind;

/**
 * Created by berekethaile on 4/24/18.
 */

public class WeatherData {

    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("list")
    @Expose
    private java.util.List<weather.android.example.com.weather_application.pojos.List> list;

    @SerializedName("city")
    @Expose
    private City city;

    public List<Weather> getWeather() {
        return weather;
    }

    public java.util.List<weather.android.example.com.weather_application.pojos.List> getList() {
        return list;
    }
    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public City getCity() {
        return city;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
