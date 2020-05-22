package com.example.workshopandroiddevelopment;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherActivity extends AppCompatActivity {

    public static final String API_KEY="635fbfa58dfddd0c56b8b94cdd08517a";

    private TextView country_name,updatedAt,desc,temp,minTemps,maxTemps;
    private ImageView icon;
    private TextInputLayout countryInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        country_name = findViewById(R.id.countryName);
        updatedAt = findViewById(R.id.updatedAt);
        desc = findViewById(R.id.desc);
        temp = findViewById(R.id.temp);
        minTemps = findViewById(R.id.minTemps);
        maxTemps = findViewById(R.id.maxTemps);

        countryInput = findViewById(R.id.CountryInput);
        icon = findViewById(R.id.icon);





    }

    public void CurrentWeather(String country){

        String url="https://api.openweathermap.org/data/2.5/weather?q="+country+"&units=metric&appid="+API_KEY;

        Ion.with(this).load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result)
                    {
                        if(e != null)
                            Toast.makeText(WeatherActivity.this, "Exception "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        else
                        {
//                            Manouba,Tn
                            String name = result.get("name").getAsString();
                            JsonObject sys = result.get("sys").getAsJsonObject();
                            String country = sys.get("country").getAsString();

                            country_name.setText(name+","+country);



//                          Updated at : 22-05-2020 23:20
                            Long dt = result.get("dt").getAsLong();
                            String UpdatedAt = "Updated At :"+new SimpleDateFormat("dd/MM/yyyy hh:mm a ",Locale.ENGLISH)
                                    .format(new Date(dt * 1000));
                            updatedAt.setText(UpdatedAt);

                            JsonObject weather = result.get("weather")
                                    .getAsJsonArray().get(0).getAsJsonObject();
                            String description = weather.get("description").getAsString();
                            String iconString = weather.get("icon").getAsString();

                            desc.setText(description);

                            String IconUrl ="http://openweathermap.org/img/w/"+iconString+".png";
                            Picasso.get()
                                    .load(IconUrl)
                                    .resize(50, 50)
                                    .centerCrop()
                                    .into(icon);


                            JsonObject main = result.get("main").getAsJsonObject();
                            String tempC =  main.get("temp").getAsString()+" C°";
                            String minT = "Min Temp : "+main.get("temp_min").getAsString()+" °C";
                            String maxT = "Max Temp : "+main.get("temp_max").getAsString()+" °C";


                            minTemps.setText(minT);
                            maxTemps.setText(maxT);
                            temp.setText(tempC);



                        }

                    }
                });


    }


    public void GetMyCurrentWeather(View view) {
        String CountryName = countryInput.getEditText().getText().toString();
        CurrentWeather(CountryName);
    }
}
