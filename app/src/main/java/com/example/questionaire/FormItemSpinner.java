package com.example.questionaire;

import java.util.ArrayList;

public class FormItemSpinner extends FormItem {
    private ArrayList<String> spinnerItems;
    private int selectedItemPosition;

    FormItemSpinner(String type, String text, String dbColumnName, ArrayList<String> spinnerItems) {
        super(type, text, dbColumnName);
        this.spinnerItems = spinnerItems;
        selectedItemPosition = 0;
    }

    FormItemSpinner(String type, String text, String dbColumnName, ArrayList<String> spinnerItems, int selectedItemPosition) {
        super(type, text, dbColumnName);
        this.spinnerItems = spinnerItems;
        this.selectedItemPosition = selectedItemPosition;
    }

    ArrayList<String> getSpinnerItems(){
        return spinnerItems;
    }

    int getSelectedItemPosition(){
        return selectedItemPosition;
    }
}
