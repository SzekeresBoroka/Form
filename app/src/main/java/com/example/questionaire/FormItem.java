package com.example.questionaire;

public class FormItem {
    private String type, text, dbColumnName;

    FormItem(String type, String dbColumnName){
        this.type = type;
        this.dbColumnName = dbColumnName;
    }

    FormItem(String type, String text, String dbColumnName){
        this.type = type;
        this.text = text;
        this.dbColumnName = dbColumnName;
    }

    public String getDbColumnName() {
        return dbColumnName;
    }

    String getType(){
        return type;
    }

    String getText(){
        return text;
    }
}

