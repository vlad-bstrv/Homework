package lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ticTakMain {
    private static final char DOT_EMPTY = '*';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    public static int SIZE = 3;
    public static char[][] field;
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();


    public static void main(String[] args) {
        start();
    }

    static void start() {
        initField(SIZE);
        printField();
        while(true) {
            humanTurn();
            printField();
            if(checkWin(DOT_X)) {
                System.out.println("Победа");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Ничья");
                break;
            }

            aiTurn();
            printField();
            if(checkWin(DOT_X)) {
                System.out.println("Проигрыш");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Ничья");
                break;
            }
        }
    }

    public static boolean isFieldFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }


    private static boolean checkWin(char symb) {
        boolean check = false;
        int count = 0;

        //horizontal
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(field[i][j] == symb) count += 1;
                if(count == SIZE) check = true;
            }
            count = 0;
        }

        //vertical
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(field[j][i] == symb) count += 1;
                if(count == SIZE) check = true;
            }
            count = 0;
        }

        //diagonal
        for (int i = 0; i < SIZE; i++) {
            if(field[i][i] == symb) count += 1;
        }
        if (count == SIZE) check = true;
        count = 0;

        for (int i = 0; i < SIZE; i++) {
            if(field[i][SIZE - i - 1] == symb) count += 1;
        }
        if (count == SIZE) check = true;

        return check;

    }

    private static void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        field[y][x] = DOT_O;

    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите X от 1 до 3");
            x = scanner.nextInt() - 1;
            System.out.println("Введите Y от 1 до 3");
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        field[y][x] = DOT_X;
    }

    private static boolean isCellValid(int x, int y) {
        if(x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if(field[y][x] == DOT_EMPTY) return true;
        return false;
    }

    private static void printField() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void initField(int size) {
        field = new char[size][size];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = DOT_EMPTY;
            }
        }
    }
}
