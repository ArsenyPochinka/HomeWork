package lesson_1.lesson4;

import java.util.Random;
import java.util.Scanner;

public class HomeWorkApp4 {
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();
    public static final char INPUT_X = 'X';
    public static final char INPUT_0 = '0';
    public static final char EMPTY_CELL = '_';
    public static char[][] field;
    public static int size;

    public static void main(String[] args) {
        selectTheField();
        fillTheField();
        printTheField();
        while (!drawnGame()) {
            playerMove();
            printTheField();
            if (drawnGame()) {
                System.out.println("Drawn game!");
                break;
            }
            if(victoryCheck(INPUT_X)) {
                System.out.println("You won!!!");
                break;
            }
            computerMove();
            printTheField();
            if(victoryCheck(INPUT_0)) {
                System.out.println("You've lost!");
                break;
            }
        }
        }

    private static boolean drawnGame() {
        boolean result=true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] == EMPTY_CELL) {
                    result = false;
                }
            }
        }
        return result;
    }

    private static void selectTheField() {
        System.out.println("Select the cell field of: \n1. 3x3 \n2. 5x5");
        int chooseTheField;
        do {
            System.out.println("input 1 or 2 to choose the type of game");
            chooseTheField = scanner.nextInt();
        }
        while (chooseTheField != 1 && chooseTheField != 2);
        if (chooseTheField == 1) {
            size = 3;
        } else {
            size = 5;
        }
    }

    private static void printTheField() {
        System.out.print(" ");
        for (int k = 1; k <= size; k++) {
            System.out.print(" " + k);
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void fillTheField() {
        field = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = EMPTY_CELL;
            }
        }
    }

    private static boolean checkingTheMove(int x, int y) {
        if (field[y][x]==EMPTY_CELL) {
            return true;
        }
        else {
            return false;
        }
    }


    private static void playerMove() {
        int x, y;
        do {
            System.out.println("enter the coordinates of your move (x,y)");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }
        while (!(x >= 0 && x <= size && y >= 0 && y <= size && checkingTheMove(x,y)));
        field[y][x] = INPUT_X;
    }

    private static void computerMove() {
        boolean move = false;
        outblock5: if (size == 5 && !move) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size - 2; j++) {
                    // checking for the presence of three "X" in the horizontal
                    if (field[i][j] == INPUT_X && field[i][j] == field[i][j + 1] && field[i][j] == field[i][j + 2]) {
                        if (j != 2 && checkingTheMove(j + 3, i)) {
                            field[i][j + 3] = INPUT_0;
                            move = true;
                            break outblock5;
                        } else if (j == 2 && checkingTheMove(1, i)) {
                            field[i][1] = INPUT_0;
                            move = true;
                            break outblock5;
                        }
                    }
                    // checking for the presence of three "X" in the vertical
                    else if (field[j][i] == INPUT_X && field[j][i] == field[j + 1][i] && field[j][i] == field[j + 2][i]) {
                        if (j != 2 && checkingTheMove(i, j + 3)) {
                            field[j + 3][i] = INPUT_0;
                            move = true;
                            break outblock5;
                        } else if (j == 2 && checkingTheMove(i, 1)) {
                            field[1][i] = INPUT_0;
                            move = true;
                            break outblock5;
                        }
                    }
                }
            }
            if (!move) {
                //checking for the presence of three "X" on the diagonals "\"
                for (int k = 0; k < size - 2; k++) {
                    if (field[k][k] == INPUT_X && field[k + 1][k + 1] == INPUT_X && field[k + 2][k + 2] == INPUT_X) {
                        if (k != 2 && checkingTheMove(k + 3, k + 3)) {
                            field[k + 3][k + 3] = INPUT_0;
                            move = true;
                            break outblock5;
                        } else if (k == 2 && checkingTheMove(1, 1)) {
                            field[1][1] = INPUT_0;
                            move = true;
                            break outblock5;
                        }
                    }
                        else if (k != 0 && field[k - 1][k] == INPUT_X && field[k][k + 1] == INPUT_X && field[k + 1][k + 2] == INPUT_X) {
                            if (k==1 && checkingTheMove(4, 3)) {
                                field[3][4] = INPUT_0;
                                move = true;
                                break outblock5;
                            }
                            else if (k==2 && checkingTheMove(1, 0)) {
                                field[0][1] = INPUT_0;
                                move = true;
                                break outblock5;
                            }
                        }
                        else if (k != 0 && field[k][k - 1] == INPUT_X && field[k + 1][k] == INPUT_X && field[k + 2][k + 1] == INPUT_X) {
                            if (k == 1 && checkingTheMove(3, 4)) {
                                field[4][3] = INPUT_0;
                                move = true;
                                break outblock5;
                            }
                            else if (k==2 && checkingTheMove(0, 1)) {
                                field[1][0] = INPUT_0;
                                move = true;
                                break outblock5;
                            }
                        }
                    }
                }
            if (!move) {
                //checking for the presence of three "X" on the diagonals "/"
                for (int k = 0; k < size - 2; k++) {
                    if (field[k][4 - k] == INPUT_X && field[k + 1][3 - k] == INPUT_X && field[k + 2][2 - k] == INPUT_X) {
                        if (k != 2 && checkingTheMove(1-k, k+3)) {
                            field[k + 3][1 - k] = INPUT_0;
                            move = true;
                            break outblock5;
                        } else if (k==2 && checkingTheMove(3, 1)) {
                            field[1][3] = INPUT_0;
                            move = true;
                            break outblock5;
                        }
                    } else if (k > 0 && field[k - 1][4 - k] == INPUT_X && field[k][3 - k] == INPUT_X && field[k + 1][2 - k] == INPUT_X) {
                        if (k == 1 && checkingTheMove(0, 3)) {
                            field[3][0] = INPUT_0;
                            move = true;
                            break outblock5;
                        } else if (k==2 && checkingTheMove(1, 0)) {
                            field[0][1] = INPUT_0;
                            move = true;
                            break outblock5;
                        }
                    } else if (k > 0 && field[k][5 - k] == INPUT_X && field[k + 1][4 - k] == INPUT_X && field[k + 2][3 - k] == INPUT_X) {
                        if (k == 1 && checkingTheMove(1, 4)) {
                            field[4][1] = INPUT_0;
                            move = true;
                            break outblock5;
                        } else if (k==2 && checkingTheMove(4, 1)) {
                            field[1][4] = INPUT_0;
                            move = true;
                            break outblock5;
                        }
                    }
                }
            }
            if (!move) {
                int x, y;
                do {
                    x = random.nextInt(size);
                    y = random.nextInt(size);
                }
                while (!checkingTheMove(x, y));
                field[y][x] = INPUT_0;
            }
        }

        outblock3: if (size == 3 && !move) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size - 1; j++) {
                    // checking for the presence of two "X" in the horizontal
                    if (field[i][j] == INPUT_X && field[i][j] == field[i][j + 1]) {
                        if (j == 0 && checkingTheMove(j+2, i)) {
                            field[i][j + 2] = INPUT_0;
                            move = true;
                            break outblock3;
                        } else if (j==1 && checkingTheMove(0, i)) {
                            field[i][0] = INPUT_0;
                            move = true;
                            break outblock3;
                        }
                    }
                    // checking for the presence of two "X" in the vertical
                    else if (field[j][i] == INPUT_X && field[j][i] == field[j + 1][i]) {
                        if (j == 0 && checkingTheMove(i, j+2)) {
                            field[j + 2][i] = INPUT_0;
                            move = true;
                            break outblock3;
                        } else if (j==1 && checkingTheMove(i, 0)) {
                            field[0][i] = INPUT_0;
                            move = true;
                            break outblock3;
                        }
                    }
                }
            }
            if (!move) {
                //checking for the presence of two "X" on the diagonals "\"
                for (int k = 0; k < size - 1; k++) {
                    if (field[k][k] == INPUT_X && field[k + 1][k + 1] == INPUT_X) {
                        if (k == 0 && checkingTheMove(2, 2)) {
                            field[2][2] = INPUT_0;
                            move = true;
                            break outblock3;
                        } else if (k==1 && checkingTheMove(0, 0)) {
                            field[0][0] = INPUT_0;
                            move = true;
                            break outblock3;
                        }
                    }
                }
            }
            if (!move) {
                //checking for the presence of three "X" on the diagonals "/"
                for (int k = 0; k < size - 1; k++) {
                    if (field[k][2 - k] == INPUT_X && field[k + 1][1 - k] == INPUT_X) {
                        if (k == 0 && checkingTheMove(0, 2)) {
                            field[2][0] = INPUT_0;
                            move = true;
                            break outblock3;
                        } else if (k==1 && checkingTheMove(2, 0)) {
                            field[0][2] = INPUT_0;
                            move = true;
                            break outblock3;
                        }
                    }
                }
            }
            if (!move) {
                int x, y;
                do {
                    x = random.nextInt(size);
                    y = random.nextInt(size);
                }
                while (!checkingTheMove(x, y));
                field[y][x] = INPUT_0;
            }
        }
    }

    private static boolean victoryCheck(char input) {
        boolean result=false;
        if(size==5 && !result) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size - 3; j++) {
                    if (       (field[i][j] == input && field[i][j] == field[i][j + 1] && field[i][j] == field[i][j + 2] && field[i][j] == field[i][j + 3])
                            || (field[j][i] == input && field[j][i] == field[j + 1][i] && field[j][i] == field[j + 2][i]&& field[j][i] == field[j + 3][i])
                            || (field[j][j] == input && field[j][j] == field[j+1][j+1] && field[j][j] == field[j+2][j + 2] && field[j][j] == field[j+3][j + 3])
                            || (field[0][1]==input && field[0][1]==field[1][2] && field[0][1]==field[2][3] && field[0][1]==field[3][4])
                            || (field[1][0]==input && field[1][0]==field[2][1] && field[1][0]==field[3][2] && field[1][0]==field[4][3])
                            || (field[j][4-j] == input && field[j][4-j] == field[j+1][3-j] && field[j][4-j] == field[j+2][2-j] && field[j][4-j] == field[j+3][1-j])
                            || (field[3][0]==input && field[3][0]==field[2][1] && field[3][0]==field[1][2] && field[3][0]==field[0][3])
                            || (field[4][1]==input && field[4][1]==field[3][2] && field[4][1]==field[2][3] && field[4][1]==field[1][4])) {
                        result = true;
                    }
                }
                            }
        }
        else if(size==3 && !result) {
            for (int i = 0; i < size; i++) {
                    if (       (field[i][0] == input && field[i][0] == field[i][1] && field[i][0] == field[i][2])
                            || (field[0][i] == input && field[0][i] == field[1][i] && field[0][i] == field[2][i])
                            || (field[0][0] == input && field[0][0] == field[1][1] && field[0][0] == field[2][2])
                            || (field[0][2] == input && field[0][2] == field[1][1] && field[0][2] == field[2][0])) {
                        result = true;
                    }
                }
            }
        else {
           result = false;
        }
        return result;
        }
    }
