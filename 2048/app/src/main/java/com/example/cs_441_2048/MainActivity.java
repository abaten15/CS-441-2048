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

        ImageView imageOne = findViewById(R.id.tileone);
        updateImage(imageOne, tileGrid[0][0]);
        ImageView imageTwo = findViewById(R.id.tiletwo);
        updateImage(imageTwo, tileGrid[0][1]);
        ImageView imageThree = findViewById(R.id.tilethree);
        updateImage(imageThree, tileGrid[0][2]);
        ImageView imageFour = findViewById(R.id.tilefour);
        updateImage(imageFour, tileGrid[0][3]);
        ImageView imageFive = findViewById(R.id.tilefive);
        updateImage(imageFive, tileGrid[1][0]);
        ImageView imageSix = findViewById(R.id.tilesix);
        updateImage(imageSix, tileGrid[1][1]);
        ImageView imageSeven = findViewById(R.id.tileseven);
        updateImage(imageSeven, tileGrid[1][2]);
        ImageView imageEight = findViewById(R.id.tileeight);
        updateImage(imageEight, tileGrid[1][3]);
        ImageView imageNine = findViewById(R.id.tilenine);
        updateImage(imageNine, tileGrid[2][0]);
        ImageView imageTen = findViewById(R.id.tileten);
        updateImage(imageTen, tileGrid[2][1]);
        ImageView imageEleven = findViewById(R.id.tileeleven);
        updateImage(imageEleven, tileGrid[2][2]);
        ImageView imageTwelve = findViewById(R.id.tiletwelve);
        updateImage(imageTwelve, tileGrid[2][3]);
        ImageView imageThirteen = findViewById(R.id.tilethirteen);
        updateImage(imageThirteen, tileGrid[3][0]);
        ImageView imageFourteen = findViewById(R.id.tilefourteen);
        updateImage(imageFourteen, tileGrid[3][1]);
        ImageView imageFifteen = findViewById(R.id.tilefifteen);
        updateImage(imageFifteen, tileGrid[3][2]);
        ImageView imageSixteen = findViewById(R.id.tilesixteen);

        updateImage(imageSixteen, tileGrid[3][3]);
    }

    private void updateImage(ImageView imageView, int value) {
        int power = getPower(value);
        Log.v(TAG, "power - " + power);
        if (power == -1) {
            imageView.setImageResource(R.drawable.tile);
        } else if (power == 0) {
            imageView.setImageResource(R.drawable.tiletwo);
        } else if (power == 1) {
            imageView.setImageResource(R.drawable.tilethree);
        } else if (power == 2) {
            imageView.setImageResource((R.drawable.tilefour));
        }
    }

    private int getPower(int num) {

        if (num == 0) {
            return -1;
        } else if (num == 2) {
            return 0;
        } else {
            int count = 0;
            while (num != 2) {
                count++;
                num /= 2;
                if (count >= 3) {
                    count = 0;
                }
            }
            return count;
        }

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
