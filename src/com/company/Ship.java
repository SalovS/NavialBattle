package com.company;

public class Ship {
    private int[][] coords;
    private boolean isALive;
    private int length;
    private int haelth;

    public Ship(int[][] coords, boolean isALive) {
        this.coords = coords;
        this.length = coords.length;
        this.isALive = isALive;
        this.haelth = coords.length;
    }

    public int getHaelth() {
        return haelth;
    }

    public void getDamaged() {
        this.haelth--;
        if (haelth <= 0) {
            isALive = false;
            System.out.println("убил");
        }
        else{
            System.out.println("попал");
        }
    }

    public void setALive(boolean ALive) {
        isALive = ALive;
    }

    public int[][] getCoords() {
        return coords;
    }

    public boolean isALive() {
        return isALive;
    }

    public int getLength() {
        return length;
    }
}
