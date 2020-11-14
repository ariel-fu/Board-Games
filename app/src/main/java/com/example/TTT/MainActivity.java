package com.example.TTT;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button[][] board;   // change to our own Board implementation

    private boolean player1Turn;
    private int player1Score;
    private int player2Score;
    private int numMoves;
    private TextView player1Text;
    private TextView player2Text;



    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

        // set player1turn to true (player1 starts first)
        player1Turn = true;
        // set default values to score and move trackers.
        player1Score = 0;
        player2Score = 0;
        numMoves = 0;

        // init 3-d array into series of empty buttons
        board = new Button[3][3];
            // set the activity
        setContentView(R.layout.activity_main_activity);

        for(int i = 0; i< board.length; i++){
            for(int j = 0; j < board[i].length; i++){
                String buttonID = "button_" + i + j;
                int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
                Button currButton = board[i][j];
                currButton = findViewById(resourceID);
                // TODO: currButton is null here?
                currButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        }

        Button reset = findViewById(R.id.button_reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    /**
     * Resets all values of the board to their default values
     */
    private void reset(){
        // set player1turn to true (player1 starts first)
        player1Turn = true;
        // set default values to score and move trackers.
        player1Score = 0;
        player2Score = 0;
        numMoves = 0;

        // init 3-d array into series of empty buttons

        for(int i =0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j].setText(" - ");
            }
        }
    }


    public void onClick(View v){
        Button currButton = (Button) v;
        if(currButton == null){
            return;
        }
        String buttonText = currButton.getText().toString();
        // invalid move, do nothing
        if(!buttonText.equals("")){
            return;
        } else if(player1Turn){
            currButton.setText("X"); // player 1 is x
        } else{
            currButton.setText("0"); // player 2 is y
        }
        numMoves++; // keep track of moves to see if there is a tie (ie all of the board is filled without a win)

        if(isWin()){
            if(player1Turn){
                player1Score++;
                player1Text.setText("Player 1: " + player1Score);
                player2Text.setText("Player 2: " + player2Score);
                Toast.makeText(this, "Player 1 wins! Player 2 takes Ls", Toast.LENGTH_LONG).show();
                reset();
            } else {
                player2Score++;
                player1Text.setText("Player 1: " + player1Score);
                player2Text.setText("Player 2: " + player2Score);
                Toast.makeText(this, "Player 2 wins! Player 1 takes Ls", Toast.LENGTH_LONG).show();
                reset();
            }
        } else if(numMoves == 9){
            // there is a tie
            tie();
        } else{
            // set the player1 turn to NOT what it currently is.
            // if player1 just went, it is player2's turn and vice versa
            player1Turn = !player1Turn;
        }


    }

    /**
     * Checks if there is a win either in the horizontal, diagonal, or vertical direction
     * @return true if there is
     */
    private boolean isWin(){
        return (isWinHorizontal() || isWinDiagonal() || isWinVertical());
    }



    /**
     * Checks if there is a win in the horizontal direction
     * @return the Button that is the win
     */
    private boolean isWinHorizontal(){
        for(int i = 0; i < board.length; i++){
            Button index1 = board[i][0];
            Button index2 = board[i][1];
            Button index3 = board[i][2];

            if(index1 == index2 && index2 == index3){
                return true; // there is a win in the horizontal direction
            }
        }

        return false; // no wins here
    }

    /**
     * Checks if there is a win in the vertical direction
     * @return the Button that won
     */
    private boolean isWinVertical(){
        for(int i =0; i < board.length; i++){
            Button index1 = board[0][i];
            Button index2 = board[1][i];
            Button index3 = board[2][i];

            if(index1 == index2 && index2 == index3){
                return true;
            }
        }

        return false; // return null if there isn't a win
    }

    /**
     * Checks if there is a win in the diagonals
     * @return the Button that won
     */
    private boolean isWinDiagonal(){

        // check the forward and backward diagonals

        Button index1 = board[0][0];
        Button index2 = board[1][1];
        Button index3 = board[2][2];
        if(index1 == index2 && index2 == index3){
            return true; // "forward" diagonal win
        } else {
            index1 = board[0][2];
            index3 = board[2][0];
            if(index1 == index2 && index2 == index3){
                return true; // "backward" diagonal win
            }
        }
        return false; // no diagonal wins
    }


    /**
     * In the case that there is a tie, pop a toast and reset the game
     *
     */
    private void tie(){
        Toast.makeText(this, "There was a tie, everyone takes L's", Toast.LENGTH_LONG).show();
        reset();
    }
}