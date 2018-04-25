package weather.android.example.com.weather_application.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import weather.android.example.com.weather_application.R;
import weather.android.example.com.weather_application.pojos.WeatherData;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WeatherAdaptor extends RecyclerView.Adapter<WeatherAdaptor.ViewHolder> {

    private WeatherData weatherData;
    private int row;
    private Context context;
    String d="";
    public WeatherAdaptor(WeatherData weatherData, int row, Context context) {
        this.weatherData = weatherData;
        this.row = row;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(row,parent,false);
        return new WeatherAdaptor.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.descriptionCondition.setText("Description :"+weatherData.getList().get(position).getWeather().get(0).getDescription());
        holder.temperature.setText(String.valueOf(weatherData.getMain().getTemp()));
        holder.cityName.setText("Town"+weatherData.getCity().getName());
        holder.mainCondition.setText("Temperature :"+weatherData.getList().get(0).getMain().getTemp().toString());
        holder.descriptionCondition.setText(weatherData.getList().get(0).getWeather().toString());
        holder.windSpeed.setText(weatherData.getWind().getSpeed().toString());
        holder.pressure.setText(weatherData.getList().get(0).getMain().getPressure());
        holder.humidity.setText(weatherData.getList().get(0).getMain().getHumidity());
        holder.sunrise.setText(weatherData.getList().get(0).getSys().getSunrise());

       /* Picasso.with(context)
                .load(icon_main_url+weatherData.getList().get(position).getWeather().get(0).getIcon()+".png")
                .resize(164, 164)
                .centerCrop()
                .into(holder.image);*/
    }

    @Override
    public int getItemCount() {
        return weatherData.getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.main_condition) TextView mainCondition;
        @BindView(R.id.description_condition) TextView descriptionCondition;
        @BindView(R.id.temperature) TextView temperature;
        @BindView(R.id.wind_speed) TextView windSpeed;
        @BindView(R.id.pressure) TextView pressure;
        @BindView(R.id.humidity) TextView humidity;
        @BindView(R.id.sunrise) TextView sunrise;
        @BindView(R.id.city_name) TextView cityName;
       // @BindView(R.id.row_ImageView) ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
