package com.example.cs_441_2048;

import android.widget.ImageView;
import android.widget.TextView;

public class TileTracker {

    int value = 0;
    private int ID;

    public TileTracker(int ID) {

        this.ID = ID;

    }

    public void update(MainActivity mainActivity) {
        TextView textView = mainActivity.findViewById(ID);
        textView.setText("" + value);
    }

}
