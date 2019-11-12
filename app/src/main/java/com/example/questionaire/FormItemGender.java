package com.example.questionaire;

public class FormItemGender extends FormItem {
    private String gender;

    FormItemGender(String type, String text, String dbColumnName) {
        super(type, text, dbColumnName);
        gender = "";
    }

    FormItemGender(String type, String text, String dbColumnName, String gender) {
        super(type, text, dbColumnName);
        this.gender = gender;
    }

    String getGender(){
        return gender;
    }
}
