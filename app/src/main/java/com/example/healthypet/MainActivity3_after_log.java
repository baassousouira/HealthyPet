package com.example.healthypet;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

// Activity for the main screen after user login
// + nav drawer


public class MainActivity3_after_log extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3_after_log);

        // Find the DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout);

        // Find the NavigationView
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Set navigation item selected listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Handle navigation item clicks here
                int id = item.getItemId();

                // Check which item was clicked and change fragment
                // HOME
                if (id == R.id.bottom_menu) {
                    // Load HomeFragment when Home is clicked
                    replaceFragment(new FragmentHOME());
                }
                //GAME
                else if (id == R.id.bottom_game) {
                    replaceFragment(new FragmentGAME());
                }
                //PLANNING
                else if (id == R.id.bottom_planning) {
                    replaceFragment(new FragmentCALENDAR());
                }
                //WORK
                else if (id == R.id.bottom_work) {
                    replaceFragment(new FragmentWORK());
                }
                //METEO
                else if (id == R.id.bottom_meteo) {
                    replaceFragment(new FragmentMETEO());
                }
                //LOG OUT
                else if (id == R.id.logout) {
                    startActivity(new Intent(MainActivity3_after_log.this, MainActivity.class));
                    finish();
                }

                // Close the drawer after item click
                drawerLayout.closeDrawers();

                return true;
            }
        });

        // Get header view from navigation view
        View headerView = navigationView.getHeaderView(0);
        ImageView userIconeNav = headerView.findViewById(R.id.userIconeNav);
        TextView userPseudoNav = headerView.findViewById(R.id.userPseudoNav);

        // Click listener for user profile
        View.OnClickListener profileClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new FragmentPROFILE());
                drawerLayout.closeDrawers();
            }
        };

        userIconeNav.setOnClickListener(profileClickListener);
        userPseudoNav.setOnClickListener(profileClickListener);

        // Replace the initial fragment with FragmentHOME
        replaceFragment(new FragmentHOME());
    }

    // Method to replace the current fragment with another fragment
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
