package connect4;
import java.util.Objects;
import java.util.Scanner;

public class main {
    private static String[][] board; // main board

    // rows / columns of the board
    public static int rows;
    public static int columns;

    // signs for players
    public static String player1;
    public static String player2;

    // stop the run of the program
    public static boolean prg;


    public static void main(String[] args) {

        // main menu
        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("Hello and welcome to CONNECT 4 !!!");
        System.out.println("------------------------------------");


        player1 = "0"; // the sign for player one
        player2 = "+"; // the sign for player two

        // rows and columns, can be changed with no more 1 difference between them
        rows = 6;
        columns = 7;

        // gen the board
        boardGen(rows,columns);

        // start playing
        gamePlay();

        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("Thank you for playing! ");
        System.out.println("------------------------------------");



    }

    // board generator
    public static void boardGen(int rows, int columns) {
        board = new String[rows][columns];  // create the 2d array with set parrameters

        // create the board with 2 loops
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = " ";

            }
        }
    }

    // print the board
    public static void boardPrint(int rows, int columns) {
        // the header and left column for easier use
        String heading ="r / c     ";

        for(int i = 1; columns >= i; i++){
            heading = heading + String.format("%d", i) + "   ";
        }

        System.out.print(heading);
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.println();
            System.out.print(" #" + (i + 1) + "     | ");

            for (int j = 0; j < columns; j++) {
                System.out.print(board[i][j] + " | ");

            }
        }
    }

    public static void move(String player){
        Scanner sc = new Scanner(System.in);
        int a = 0;

        //player 1
        if(Objects.equals(player, player1)){
            System.out.println();
            System.out.println("Player 1 what is your move? (if you want to finish put -1)");
        }

        // player 2
        else{
            System.out.println();
            System.out.println("Player 2 what is your move? (if you want to finish put -1)");
        }


        int column  = sc.nextInt(); // the column of choosing


        // check whether the column is full
        if(column >= 1 && column <= columns) {
            while (a < rows) {
                if (board[rows - a - 1][column - 1].equals(player1) || board[rows - a - 1][column - 1].equals(player2)) {
                    a++;
                    if(rows <= a){
                        System.out.println();
                        System.out.println("-------------------------------------------");
                        System.out.println("This column is full chose a different one!!");
                        System.out.println("-------------------------------------------");
                        System.out.println();
                        move(player);
                    }
                }
                else {
                    board[rows - a - 1][column - 1] = player;
                    a = rows + 1;

                }

            }

        }
        else {
            prg = false; // terminate the program if the number imputed is larger or smaller than the amount of columns
        }

        // check whether player 1 wins
        if(winPlayer1()){
            prg = false;
            System.out.println();
            System.out.println("---------------------------");
            System.out.println();
            System.out.println("Player 1 wins!!!");
            System.out.println();
            System.out.println("----------------------------");
            System.out.println();


        }

        // check whether player 2 wins
        if(winPlayer2()){
            prg = false;
            System.out.println();
            System.out.println("---------------------------");
            System.out.println();
            System.out.println("Player 2 wins!!!");
            System.out.println();
            System.out.println("----------------------------");
            System.out.println();
        }

    }


    // check the board for player 1 win
    public static Boolean winPlayer1(){
        int n = 0; // the value system to determine a winner

        // to eliminate the problem when the num of columns is different from the num of rows
        int divs;
        if (rows == columns ){
            divs = 3;
        }
        else{
            divs = 2;
        }

        // horizontal check
        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < (columns / 2) + 3; j++) {
                if (Objects.equals(board[i][j], board[i][j + 1]) && Objects.equals(board[i][j + 1], player1)) {
                    n++;
                }
                else{
                    n = 0; // make sure that the program takes only 4 connections in a row
                }


                if(n>= 3){
                    return true;

                }
            }
            n = 0;

        }


        // vertical check
        for (int i = 0; i < columns ; i++) {

            // + 3 when rows = cols ; + 2 otherwise
            for (int j = 0; j < (rows / 2) + divs; j++) {
                if (Objects.equals(board[j][i], board[j + 1][i]) && Objects.equals(board[j][i], player1)) {
                    n++;
                }
                else{
                    n = 0; // make sure that the program takes only 4 connections in a row
                }

                if(n>= 3){
                    return true;

                }

            }

            n = 0;
        }


        // diagonal positive check / first half and the middle part
        for (int i = 3; i < rows ; i++) {
            for (int j = 1; j <= i ; j++) {
                if (Objects.equals(board[i-j + 1][j-1], board[i-j][j]) && Objects.equals(board[i-j + 1][j-1], player1)) {
                    n++;
                }
                else{
                    n = 0; // make sure that the program takes only 4 connections in a row
                }

                if(n>= 3){
                    return true;

                }

            }

            n = 0;
        }


        // diagonal positive check / second half
        for (int i = 1; i < columns - 3; i++) {
            for (int j = rows - 1; j > i; j--) {
                if (Objects.equals(board[j][i + columns - 1 - j], board[j-1][i + columns - j]) && Objects.equals(board[j][i + columns - 1 - j], player1)) {
                    n++;
                }
                else{
                    n = 0; // make sure that the program takes only 4 connections in a row
                }

                if(n>= 3){
                    return true;

                }
            }

            n = 0;
        }


        // diagonal negative check / first half
        for (int i = 3; i < columns - 1; i++) {
            for (int j = 0; j < i  ; j++) {
                if (Objects.equals(board[rows - 1 - j][i - j], board[rows - 2 - j][i - j - 1]) && Objects.equals(board[rows - 1 - j][i - j], player1)) {
                    n++;
                }
                else{
                    n = 0; // make sure that the program takes only 4 connections in a row
                }

                if(n>= 3){
                    return true;

                }

            }

            n = 0;
        }


        // diagonal negative check / second half
        for (int i = 3; i < rows ; i++) {
            for (int j = 0; j < i  ; j++) {
                if (Objects.equals(board[i - j][columns - 1 - j], board[i - j - 1][columns - 2 - j]) && Objects.equals(board[i - j][columns - 1 - j], player1)) {
                    n++;
                }
                else{
                    n = 0; // make sure that the program takes only 4 connections in a row
                }

                if(n>= 3){
                    return true;

                }

            }
            n = 0;
        }

        return false; // in case no connection of four

    }

    // check the board for player 2 win
    public static Boolean winPlayer2(){
        int n = 0; // the value system to determine a winner

        // to eliminate the problem when the num of columns is different from the num of rows
        int divs;
        if (rows == columns ){
            divs = 3;
        }
        else{
            divs = 2;
        }

        // horizontal check
        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < (columns / 2) + 3; j++) {
                if (Objects.equals(board[i][j], board[i][j + 1]) && Objects.equals(board[i][j + 1], player2)) {
                    n++;

                }
                else{
                    n = 0; // make sure that the program takes only 4 connections in a row

                }

                if(n>= 3){
                    return true;

                }
            }
            n = 0;

        }


        // vertical check
        for (int i = 0; i < columns ; i++) {

            // + 3 when rows = cols ; + 2 otherwise
            for (int j = 0; j < (rows / 2) + divs; j++) {
                if (Objects.equals(board[j][i], board[j + 1][i]) && Objects.equals(board[j][i], player2)) {
                    n++;
                }

                else{
                    n = 0; // make sure that the program takes only 4 connections in a row

                }

                if(n>= 3){
                    return true;

                }

            }

            n = 0;
        }


        // diagonal positive check / first half and the middle part
        for (int i = 3; i < rows ; i++) {
            for (int j = 1; j <= i ; j++) {
                if (Objects.equals(board[i-j + 1][j-1], board[i-j][j]) && Objects.equals(board[i-j + 1][j-1], player2)) {
                    n++;
                }
                else{
                    n = 0; // make sure that the program takes only 4 connections in a row

                }
                if(n>= 3){
                    return true;

                }

            }

            n = 0;
        }


        // diagonal positive check / second half
        for (int i = 1; i < columns - 3; i++) {
            for (int j = rows - 1; j > i; j--) {
                if (Objects.equals(board[j][i + columns - 1 - j], board[j-1][i + columns - j]) && Objects.equals(board[j][i + columns - 1 - j], player2)) {
                    n++;
                }

                else{
                    n = 0; // make sure that the program takes only 4 connections in a row

                }
                if(n>= 3){
                    return true;

                }

            }

            n = 0;
        }


        // diagonal negative check / first half
        for (int i = 3; i < columns - 1; i++) {
            for (int j = 0; j < i  ; j++) {
                if (Objects.equals(board[rows - 1 - j][i - j], board[rows - 2 - j][i - j - 1]) && Objects.equals(board[rows - 1 - j][i - j], player2)) {
                    n++;
                }

                else{
                    n = 0; // make sure that the program takes only 4 connections in a row

                }

                if(n>= 3){
                    return true;

                }

            }

            n = 0;

        }


        // diagonal negative check / second half
        for (int i = 3; i < rows ; i++) {
            for (int j = 0; j < i  ; j++) {
                if (Objects.equals(board[i - j][columns - 1 - j], board[i - j - 1][columns - 2 - j]) && Objects.equals(board[i - j][columns - 1 - j], player2)) {
                    n++;
                }

                else{
                    n = 0; // make sure that the program takes only 4 connections in a row

                }
                if(n>= 3){
                    return true;

                }
            }

            n = 0;

        }


        return false; // in case no connection of four

    }


    public static void gamePlay(){
        prg = true; // run the program

        while(prg){
            move(player1);
                boardPrint(rows, columns);
                System.out.println();

        // in case the player one wins the game doesn't continue
            if(prg){
                    move(player2);
                    System.out.println();
                    boardPrint(rows,columns);
                    System.out.println();

            }
        }

    }


}
