package com.example.healthypet;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthypet.data.model.Forecasts;
import com.example.healthypet.data.model.WeatherYahoo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.RealResponseBody;

public class FragmentMETEO extends Fragment {
    TextView location;
    TextView updateAt;
    TextView status;
    TextView temp;
    TextView tempMin;
    TextView tempMax;
    TextView sunrise;
    TextView sunset;
    TextView wind;
    TextView pressure;
    TextView humidity;
    TextView info;


    public FragmentMETEO() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_m_e_t_e_o, container, false);

        // Find the "Go Back" button
        Button btnGoBack = view.findViewById(R.id.btnGOBACKplaySection);

        // Set click listener for the "Go Back" button
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace the current fragment with the Home fragment
                replaceFragment(new FragmentHOME());
            }
        });
        location = view.findViewById(R.id.address);
        updateAt = view.findViewById(R.id.updated_at);
        status = view.findViewById(R.id.status);
        temp = view.findViewById(R.id.temp);
        tempMin = view.findViewById(R.id.temp_min);
        tempMax = view.findViewById(R.id.temp_max);
        sunrise = view.findViewById(R.id.sunrise);
        sunset = view.findViewById(R.id.sunset);
        wind = view.findViewById(R.id.wind);
        pressure = view.findViewById(R.id.pressure);
        humidity = view.findViewById(R.id.humidity);
        info = view.findViewById(R.id.recommendation);



        callWeatherAPI();

        return view;
    }

    private void callWeatherAPI() {
        String urllink = "https://yahoo-weather5.p.rapidapi.com/weather?location=paris&format=json&u=c";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urllink)
                .get()
                .addHeader("X-RapidAPI-Key", "873188de34mshe53cb8029fc781ep1de0d3jsn8e8364aa9f41")
                .addHeader("X-RapidAPI-Host", "yahoo-weather5.p.rapidapi.com")
                .build();
        System.out.println("****>> creation de la requete");

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println("****>> erreur" + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //address.setText(response.body().string());
                            System.out.println("resp : " + response.body().toString());
                            updateFields(response);

                        } catch (IOException e) {
                            //throw new RuntimeException(e);
                            e.printStackTrace();
                        }

                    }
                });


            }
        });


    }

    private void updateFields(Response response) throws IOException {
        System.out.println("****>> resp : " + response);
        ResponseBody body = response.body();
        if (null!= body) {
            System.out.println("****>> resp : " + body + ", instace : "+ body.getClass());
            RealResponseBody realResponseBody = (RealResponseBody) body;
            byte[] bytes = realResponseBody.bytes();
            String resp=new String(bytes);

            System.out.println("****>> resp 2 : " + resp);


            ObjectMapper objectMapper = new ObjectMapper();
            WeatherYahoo weatherYahoo = objectMapper.readValue(resp, WeatherYahoo.class);
            System.out.println("*********************** " );
            System.out.println("****>> Direction du vent : " + weatherYahoo.getCurrentObservation().getWind().getDirection());
            System.out.println("****>> Heure du leve du soleil : " + weatherYahoo.getCurrentObservation().getAstronomy().getSunrise());
            System.out.println("****>> Pression : " + weatherYahoo.getCurrentObservation().getAtmosphere().getPressure());

            Forecasts forecasts = weatherYahoo.getForecasts().get(0);

            location.setText("" + weatherYahoo.getLocation().getCity());
            String pubDate = weatherYahoo.getCurrentObservation().getPubDate();
            String formatedPubDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(Integer.parseInt(pubDate) * 1000L));
            updateAt.setText("Updated at: " + formatedPubDate);
            status.setText("" + weatherYahoo.getCurrentObservation().getCondition().getText());
            temp.setText("" + weatherYahoo.getCurrentObservation().getCondition().getTemperature()+ "°C");
            tempMin.setText("" + forecasts.getLow()+ "°C");
            tempMax.setText("" + forecasts.getHigh()+ "°C");
            sunrise.setText("" + weatherYahoo.getCurrentObservation().getAstronomy().getSunrise());
            sunset.setText("" + weatherYahoo.getCurrentObservation().getAstronomy().getSunset());
            wind.setText("" + weatherYahoo.getCurrentObservation().getWind().getSpeed()+ " km/h");
            pressure.setText("" + weatherYahoo.getCurrentObservation().getAtmosphere().getPressure()+ " millibars");
            humidity.setText("" + weatherYahoo.getCurrentObservation().getAtmosphere().getHumidity()+ " %");

        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}