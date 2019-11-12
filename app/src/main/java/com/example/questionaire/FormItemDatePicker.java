package com.example.questionaire;

public class FormItemDatePicker extends FormItem {
    private String date;

    FormItemDatePicker(String type, String text, String dbColumnName) {
        super(type, text, dbColumnName);
        date = "";
    }

    FormItemDatePicker(String type, String text, String dbColumnName, String date) {
        super(type, text, dbColumnName);
        this.date = date;
    }

    String getDate(){
        return date;
    }
}
