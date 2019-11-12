package com.example.questionaire;

public class FormItemYearOfStudy extends FormItem {
    private String yearOfStudy;

    FormItemYearOfStudy(String type, String text, String dbColumnName) {
        super(type, text, dbColumnName);
        yearOfStudy = "";
    }

    FormItemYearOfStudy(String type, String text, String dbColumnName, String yearOfStudy) {
        super(type, text, dbColumnName);
        this.yearOfStudy = yearOfStudy;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }
}
