package ariel.games.TTT;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TicTacToe extends AppCompatActivity implements View.OnClickListener {
    private Button[][] board;   // change to our own Board implementation

    private boolean player1Turn;
    private int player1Score;
    private int player2Score;
    private int numMoves;
    private TextView player1Text;
    private TextView player2Text;
    private boolean isWinner ;
    private String EMPTY = " - ";



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set player1turn to true (player1 starts first)
        player1Turn = true;
        isWinner = false;
        // set default values to score and move trackers.
        player1Score = 0;
        player2Score = 0;
        numMoves = 0;



        // init 3-d array into series of empty buttons
        board = new Button[3][3];
        // set the activity
        setContentView(R.layout.activity_tic_tac_toe);

        player1Text = findViewById(R.id.player1Text);
        player2Text = findViewById(R.id.player2Text);

        for(int i = 0; i<3; i++){
            for(int j = 0; j < 3; j++){

                String buttonID = "button_" + i + j;
                int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());

                Button currButton = findViewById(resourceID);
                currButton.setOnClickListener((View.OnClickListener) this);
                currButton.setText(EMPTY);
                board[i][j]=currButton; // set curr square in board to the button


            }
        }

        Button reset = findViewById(R.id.button_reset);
        Button hardReset = findViewById(R.id.hardReset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        hardReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                reset();
                player1Text.setText(R.string.player1);
                player2Text.setText(R.string.player2);
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
        numMoves = 0;
        isWinner = false;

        // init 3-d array into series of empty buttons

        for(int i =0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j].setText(EMPTY);
            }
        }
    }


    public void onClick(View v){
        Button currButton = (Button) v;
        if(currButton == null){
            return;
        }
        String buttonText = currButton.getText().toString();
        if(!isWinner) {
            // invalid move, do nothing
            if (!buttonText.equals(EMPTY)) {
                return;
            } else if (player1Turn) {
                currButton.setText("X"); // player 1 is x
            } else {
                currButton.setText("0"); // player 2 is y
            }
            numMoves++; // keep track of moves to see if there is a tie (ie all of the board is filled without a win)

            if (isWin()) {
                if (player1Turn) {
                    isWinner = true;
                    player1Score++;
                    String player1 = getString(R.string.player1Text) + player1Score;
                    player1Text.setText(player1);
                    showToast("Player 1 wins!");
                    isWinner = true;
                } else {
                    isWinner = true;
                    player2Score++;
                    String player2 = getString(R.string.player2Text) + player2Score;
                    player2Text.setText(player2);
                    showToast("Player 2 wins!");

                }
            } else if (numMoves == 9) {
                // there is a tie
                isWinner = true;
                showToast("Tie, no one won T~T");
            } else {
                // set the player1 turn to NOT what it currently is.
                // if player1 just went, it is player2's turn and vice versa
                player1Turn = !player1Turn;
                return;
            }
        }

        showToast("Press RESET BOARD to play again");


    }

    public void showToast(String text) {
        // Set the toast and duration

        int toastDurationInMilliSeconds = 1000;
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);

        // Set the countdown to display the toast
        CountDownTimer toastCountDown;
        toastCountDown = new CountDownTimer(toastDurationInMilliSeconds, 20 /*Tick duration*/) {
            public void onTick(long millisUntilFinished) {
                toast.show();
            }
            public void onFinish() {
                toast.cancel();
            }
        };

        // Show the toast and starts the countdown
        toast.show();
        toastCountDown.start();
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

            if(checkEqual(index1, index2, index3)){
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

            if(checkEqual(index1, index2, index3)){
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
        if(checkEqual(index1, index2, index3)){
            return true; // "forward" diagonal win
        } else {
            index1 = board[0][2];

            index3 = board[2][0];
            if(checkEqual(index1, index2, index3)){
                return true; // "backward" diagonal win
            }
        }
        return false; // no diagonal wins
    }

    private boolean checkEqual(Button one, Button two, Button three){
        String index1 = one.getText().toString();
        String index2 = two.getText().toString();
        String index3 = three.getText().toString();
        if(!index1.equals(EMPTY) &&!index2.equals(EMPTY) &&!index3.equals(EMPTY) ) {
            if (index1.equals(index2) && index2.equals(index3)) {
                return true;
            }
        }
        return false;
    }


}