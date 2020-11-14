package com.example.TTT;

/**
 * This class models a 3x3 tic-tac board
 *
 * @author  Ariel
 */
public class Board {
    private Button[][] board;   // a 2d array to model a board

    /**
     * No-arg constructor that inits the board to hold all empty Buttons
     */
    public Board(){
        board = new Button[3][3];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j ++){
                board[i][j] = new Button();
            }
        }
    }

    /**
     * Checks the board for a win
     * @return the Button type that won
     */
    public char isWin(){
        if(isWinHorizontal() != null){
            return isWinHorizontal().getType();
        } else if(isWinVertical() != null){
            return isWinVertical().getType();
        } else if(isWinDiagonal() != null){
            return isWinDiagonal().getType();
        } else {
            return '-'; // returns a dash for no win
        }
    }

    /**
     * Checks if there is a win in the horizontal direction
     * @return the Button that is the win
     */
    private Button isWinHorizontal(){
        for(int i = 0; i < board.length; i++){
            Button index1 = board[i][0];
            Button index2 = board[i][1];
            Button index3 = board[i][2];

            if(index1 == index2 && index2 == index3){
                return index1; // change to char? or button? to show which one is the winner.
            }
        }

        return null; // can't return char, maybe return a button and return null here?
    }

    /**
     * Checks if there is a win in the vertical direction
     * @return the Button that won
     */
    private Button isWinVertical(){
        for(int i =0; i < board.length; i++){
            Button index1 = board[0][i];
            Button index2 = board[1][i];
            Button index3 = board[2][i];

            if(index1 == index2 && index2 == index3){
                return index1;
            }
        }

        return null; // return null if there isn't a win
    }

    /**
     * Checks if there is a win in the diagonals
     * @return the Button that won
     */
    private Button isWinDiagonal(){

        // check the forward and backward diagonals

        Button index1 = board[0][0];
        Button index2 = board[1][1];
        Button index3 = board[2][2];
        if(index1 == index2 && index2 == index3){
            return index1; // TODO: fix like the above issue
        } else {
            index1 = board[0][2];
            index3 = board[2][0];
            if(index1 == index2 && index2 == index3){
                return index1; // TODO: fix like the above issue
            }
        }
        return null; // or return null
    }


}
