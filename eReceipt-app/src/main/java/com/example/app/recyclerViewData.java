package com.example.app;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

public class recyclerViewData {
    private String text; //is the name of the folder
    private int buttonId; //is the button's id //I set the buttonId to a string for now

    public recyclerViewData(String text, int buttonId) {
        this.text = text;
        this.buttonId = buttonId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getButtonId() {
        return buttonId;
    }

    public void setButtonId(int buttonId) {
        this.buttonId = buttonId;
    }

}
