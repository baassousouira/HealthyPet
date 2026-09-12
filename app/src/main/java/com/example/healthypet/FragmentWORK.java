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



public class FragmentWORK extends Fragment {

    public FragmentWORK() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_w_o_r_k, container, false);

        // Find the "Go Back" button
        Button btnGoBack = view.findViewById(R.id.btnGOBACKworkSection);


    // FIND THE OK BUTTONS OF TASKS FOR WORK :
        // Find the "OK" button for Task 1 (more infos)
        Button btnOKTaskInfos = view.findViewById(R.id.btnOKtaskInfoPet);
        // Find the "OK" button  for Task 2 (trick)
        Button btnOKTaskTrick = view.findViewById(R.id.btnOKtaskTRICK);
        // Find the "OK" button  for Task 3 (brush)
        Button btnOKTaskBrush = view.findViewById(R.id.btnOKtaskBROSSE);


    // FIND THE TEXT VIEWS OF TASKS :
        // Find the TextView for Task 1 (more infos)
        TextView txtnbrInfos = view.findViewById(R.id.txtnbrInfoPet);
        // Find the TextView for Task 2 (trick)
        TextView txtnbrTrick = view.findViewById(R.id.txtTrick);
        // Find the TextView  for Task 3 (brush)
        TextView txtnbrBrush = view.findViewById(R.id.txtNbrBrosse);


    // FIND THE PROGRESS BARS OF TASKS :
        // Find the ProgressBar for Task 1 (more infos)
        ProgressBar progressBarInfos = view.findViewById(R.id.progress_task_infosPet);
        // Find the ProgressBar for Task 2 (trick)
        ProgressBar progressBarTrick = view.findViewById(R.id.progress_task_TRICK);
        // Find the ProgressBar  for Task 3 (brush)
        ProgressBar progressBarBrush = view.findViewById(R.id.progressBarTASKbrosse);




        // Set click listener for the "Go Back" button
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace the current fragment with the Home fragment
                replaceFragment(new FragmentHOME());
            }
        });

        // FOR TASK 1
        // Set click listener for the "OK" button of Task 1 (more infos)
        btnOKTaskInfos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current progress
                String[] progressParts = txtnbrInfos.getText().toString().split("/");
                int currentProgress = Integer.parseInt(progressParts[0]);
                int totalProgress = Integer.parseInt(progressParts[1]);
                if (currentProgress == totalProgress) {
                    Toast.makeText(getActivity(), "You have already accomplished this task!", Toast.LENGTH_SHORT).show();
                } else {
                    currentProgress++;
                    // Update the text and progress bar
                    txtnbrInfos.setText(currentProgress + "/" + totalProgress);
                    progressBarInfos.setProgress((currentProgress * 100) / totalProgress);
                }
            }
        });


        // FOR TASK 2
        // Set click listener for the "OK" button of Task 2 (trick)
        btnOKTaskTrick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] progressParts = txtnbrTrick.getText().toString().split("/");
                int currentProgress = Integer.parseInt(progressParts[0]);
                int totalProgress = Integer.parseInt(progressParts[1]);
                if (currentProgress == totalProgress) {
                    Toast.makeText(getActivity(), "You have already accomplished this task!", Toast.LENGTH_SHORT).show();
                } else {
                    currentProgress++;
                    txtnbrTrick.setText(currentProgress + "/" + totalProgress);
                    progressBarTrick.setProgress((currentProgress * 100) / totalProgress);
                }
            }
        });


        // FOR TASK 3
        // Set click listener for the "OK" button of Task 3 (brush)
        btnOKTaskBrush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] progressParts = txtnbrBrush.getText().toString().split("/");
                int currentProgress = Integer.parseInt(progressParts[0]);
                int totalProgress = Integer.parseInt(progressParts[1]);
                if (currentProgress == totalProgress) {
                    Toast.makeText(getActivity(), "You have already accomplished this task!", Toast.LENGTH_SHORT).show();
                } else {
                    currentProgress++;
                    txtnbrBrush.setText(currentProgress + "/" + totalProgress);
                    progressBarBrush.setProgress((currentProgress * 100) / totalProgress);
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
