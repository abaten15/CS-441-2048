package com.example.cs_441_2048;

import android.widget.TextView;

import java.util.Random;

public class TileTracker {

    private int[][] tileGrid;
    private Random randomValGen;

    public TileTracker() {

        randomValGen = new Random();

        tileGrid = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tileGrid[i][j] = 0;
            }
        }

        tileGrid[0][0] = 2;
        tileGrid[0][2] = 4;

        slideRow(0, true);


    }

    private void slideRow(int row, boolean right) {

        for (int k = 0; k < 3; k++) {
            if (right) {
                int[] tileRow = tileGrid[row];
                for (int i = 3; i > 0; i--) {
                    if (tileRow[i] == 0) {
                        tileRow[i] = tileRow[i - 1];
                        tileRow[i - 1] = 0;
                    }
                }
            } else {
                int[] tileRow = tileGrid[row];
                for (int i = 0; i < 3; i++) {
                    if (tileRow[i] == 0) {
                        tileRow[i] = tileRow[i + 1];
                        tileRow[i + 1] = 0;
                    }
                }
            }
        }

    }

    public boolean swipeRight() {


        return false;

    }

    public boolean addNewRandomValue() {

        boolean successful = false;
        int countEmpty = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tileGrid[i][j] == 0) {
                    successful = true;
                    countEmpty++;
                }
            }
        }

        if (countEmpty > 0) {
            int indexToChange = randomValGen.nextInt(countEmpty);

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (tileGrid[i][j] == 0) {
                        // add value here if appropriate
                        if (indexToChange == 0) {
                            int probability = randomValGen.nextInt(4);
                            if (probability == 2) {
                                tileGrid[i][j] = 4;
                            } else {
                                tileGrid[i][j] = 2;
                            }
                        }
                        // Keep indexing grid
                        else {
                            indexToChange -= 1;
                        }
                    }
                }
            }

        }

        return successful;

    }

    public void update(MainActivity mainActivity) {
        TextView one = mainActivity.findViewById(R.id.textone);
        TextView two = mainActivity.findViewById(R.id.texttwo);
        TextView three = mainActivity.findViewById(R.id.textthree);
        TextView four = mainActivity.findViewById(R.id.textfour);

        one.setText(tileGrid[0][0]);
        two.setText(tileGrid[0][1]);
        three.setText(tileGrid[0][2]);
        four.setText(tileGrid[0][3]);

    }

    public int[][] getTileGrid() {
        return tileGrid;
    }

}
