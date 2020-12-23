package com.company;


import java.util.List;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);

    public void start(Player player1, Player player2) {
        boolean thуGameContinues = true;
        boolean firstPlayersMove = true;
        while (thуGameContinues) {
            if (firstPlayersMove) {
                printSea(player2);
                if (gameIsOver(player2))
                    thуGameContinues = false;
                System.out.println("Ход :" + player1.getName());
                if (!salvo(player2))
                    firstPlayersMove = false;
            } else {
                printSea(player1);
                if (gameIsOver(player1))
                    thуGameContinues = false;
                System.out.println("Ход :" + player2.getName());
                if (!salvo(player1))
                    firstPlayersMove = true;
            }
        }
        scanner.close();
    }

    private boolean gameIsOver(Player player) {
        int count = 0;
        for (Ship ship : player.getShips()) {
            if (ship.isALive())
                count++;
        }
        if (count == 0)
            System.out.println("У игрока " + player.getName() + " закончились карабли");
        return count == 0;
    }

    private void printSea(Player player) {
        //clearScreen();
        Sea sea = player.getSea();
        System.out.println("     А  Б  В  Г  Д  Е  Ж  З  И  К");
        for (int i = 0; i < 10; i++) {
            System.out.printf("%2d ", i);
            for (int j = 0; j < 10; j++) {
                System.out.printf("%3s", sea.getGrid()[j][i]);
            }
            System.out.println();
        }
    }

    public boolean salvo(Player player) {
        boolean gotAHit = false;
        boolean isCorrect = false;
        int[] coords = new int[2];
        Sea sea = player.getSea();
        while (!isCorrect) {
            isCorrect = true;
            try {
                System.out.println("Введите координату в формате: 1,1");
                String[] text = scanner.nextLine().split(",");
                if (text.length != 2)
                    throw new Exception();
                coords[0] = Integer.parseInt(text[0]);
                coords[1] = Integer.parseInt(text[1]);
                if (coords[0] < 10 & coords[0] >= 0 & coords[1] < 10 & coords[1] >= 0)
                    isCorrect = false;
                if (sea.getObject(coords[0], coords[1]).equals("O") ||
                        sea.getObject(coords[0], coords[1]).equals("*"))
                    isCorrect = false;
            } catch (Exception e) {
            }
        }
        if (checkTheHit(player, coords)) {
            sea.addObject(coords[0], coords[1], "*");
            gotAHit = true;
        } else
            sea.addObject(coords[0], coords[1], "O");

        player.setSea(sea);
        return gotAHit;
    }

    public boolean checkTheHit(Player player, int[] coords) {
        List<Ship> ships = player.getShips();
        for (int i = 0; i < ships.size(); i++) {
            for (int j = 0; j < ships.get(i).getCoords().length; i++) {
                if (ships.get(i).getCoords()[i] == coords) {
                    ships.get(i).getDamaged();
                    player.setShips(ships);
                    return true;
                }
            }
        }
        return false;
    }

    public void getShips(Player player) {

        int[] maxShipL = new int[]{4, 3, 2, 1};
        String messageText = "";
        boolean isReadData = true;
        while (isReadData) {
            //clearScreen();
            System.out.println(messageText);
            int[] shipL = new int[]{0, 0, 0, 0};
            System.out.println("Игрок " + player.getName() + " расставляет карабли");
            printInputRules();

            for (Ship ship : player.getShips()) {
                shipL[ship.getLength() - 1]++;
            }

            System.out.printf("Выставлено: %d однопалубных из %d, %d двухпалубных из %d," +
                            " %d трёхпалубных из %d, %d четырёхпалубных из %d\n",
                    shipL[0], maxShipL[0], shipL[1], maxShipL[1], shipL[2], maxShipL[2], shipL[3], maxShipL[3]);

            printShips(player);

            String text = "";
            if (scanner.hasNextLine())
                text = scanner.nextLine();
            Ship ship = parseShip(text);
            if (ship.isALive() == false) {
                messageText = "Некоректный ввод координат";
                continue;
            }

            if (chekShip(ship, player.getShips())) {
                if (shipL[ship.getLength() - 1] == maxShipL[ship.getLength() - 1]) {
                    messageText = "Кораблей с количеством палуб " + ship.getLength() + " - максимум\n";
                    continue;
                }
                shipL[ship.getLength() - 1]++;
                player.addShip(ship);
                messageText = "Корабль добавлен";

            } else {
                messageText = "Вокруг корабля должна быть область шириной в одну клетку," +
                        " в которой не может быть других кораблей (ореол корабля)";
            }

            if (shipL[0] == maxShipL[0] & shipL[1] == maxShipL[1] & shipL[2] == maxShipL[2] & shipL[3] == maxShipL[3]) {
                isReadData = false;
            }
        }
    }

    private boolean chekShip(Ship ship, List<Ship> ships) {
        for (Ship oldShip : ships) {
            for (int[] oldShipCoord : oldShip.getCoords()) {
                for (int[] coord : ship.getCoords()) {
                    if (oldShipCoord[0] - 1 == coord[0] & oldShipCoord[1] == coord[1])
                        return false;
                    if (oldShipCoord[0] - 1 == coord[0] & oldShipCoord[1] - 1 == coord[1])
                        return false;
                    if (oldShipCoord[0] - 1 == coord[0] & oldShipCoord[1] + 1 == coord[1])
                        return false;
                    if (oldShipCoord[0] + 1 == coord[0] & oldShipCoord[1] == coord[1])
                        return false;
                    if (oldShipCoord[0] + 1 == coord[0] & oldShipCoord[1] - 1 == coord[1])
                        return false;
                    if (oldShipCoord[0] + 1 == coord[1] & oldShipCoord[1] + 1 == coord[1])
                        return false;
                    if (oldShipCoord[0] == coord[0] & oldShipCoord[1] == coord[1])
                        return false;
                    if (oldShipCoord[0] == coord[0] & oldShipCoord[1] - 1 == coord[1])
                        return false;
                    if (oldShipCoord[0] == coord[0] & oldShipCoord[1] + 1 == coord[1])
                        return false;
                }
            }
        }
        return true;
    }

    private Ship parseShip(String text) {
        String[] arr = text.split(";");
        int[][] coords = new int[arr.length][2];
        Ship ship = new Ship(coords, false);
        if (arr.length > 4)
            return ship;
        try {
            for (int i = 0; i < arr.length; i++) {
                String[] coord = arr[i].split(",");
                if (coord.length > 2)
                    return ship;
                coords[i][0] = Integer.parseInt(coord[0]);
                coords[i][1] = Integer.parseInt(coord[1]);
            }
        } catch (Exception e) {
            return ship;
        }
        if (!checkCoords(coords))
            return ship;

        return new Ship(coords, true);
    }

    private boolean checkCoords(int[][] coords) {
        boolean horizontal = true;
        boolean vertical = true;
        for (int i = 0; i < coords.length; i++) {
            if (coords[i][0] > 9 || coords[i][0] < 0 || coords[i][1] > 9 || coords[i][1] < 0)
                return false;
            if (i < coords.length - 1) {
                if (!(coords[i][0] + 1 == coords[i + 1][0] && coords[i][1] == coords[i + 1][1]))
                    horizontal = false;
                if (!(coords[i][0] == coords[i + 1][0] && coords[i][1] + 1 == coords[i + 1][1]))
                    vertical = false;
            }
        }
        return horizontal || vertical;
    }

    public void printShips(Player player) {
        Sea sea = new Sea();
        for (Ship ship : player.getShips()) {
            for (int[] coord : ship.getCoords()) {
                sea.addObject(coord[0], coord[1], "P");
            }
        }

        System.out.println("     А  Б  В  Г  Д  Е  Ж  З  И  К");
        for (int i = 0; i < 10; i++) {
            System.out.printf("%2d ", i);
            for (int j = 0; j < 10; j++) {
                System.out.printf("%3s", sea.getGrid()[j][i]);
            }
            System.out.println();
        }
    }

    public static Player getPlayer() {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        scanner.close();
        return new Player(name);
    }

    public void clearScreen() {
//            try {
//                Runtime.getRuntime().exec("cls");
//            }catch (Exception e){
//                System.out.println(e.getMessage());
//            }
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printInputRules() {
        System.out.println("Для того, чтобы расставить корабли, игрок последовательно вводит координаты всех 10" +
                " кораблей в консоль. Формат координат следующий:\n" +
                "x1,y1;x2,y2;x3,y3;x4,y4 - для четырехпалубного корабля\n" +
                "x1,y1;x2,y2;x3,y3 - для трехпалубных кораблей\n" +
                "x1,y1;x2,y2 - для двухпалубных кораблей\n" +
                "x1,y1 - для однопалубных кораблей\n");
    }
}
