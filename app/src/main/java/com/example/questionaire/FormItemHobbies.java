package com.example.questionaire;

import java.util.ArrayList;

public class FormItemHobbies extends FormItem {
    private ArrayList<String> hobbies;

    FormItemHobbies(String type, String text, String dbColumnName) {
        super(type, text, dbColumnName);
        hobbies = new ArrayList<>();
    }

    FormItemHobbies(String type, String text, String dbColumnName, ArrayList<String> hobbies) {
        super(type, text, dbColumnName);
        this.hobbies = hobbies;
    }

    public ArrayList<String> getHobbies() {
        return hobbies;
    }
}
