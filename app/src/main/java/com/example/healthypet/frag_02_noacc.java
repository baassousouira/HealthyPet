package com.example.healthypet;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.healthypet.data.DButils;
import com.example.healthypet.data.model.client.Client;
import com.example.healthypet.data.model.client.Pet;

// !!!!!!!!!!!!!!!!!!!!!!!!!!!
// !!!! CELLE CI EST FINI !!!!
// !!!!!!!!!!!!!!!!!!!!!!!!!!!


public class frag_02_noacc extends Fragment {

    // ui elements
    private EditText pseudoInput, inputMail, inputPassword, petNameInput, petAgeInput, petWeightInput, petHeightInput;
    private RadioButton radioButtonMale, radioButtonFemale, radioButtonDog, radioButtonCat, radioButtonRabbit, radioButtonTurtle;
    private Button goBackBtnNoAcc, nextBtnNoAcc;

    public frag_02_noacc() {
        // Required empty public constructor
    }

    // THIS ONE IS CALLED TO CREATE THE VIEW FOR THE FRAGMENT
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_02_noacc, container, false);

        // WE INITIALIZE THE VIEWS
        pseudoInput = view.findViewById(R.id.pseudoinput);
        inputMail = view.findViewById(R.id.inputmail);
        inputPassword = view.findViewById(R.id.inputpassword);
        petNameInput = view.findViewById(R.id.petnameInput);
        petAgeInput = view.findViewById(R.id.petagenoaccINPUT);
        petWeightInput = view.findViewById(R.id.petweightnoaccINPUT);
        petHeightInput = view.findViewById(R.id.petheightnoaccINPUT);

        radioButtonMale = view.findViewById(R.id.radioButtonMale);
        radioButtonFemale = view.findViewById(R.id.radioButtonFemale);
        radioButtonDog = view.findViewById(R.id.radioButton4);
        radioButtonCat = view.findViewById(R.id.radioButton1);
        radioButtonRabbit = view.findViewById(R.id.radioButton2);
        radioButtonTurtle = view.findViewById(R.id.radioButton3);

        goBackBtnNoAcc = view.findViewById(R.id.gobackbtnoacc);
        nextBtnNoAcc = view.findViewById(R.id.nextbtnnosacc);

        // WE SET THE CLICK LISTENERS
        // BUTTON GO BACK
        goBackBtnNoAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to previous fragment
                getActivity().onBackPressed();
            }
        });

        // BUTTON NEXT
        nextBtnNoAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    // If inputs are valid :
                    Toast.makeText(getActivity(), "Next button clicked", Toast.LENGTH_SHORT).show();
                    boolean isRegistered=registerClient();
                    if (isRegistered) {
                        startActivity(new Intent(getActivity(), MainActivity3_after_log.class));
                    }
                } else {
                    // If inputs are not valid, show an error message :
                    Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private boolean registerClient() {
        SQLiteDatabase writableDatabase = MainActivity.getDbHelper().getWritableDatabase();
        String pseudo= pseudoInput.getText().toString().trim();
        Integer count= DButils.countElement("select * from client where pseudo = ?", new String[] {pseudo});
        if (count>0) {
            //afiche un lessge d'erreur
            Toast.makeText(getActivity(), "This pseudo already exists !", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            //inscrit la personne en base de données
            Client client= new Client();
            client.setEmail(inputMail.getText().toString().trim());
            client.setPseudo(pseudoInput.getText().toString().trim());
            client.setPswd(inputPassword.getText().toString().trim());

            String age = petAgeInput.getText().toString().trim();
            String height = petHeightInput.getText().toString().trim();
            String weight = petWeightInput.getText().toString().trim();
            Pet pet = new Pet();
            pet.setAge(Integer.parseInt(age));
            pet.setName(petNameInput.getText().toString().trim());
            pet.setHeight(Double.parseDouble(height));
            pet.setWeight(Double.parseDouble(weight));

            pet.setType(getSpecificType());
            pet.setSexe(getSpecificSexe());


            boolean isOk = DButils.insertNewClientAndPet(client, pet);
            if (!isOk){

                Toast.makeText(getActivity(), "This pseudo already exists !", Toast.LENGTH_SHORT).show();
                return false;
            }else {
                MainActivity.setClientAndPet(client, pet);
                return true;
            }
        }


    }

    private String getSpecificSexe() {
        if(radioButtonMale.isChecked()){
            return radioButtonMale.getText().toString();
        }else if(radioButtonFemale.isChecked()) {
            return radioButtonFemale.getText().toString();
        }else {
            return null;
        }

    }

    private String getSpecificType() {
        if(radioButtonCat.isChecked()){
            return radioButtonCat.getText().toString();
        }else if(radioButtonDog.isChecked()){
            return radioButtonDog.getText().toString();
        }else if(radioButtonTurtle.isChecked()){
            return radioButtonTurtle.getText().toString();
        }else if(radioButtonRabbit.isChecked()){
            return radioButtonRabbit.getText().toString();
        }else {
            return null;
        }
    }

    // THIS ONE IS TO VALIDATE INPUT FIELDS
    private boolean validateInputs() {
        // Example: Check if all fields are filled
        if (pseudoInput.getText().toString().trim().isEmpty() ||
                inputMail.getText().toString().trim().isEmpty() ||
                inputPassword.getText().toString().trim().isEmpty() ||
                petNameInput.getText().toString().trim().isEmpty() ||
                petAgeInput.getText().toString().trim().isEmpty() ||
                petWeightInput.getText().toString().trim().isEmpty() ||
                petHeightInput.getText().toString().trim().isEmpty()) {
            // Return false if any field is empty
            return false;
        }
        // Check if a radio button is selected
        if (!radioButtonMale.isChecked() && !radioButtonFemale.isChecked()) {
            // Return false if no radio button is selected
            return false;
        }
        // Return true if all validation checks pass
        return true;
    }
}