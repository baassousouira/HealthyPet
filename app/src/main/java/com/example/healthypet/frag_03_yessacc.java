package com.example.healthypet;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.healthypet.MainActivity3_after_log;
import com.example.healthypet.data.DButils;
import com.example.healthypet.data.model.client.Client;
import com.example.healthypet.data.model.client.Pet;


// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// !!!! CELLE CI EST FINI FAUDRA SUREMENT MODIFIER QUAND ON AURA FINI POUR FAIRE LA VERIF DES ID ET MDP
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class frag_03_yessacc extends Fragment {

    // the fragment variables
    private EditText pseudoInput, inputPassword;
    private Button goBackBtnYessAcc, nextBtnYessAcc;


    public frag_03_yessacc() {
        // Required empty public constructor
    }


    // THIS ONE IS CALLES TO CREATE THE VIEW FOR THE FRAGMENT
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_03_yessacc, container, false);

        //Initialize views :
        pseudoInput = view.findViewById(R.id.pseudoyessaccInput);
        inputPassword = view.findViewById(R.id.passwordyessaccInput);
        goBackBtnYessAcc = view.findViewById(R.id.gobackbtnyessacc);
        nextBtnYessAcc = view.findViewById(R.id.nextbtnyessacc);

        // we set the click listeners
        // BUTTON GO BACK
        goBackBtnYessAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to previous fragment
                getActivity().onBackPressed();
            }
        });
        //BUTTON NEXT
        nextBtnYessAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate inputs and proceed if valid
                if (validateInputs()) {
                    // Start MainActivity3_after_log activity
                    boolean isOk = isAlreadyRegistred();
                    if (isOk) {
                        Client client = getClient();
                        Pet pet = getPet();
                        MainActivity.setClientAndPet(client, pet);
                        startActivity(new Intent(getActivity(), MainActivity3_after_log.class));
                    }else {
                        Toast.makeText(getActivity(), "pseudo existant ou mdp erroné", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Show error message if inputs are not valid
                    Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private Pet getPet() {
        String pseudo = pseudoInput.getText().toString();
        Client client = DButils.getClient(pseudo);
        Pet pet = DButils.getPet(client.getId());
        return pet;
    }

    private Client getClient() {
        String pseudo = pseudoInput.getText().toString();
        Client client = DButils.getClient(pseudo);
        return client;
    }

    //verifie l'existance du pseud et mdp correct
    private boolean isAlreadyRegistred() {
        String pseudo = pseudoInput.getText().toString();
        String pswd = inputPassword.getText().toString();

        Client client = DButils.getClient(pseudo);
        if (client.getPseudo().equals(pseudo)){
            if (client.getPswd().equals(pswd)){
                return true;
            }
        }
        return false;
    }

    private boolean validateInputs() {
        // Check if all fields are filled
        if (pseudoInput.getText().toString().trim().isEmpty() ||
                inputPassword.getText().toString().trim().isEmpty()) {
            // Return false if any field is empty
            return false;
        }
        // Return true if all validation checks are passed
        return true;
    }
}
