package com.company;

public class Sea {
    private static final int LENGTH = 10;
    private static final int WIDTH = 10;
    String[][] grid = new String[LENGTH][WIDTH];

    public Sea() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = " ";
            }
        }
    }

    public void addObject(int x, int y, String object) {
        grid[x][y] = object;
    }

    public String[][] getGrid() {
        return grid;
    }

    public String getObject(int x, int y) {
        return grid[x][y];
    }
}
