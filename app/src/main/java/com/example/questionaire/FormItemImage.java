package com.example.questionaire;

import android.graphics.Bitmap;

public class FormItemImage extends FormItem {
    private Bitmap image;

    FormItemImage(String type, String text, String dbColumnName) {
        super(type, text, dbColumnName);
    }

    FormItemImage(String type, String text, String dbColumnName, Bitmap image) {
        super(type, text, dbColumnName);
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }
}
