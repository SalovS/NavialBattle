package com.company;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int shots = 0;
    private List<Ship> ships = new ArrayList<>();
    private Sea sea;

    public Player(String name) {
        this.name = name;
        sea = new Sea();
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public void addShot() {
        shots++;
    }

    public String getName() {
        return name;
    }

    public int getShots() {
        return shots;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public Sea getSea() {
        return sea;
    }

    public void addShip(Ship ship) {
        this.ships.add(ship);
    }

    public void setSea(Sea sea) {
        this.sea = sea;
    }
}
