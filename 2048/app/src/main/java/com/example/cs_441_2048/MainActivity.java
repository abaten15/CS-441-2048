package com.example.cs_441_2048;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Button;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;

import java.lang.Math;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TileTracker tracker;

    private static final String TAG = "MAIN_ACTIVITY";

    private GestureDetectorCompat gestureDetectorCompat = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tracker = new TileTracker();

        int[][] tileGrid = tracker.getTileGrid();
        update(tileGrid);

/*
        Button button = findViewById(R.id.swipedetector);
        button.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                Log.v(TAG, "drag");
                return true;
            }
        });
*/

        GestureHandler gestureHandler = new GestureHandler();
        gestureHandler.setActivity(this);

        gestureDetectorCompat = new GestureDetectorCompat(this, gestureHandler);

    }

    int lastX = -1;
    int lastY = -1;

    private static final int SWIPE_THRESHOLD = 100;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            lastX = (int) event.getX();
            lastY = (int) event.getY();
        } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            int deltaX = x - lastX;
            int deltaY = y - lastY;
            Log.v(TAG, "" + x +" " + y + " " + lastX + " " + lastY);
            Log.v(TAG, "deltaX = " + deltaX + " | deltaY = " + deltaY * -1);
            if (deltaX > SWIPE_THRESHOLD && deltaX > 0) {
                Log.v(TAG, "swipe right " + deltaX);
                swipeRight();
            } else if (deltaX * -1 > SWIPE_THRESHOLD && deltaX < 0){
                Log.v(TAG, "swipe left " + deltaX);
                swipeLeft();
            }
            if (deltaY > SWIPE_THRESHOLD && deltaY > 0) {
                Log.v(TAG, "swipe down " + deltaY);
                swipeDown();
            } else if (deltaY - deltaY - deltaY > SWIPE_THRESHOLD && deltaY < 0) {
                Log.v(TAG, "swipe up" + deltaY);
                swipeUp();
            }

        }
        return true;
    }

    public void swipeRight() {
        for (int i = 0; i < 4; i++) {
            tracker.slideRow(i, true);
            tracker.mergeRow(i, true);
        }
        tracker.addNewRandomValue();
        update(tracker.getTileGrid());
    }

    public void swipeLeft() {
        for (int i = 0; i < 4; i++) {
            tracker.slideRow(i, false);
            tracker.mergeRow(i, false);
        }
        tracker.addNewRandomValue();
        update(tracker.getTileGrid());
    }

    public void swipeDown() {
        for (int i = 0; i < 4; i++) {
            tracker.slideCol(i, true);
            tracker.mergeCol(i, true);
        }
        tracker.addNewRandomValue();
        update(tracker.getTileGrid());
    }

    public void swipeUp() {
        for (int i = 0; i < 4; i++) {
            tracker.slideCol(i, false);
            tracker.mergeCol(i, false);
        }
        tracker.addNewRandomValue();
        update(tracker.getTileGrid());
    }

    private void update(int[][] tileGrid) {

        TextView one = findViewById(R.id.textone);
        TextView two = findViewById(R.id.texttwo);
        TextView three = findViewById(R.id.textthree);
        TextView four = findViewById(R.id.textfour);
        TextView five = findViewById(R.id.textfive);
        TextView six = findViewById(R.id.textsix);
        TextView seven = findViewById(R.id.textseven);
        TextView eight = findViewById(R.id.texteight);
        TextView nine = findViewById(R.id.textnine);
        TextView ten = findViewById(R.id.textten);
        TextView eleven = findViewById(R.id.texteleven);
        TextView twelve = findViewById(R.id.texttwelve);
        TextView thirteen = findViewById(R.id.textthirteen);
        TextView fourteen = findViewById(R.id.textfourteen);
        TextView fifteen = findViewById(R.id.textfifteen);
        TextView sixteen = findViewById(R.id.textsixteen);

        if (one == null) {
            return;
        }

        one.setText("" + tileGrid[0][0]);
        two.setText("" + tileGrid[0][1]);
        three.setText("" + tileGrid[0][2]);
        four.setText("" + tileGrid[0][3]);
        five.setText("" + tileGrid[1][0]);
        six.setText("" + tileGrid[1][1]);
        seven.setText("" + tileGrid[1][2]);
        eight.setText("" + tileGrid[1][3]);
        nine.setText("" + tileGrid[2][0]);
        ten.setText("" + tileGrid[2][1]);
        eleven.setText("" + tileGrid[2][2]);
        twelve.setText("" + tileGrid[2][3]);
        thirteen.setText("" + tileGrid[3][0]);
        fourteen.setText("" + tileGrid[3][1]);
        fifteen.setText("" + tileGrid[3][2]);
        sixteen.setText("" + tileGrid[3][3]);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
