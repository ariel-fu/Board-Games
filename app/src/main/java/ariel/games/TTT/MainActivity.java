package ariel.games.TTT;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // if the player clicks on tic-tac-toe, we go to that activity
        Button tic_tac_toe = (Button)findViewById(R.id.tic_tac_toe);
        Log.d("null pointer", "Value of tic tac toe: " + tic_tac_toe);
        tic_tac_toe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("stop here>", "are we stopping here");
                Intent intent = new Intent(view.getContext(), TicTacToe.class);
                startActivity(intent);
            }
        });

    }

}