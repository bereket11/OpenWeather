package weather.android.example.com.weather_application.MVP;

public interface BasedView<T> {
    void setPresenter(T presenter);
}
