package com.example.cs_441_2048;

import java.util.Random;

public class TileTracker {

    int[][] tileGrid;
    Random randomValGen;

    public TileTracker() {

        randomValGen = new Random();

        tileGrid = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tileGrid[i][j] = 0;
            }
        }

        tileGrid[0][0] = 2;

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

    }

}
