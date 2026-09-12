package com.example.healthypet.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.healthypet.MainActivity;
import com.example.healthypet.data.model.client.Client;
import com.example.healthypet.data.model.client.Pet;

import java.util.Arrays;

public class DButils {

    public static Integer countElement(String sql, String[] args) {
        int count = 0;
        SQLiteDatabase writableDatabase = MainActivity.getDbHelper().getWritableDatabase();
        Cursor cursor = writableDatabase.rawQuery(sql, args);
        int nRows = cursor.getCount();
        /*if (nRows>0) {
            while (!cursor.isAfterLast()){
                int colIndex=cursor.getColumnIndex("NBCOUNT");
                count += cursor.getInt(colIndex);
                cursor.moveToFirst();
            }
        }*/
        count += nRows;
        System.out.println("On a trouvé " + nRows + " ligne(s) avec le SQL " + sql + " et paramètres " + Arrays.toString(args));

        cursor.close();
        return count;
    }

    public static boolean insertNewClientAndPet(Client client, Pet pet) {
        boolean isOk = false;
        SQLiteDatabase writableDatabase = MainActivity.getDbHelper().getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("email", client.getEmail());
        contentValues.put("pseudo", client.getPseudo());
        contentValues.put("pswd", client.getPswd());

        long clientId = writableDatabase.insert("client", null, contentValues);
        System.out.println("client Inserted is : " + clientId);

        contentValues = new ContentValues();
        contentValues.put("age", pet.getAge());
        contentValues.put("name", pet.getName());
        contentValues.put("height", pet.getHeight());
        contentValues.put("weight", pet.getWeight());
        contentValues.put("sexe", pet.getSexe());
        contentValues.put("type", pet.getType());
        contentValues.put("clientId", clientId);

        long petId = writableDatabase.insert("pet", null, contentValues);
        System.out.println("pet Inserted is : " + petId);

        client.setId((int) clientId);
        pet.setId((int) petId);
        pet.setClientId((int) clientId);
        isOk = true;

        return isOk;
    }

    public static Client getClient(String pseudo) {
        SQLiteDatabase writableDatabase = MainActivity.getDbHelper().getWritableDatabase();
        String sql = "select * from client where pseudo = ?";
        String[] args = new String[]{pseudo};
        Cursor cursor = writableDatabase.rawQuery(sql, args);
        int nRows = cursor.getCount();
        Client client = new Client();
        if (nRows > 0) {
            cursor.moveToFirst();
            int colIndexP = cursor.getColumnIndex("pseudo");
            int colIndexPW = cursor.getColumnIndex("pswd");
            int colIndexE = cursor.getColumnIndex("email");
            int colIndexI = cursor.getColumnIndex("id");

            String bddPseudo = cursor.getString(colIndexP);
            String bddPswd = cursor.getString(colIndexPW);
            String bddEMail = cursor.getString(colIndexE);
            Integer bddId = cursor.getInt(colIndexI);

            client.setPseudo(bddPseudo);
            client.setPswd(bddPswd);
            client.setEmail(bddEMail);
            client.setId(bddId);
        }
        cursor.close();
        return client;


    }

    public static Pet getPet(Integer id) {
        SQLiteDatabase writableDatabase = MainActivity.getDbHelper().getWritableDatabase();
        String sql = "select * from pet where clientId = ?";
        String[] args = new String[]{"" + id};
        Cursor cursor = writableDatabase.rawQuery(sql, args);
        int nRows = cursor.getCount();
        Pet pet = new Pet();
        if (nRows > 0) {


            cursor.moveToFirst();
            int colIndexi = cursor.getColumnIndex("id");
            int colIndexn = cursor.getColumnIndex("name");
            int colIndexa = cursor.getColumnIndex("age");
            int colIndexh = cursor.getColumnIndex("height");
            int colIndexw = cursor.getColumnIndex("weight");
            int colIndexs = cursor.getColumnIndex("sexe");
            int colIndexc = cursor.getColumnIndex("clientId");
            int colIndext = cursor.getColumnIndex("type");

            Integer bddAge = cursor.getInt(colIndexa);
            String bddName = cursor.getString(colIndexn);
            Double bddHeight = cursor.getDouble(colIndexh);
            Double bddWeight = cursor.getDouble(colIndexw);
            String bddSexe = cursor.getString(colIndexs);
            Integer bddClientId = cursor.getInt(colIndexc);
            String bddType = cursor.getString(colIndext);
            Integer bddId = cursor.getInt(colIndexi);

            pet.setType(bddType);
            pet.setName(bddName);
            pet.setAge(bddAge);
            pet.setHeight(bddHeight);
            pet.setWeight(bddWeight);
            pet.setSexe(bddSexe);
            pet.setClientId(bddClientId);
            pet.setId(bddId);

        }
        cursor.close();
        return pet;
    }
}
