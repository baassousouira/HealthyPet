package com.example.healthypet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;

import com.example.healthypet.data.model.client.Client;
import com.example.healthypet.data.model.client.Pet;

public class FragmentHOME extends Fragment {

    private TextView petName, petAge, petHeight, petWeight, petGenre;

    public FragmentHOME() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_h_o_m_e, container, false);

        // Find buttons by their IDs
        Button btnGame = view.findViewById(R.id.btn_game_HOME_Section);
        Button btnWork = view.findViewById(R.id.btn_work_HOME_Section);
        Button btnCalendar = view.findViewById(R.id.btn_calendar_HOME_Section);
        Button btnMeteo = view.findViewById(R.id.btn_meteo_HOME_Section);

        //Find the text views to be able to modify them
        petName = view.findViewById(R.id.nameofpet);
        petAge = view.findViewById(R.id.ageofpet);
        petHeight = view.findViewById(R.id.heightofpet);
        petWeight = view.findViewById(R.id.weightofpet);
        petGenre = view.findViewById(R.id.genreofpet);

        Pet pet = MainActivity.getPet();

        petName.setText(pet.getName());
        petAge.setText(pet.getAge()+" years");
        petHeight.setText(pet.getHeight()+" cm");
        petWeight.setText(pet.getWeight()+ " kg");
        petGenre.setText(pet.getSexe());


        // FOR BUTTON GAME
        // Set click listeners for each button
        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace the current fragment with the Games fragment
                replaceFragment(new FragmentGAME());
            }
        });

        // FOR BUTTON WORK
        btnWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace the current fragment with the Work fragment
                replaceFragment(new FragmentWORK());
            }
        });

        // FOR BUTTON CALENDAR
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace the current fragment with the Calendar fragment
                replaceFragment(new FragmentCALENDAR());
            }
        });

        // FOR BUTTON METEO
        btnMeteo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace the current fragment with the Meteo fragment
                replaceFragment(new FragmentMETEO());
            }
        });

        return view;
    }

    // Method to replace the current fragment with another fragment
    private void replaceFragment(Fragment fragment) {
        // Get the FragmentManager and start a transaction
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        // Replace the current fragment with the new one
        transaction.replace(R.id.fragment_container, fragment);
        // Add the transaction to the back stack
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }
}
