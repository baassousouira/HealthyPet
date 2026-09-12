package com.example.healthypet;

import static com.example.healthypet.R.id.btnacc;
import static com.example.healthypet.R.id.btnnoacc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import com.example.healthypet.databinding.ActivityMainActivity2AccOrNottBinding;

// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// !!!! CELLE CI EST FINI NORMALEMENT !!!!
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class MainActivity2AccOrNott extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_acc_or_nott);

        // Replace the fragment container with frag_01_buttons_yessacc_noacc fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, new frag_01_buttons_yesacc_noacc())
                .commit();
    }
}