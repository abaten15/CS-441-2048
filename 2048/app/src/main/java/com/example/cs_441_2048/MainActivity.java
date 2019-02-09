package com.example.cs_441_2048;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TileTracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        final GestureDetector gestureDetector = new GestureDetector(this);

        ImageView swipeDetector = findViewById(R.id.swipedetector);
        swipeDetector.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch (View v, MotionEvent e) {
                return ;
            }
        });

        tracker = new TileTracker();

        int[][] tileGrid = tracker.getTileGrid();
        update(tileGrid);

    }

    public void swipeDown() {
        for (int i = 0; i < 4; i++) {
            tracker.slideCol(i, true);
        }
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
