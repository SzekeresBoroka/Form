package com.example.questionaire;

public class FormItemText extends FormItem {
    private String hint, value;

    FormItemText(String type, String text, String dbColumnName, String hint, String value) {
        super(type, text, dbColumnName);
        this.hint = hint;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    String getHint(){
        return hint;
    }
}
