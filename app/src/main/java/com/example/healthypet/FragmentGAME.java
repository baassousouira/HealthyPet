package com.example.healthypet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.FragmentTransaction;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


// !!!!!!!!!!!!!!!!!!!!!!!
// !!!! CELLE CI EST FINI
// !!!!!!!!!!!!!!!!!!!!!!!




public class FragmentGAME extends Fragment {

    public FragmentGAME() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_g_a_m_e, container, false);

        // Find the "Go Back" button
        Button btnGoBack = view.findViewById(R.id.btnGOBACKplaySection);


    // FIND THE OK BUTTONS OF TASKS FOR GAME :
        // Find the "OK" button for Task 1 (feeding)
        Button btnOKTaskFood = view.findViewById(R.id.btnOKtaskfood);
        // Find the "OK" button  for Task 2 (go out)
        Button btnOKTaskGoOut = view.findViewById(R.id.btnOKtaskGoOut);
        // Find the "OK" button  for Task 3 (play)
        Button btnOKTaskPlay = view.findViewById(R.id.btnOKtask_play);


    // FIND THE TEXT VIEWS OF TASKS :
        // Find the TextView for Task 1 (feeding)
        TextView txtnbrfood = view.findViewById(R.id.txtnbrfood);
        // Find the TextView for Task 2 (go out)
        TextView txtnbrGoOut = view.findViewById(R.id.txt_nbr_goOut);
        // Find the TextView  for Task 3 (play)
        TextView txtnbrPlay = view.findViewById(R.id.txtNbrplay);


    // FIND THE PROGRESS BARS OF TASKS :
        // Find the ProgressBar for Task 1 (feeding)
        ProgressBar progressBarTaskFood = view.findViewById(R.id.progress_task_food);
        // Find the ProgressBar for Task 2 (go out)
        ProgressBar progressBarGoOut = view.findViewById(R.id.progress_bar_task_goOut);
        // Find the ProgressBar  for Task 3 (play)
        ProgressBar progressBarPlay = view.findViewById(R.id.progressBarTASKplay);




        // Set click listener for the "Go Back" button
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace the current fragment with the Home fragment
                replaceFragment(new FragmentHOME());
            }
        });




        // FOR TASK 1
        // Set click listener for the "OK" button of Task 1 (feeding)
        btnOKTaskFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current progress
                String[] progressParts = txtnbrfood.getText().toString().split("/");
                int currentProgress = Integer.parseInt(progressParts[0]);
                int totalProgress = Integer.parseInt(progressParts[1]);

                // Check if the progress is already at its maximum value
                if (currentProgress == totalProgress) {
                    // Show a toast message indicating that the task is accomplished
                    Toast.makeText(getActivity(), "You have already accomplished this task!", Toast.LENGTH_SHORT).show();
                } else {
                    // Increment the progress
                    currentProgress++;

                    // Update the text and progress bar
                    txtnbrfood.setText(currentProgress + "/" + totalProgress);
                    progressBarTaskFood.setProgress((currentProgress * 100) / totalProgress);
                }
            }
        });


        // FOR TASK 2
        // Set click listener for the "OK" button of Task 2 (go out)
        btnOKTaskGoOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] progressParts = txtnbrGoOut.getText().toString().split("/");
                int currentProgress = Integer.parseInt(progressParts[0]);
                int totalProgress = Integer.parseInt(progressParts[1]);
                if (currentProgress == totalProgress) {
                    Toast.makeText(getActivity(), "You have already accomplished this task!", Toast.LENGTH_SHORT).show();
                } else {
                    currentProgress++;
                    txtnbrGoOut.setText(currentProgress + "/" + totalProgress);
                    progressBarGoOut.setProgress((currentProgress * 100) / totalProgress);
                }
            }
        });


        // FOR TASK 3
        // Set click listener for the "OK" button of Task 3 (play)
        btnOKTaskPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] progressParts = txtnbrPlay.getText().toString().split("/");
                int currentProgress = Integer.parseInt(progressParts[0]);
                int totalProgress = Integer.parseInt(progressParts[1]);
                if (currentProgress == totalProgress) {
                    Toast.makeText(getActivity(), "You have already accomplished this task!", Toast.LENGTH_SHORT).show();
                } else {
                    currentProgress++;
                    txtnbrPlay.setText(currentProgress + "/" + totalProgress);
                    progressBarPlay.setProgress((currentProgress * 100) / totalProgress);
                }
            }
        });

        return view;
    }

    // Method to replace the current fragment with another fragment
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}