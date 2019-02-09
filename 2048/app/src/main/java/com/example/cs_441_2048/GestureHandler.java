package com.example.cs_441_2048;

import android.view.MotionEvent;

public class GestureHandler extends android.view.GestureDetector.SimpleOnGestureListener {

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    private MainActivity mainActivity;

    public GestureHandler(MainActivity mainIn) {
        mainActivity = mainIn;
    }

    @Override
    public boolean onDown(MotionEvent event) {
        mainActivity.swipeDown();
        return false;
    }

}
