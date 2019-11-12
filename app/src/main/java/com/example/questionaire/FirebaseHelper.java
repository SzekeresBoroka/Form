package com.example.questionaire;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class FirebaseHelper {
    DatabaseReference db;
    Boolean saved = null;
    ArrayList<User> users = new ArrayList<>();
    Context context;

    public FirebaseHelper(DatabaseReference db, Context context) {
        this.db = db;
        this.context = context;
    }

    //SAVE
    public Boolean save(User user) {
        if(user==null) {
            saved=false;
        }
        else {
            try {
                db.child("users").push().setValue(user);
                saved=true;
            }catch (DatabaseException e) {
                e.printStackTrace();
                saved=false;
            }
        }
        return saved;
    }

    //READ
    public ArrayList<User> retrieve()
    {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return users;
    }

    private void fetchData(DataSnapshot dataSnapshot) {
        users.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            String lastname = ds.child("lastname").getValue(String.class);
            String firstname = ds.child("firstname").getValue(String.class);
            String birthdate = ds.child("birthdate").getValue(String.class);
            String gender = ds.child("gender").getValue(String.class);
            String yearOfStudy = ds.child("yearOfStudy").getValue(String.class);
            String expectations = ds.child("expectations").getValue(String.class);
            String location = ds.child("locationId").getValue(Integer.class).toString();
            if(location == null){
                location = "0";
            }
            int locationId = parseInt(location);
            String department = ds.child("departmentId").getValue(Integer.class).toString();
            if(department == null){
                department = "0";
            }
            int departmentId = parseInt(department);
            ArrayList<String> hobbies = new ArrayList<>();
            for(DataSnapshot hobbyDs : ds.child("hobbies").getChildren()){
                String hobby = hobbyDs.getValue().toString();
                if(!hobby.isEmpty()){
                    hobbies.add(hobby);
                    //Toast.makeText(context, "Hobby: " + hobby, Toast.LENGTH_SHORT).show();
                }
            }
            //Toast.makeText(context, lastname + " - " + firstname + " - " + birthdate + " - " + gender + " - " + locationId, Toast.LENGTH_SHORT).show();
            users.add(new User(lastname, firstname, birthdate, gender, locationId, departmentId, yearOfStudy, hobbies, expectations));
        }
    }
}
