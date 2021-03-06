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

        addNewRandomValue();

    }

    public boolean mergeRow(int row, boolean right) {

        boolean canMerge = canMergeRow(row, right);

        for (int k = 0; k < 3; k++) {
            if (right) {
                int[] tileRow = tileGrid[row];
                for (int i = 3; i > 0; i--) {
                    if (tileRow[i] == tileRow[i - 1]) {
                        tileRow[i] *= 2;
                        tileRow[i - 1] = 0;
                        slideRow(row, right);
                    }
                }
            } else {
                int[] tileRow = tileGrid[row];
                for (int i = 0; i < 3; i++) {
                    if (tileRow[i] == tileRow[i + 1]) {
                        tileRow[i] *= 2;
                        tileRow[i + 1] = 0;
                        slideRow(row, right);
                    }
                }
            }
        }

        return canMerge;

    }

    public boolean canMergeRow(int row, boolean right) {

        if (right) {
            int[] tileRow = tileGrid[row];
            for (int i = 3; i > 0; i--) {
                if (tileRow[i] == tileRow[i - 1] &&
                    tileRow[i] != 0) {
                    return true;
                }
            }
        } else {
            int[] tileRow = tileGrid[row];
            for (int i = 0; i < 3; i++) {
                if (tileRow[i] == tileRow[i + 1] &&
                    tileRow[i] != 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean slideRow(int row, boolean right) {

        boolean merged = false;

        for (int k = 0; k < 3; k++) {
            if (right) {
                int[] tileRow = tileGrid[row];
                for (int i = 3; i > 0; i--) {
                    if (tileRow[i] == 0) {
                        tileRow[i] = tileRow[i - 1];
                        tileRow[i - 1] = 0;
                        if (tileRow[i] != 0) {
                            merged = true;
                        }
                    }
                }
            } else {
                int[] tileRow = tileGrid[row];
                for (int i = 0; i < 3; i++) {
                    if (tileRow[i] == 0) {
                        tileRow[i] = tileRow[i + 1];
                        tileRow[i + 1] = 0;
                        if (tileRow[i] != 0) {
                            merged = true;
                        }
                    }
                }
            }
        }

        return merged;

    }

    public boolean canMergeCol(int column, boolean down) {

        if (down) {
            for (int i = 3; i > 0; i--) {
                if (tileGrid[i][column] == tileGrid[i - 1][column] &&
                    tileGrid[i][column] != 0) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < 3; i++) {
                if (tileGrid[i][column] == tileGrid[i + 1][column] &&
                    tileGrid[i][column] != 0) {
                    return true;
                }
            }
        }

        return false;

    }

    public boolean mergeCol(int column, boolean down) {

        boolean canMerge = canMergeCol(column, down);

        for (int k = 0; k < 3; k++) {
            if (down) {
                for (int i = 3; i > 0; i--) {
                    if (tileGrid[i][column] == tileGrid[i - 1][column]) {
                        tileGrid[i][column] *= 2;
                        tileGrid[i - 1][column] = 0;
                        slideCol(column, down);
                    }
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    if (tileGrid[i][column] == tileGrid[i + 1][column]) {
                        tileGrid[i][column] *= 2;
                        tileGrid[i + 1][column] = 0;
                        slideCol(column, down);
                    }
                }
            }
        }

        return canMerge;

    }

    public boolean slideCol(int column, boolean down) {

        boolean merged = false;

        for (int k = 0; k < 3; k++) {
            if (down) {
                for (int i = 3; i > 0; i--) {
                    if (tileGrid[i][column] == 0) {
                        tileGrid[i][column] = tileGrid[i - 1][column];
                        tileGrid[i - 1][column] = 0;
                        if (tileGrid[i][column] != 0) {
                            merged = true;
                        }
                    }
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    if (tileGrid[i][column] == 0) {
                        tileGrid[i][column] = tileGrid[i + 1][column];
                        tileGrid[i + 1][column] = 0;
                        if (tileGrid[i][column] != 0) {
                            merged = true;
                        }
                    }
                }
            }
        }

        return merged;

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
                            return true;
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
