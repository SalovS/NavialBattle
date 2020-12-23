package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Player player1 = new Player("игрок 1");
        Player player2 = new Player("игрок 2");
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship(new int[][]{{0,0},{0,1},{0,2},{0,3}},true));
        ships.add(new Ship(new int[][]{{2,0},{2,1},{2,2}},true));
        ships.add(new Ship(new int[][]{{4,0},{4,1},{4,2}},true));
        ships.add(new Ship(new int[][]{{6,0},{6,1}},true));
        ships.add(new Ship(new int[][]{{6,3},{6,4}},true));
        ships.add(new Ship(new int[][]{{6,6},{6,7}},true));
        ships.add(new Ship(new int[][]{{8,0}},true));
        ships.add(new Ship(new int[][]{{8,2}},true));
        ships.add(new Ship(new int[][]{{8,4}},true));
        ships.add(new Ship(new int[][]{{8,6}},true));
        List<Ship> ships2 = ships;
        player1.setShips(ships);
        player2.setShips(ships2);
        Game game = new Game();
//        game.getShips(player1);
//        game.getShips(player2);
        game.start(player1, player2);
    }
}
