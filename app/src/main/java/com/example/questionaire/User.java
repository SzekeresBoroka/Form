package com.example.questionaire;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String lastname, firstname, birthdate, gender, yearOfStudy, expectations;
    private int locationId, departmentId;
    private ArrayList<String> hobbies;

    public User() {
        hobbies = new ArrayList<>();
    }

    public User(String lastname, String firstname, String birthdate, String gender, int locationId, int departmentId, String yearOfStudy, ArrayList<String> hobbies, String expectations){
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.locationId = locationId;
        this.departmentId = departmentId;
        this.yearOfStudy = yearOfStudy;
        this.expectations = expectations;
        this.hobbies = hobbies;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getGender() {
        return gender;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public String getExpectations() {
        return expectations;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public ArrayList<String> getHobbies() {
        return hobbies;
    }
}
