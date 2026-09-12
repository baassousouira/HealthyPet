package com.example.healthypet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.healthypet.data.model.client.Client;
import com.example.healthypet.data.model.client.Pet;
import com.example.healthypet.data.sqlite.DBHelper;

import java.io.IOException;


// ON CLICK GO TO ACTIVITY 2
public class MainActivity extends AppCompatActivity {
    private static Client client;
    public static Client getClient() {
        return client;
    }
    public static void setClientAndPet(Client c, Pet p){
        client = c;
        pet = p;
    };

    private static Pet pet;
    public static Pet getPet() {
        return pet;
    }

    private static DBHelper dbHelper;
    public static DBHelper getDbHelper() {
        return dbHelper;
    }

    private View imageHP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View imageHP = findViewById(R.id.imageView2);

        imageHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivity2AccOrNotT when image is clicked
                startActivity(new Intent(MainActivity.this, MainActivity2AccOrNott.class));
            }
        });

        dbHelper = new DBHelper(this);
        pet = new Pet();
        client = new Client();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(getDbHelper() !=null) {
            getDbHelper().close();
        }
    }


}