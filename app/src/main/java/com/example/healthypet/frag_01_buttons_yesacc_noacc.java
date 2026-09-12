package com.example.healthypet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.healthypet.frag_01_buttons_yesacc_noacc;



// !!!!!!!!!!!!!!
// !!!! DONE !!!!
// !!!!!!!!!!!!!!


public class frag_01_buttons_yesacc_noacc extends Fragment {

    public frag_01_buttons_yesacc_noacc() {
        // Required empty public constructor
    }

    // THIS ONE IS CALLED TO CREATE THE VIEW OF THE FRAGMENT
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_01_buttons_yesacc_noacc, container, false);
    }


    // THIS ONE IS CALLED IMMEDIATELY AFTER onCreateView()
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // WE FIND THE BUTTONS
        Button btnNoAcc = view.findViewById(R.id.btnnoacc);
        Button btnAcc = view.findViewById(R.id.btnacc);

        // WE SET THE CLICK LISTENERS FOR THE BUTTONS
        // BUTTON NO ACCOUNT
        btnNoAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace the fragment with frag_02_noacc
                replaceFragment(new frag_02_noacc());
            }
        });

        // BUTTON I HAVE AN ACCOUNT
        btnAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace the fragment with frag_03_yessacc
                replaceFragment(new frag_03_yessacc());
            }
        });
    }


    // THIS ONE IS CALLED TO REPLACE THE CURRENT FRAGMENT WITH ANOTHER
    private void replaceFragment(Fragment fragment) {
        // FIRST WE CHECK IF ACTIVITY IS NULL
        if (getActivity() == null) return;
        // THEN WE START A TRANSACTION TO REPLACE FRAGMENTS
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
