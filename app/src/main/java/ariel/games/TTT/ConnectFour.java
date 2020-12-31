package ariel.games.TTT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ConnectFour extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // since there isn't anything here yet, direct the user back
        super.onCreate(savedInstanceState);
        Intent goBack = getIntent();
        goBack.setClass(this.getApplicationContext(), MainActivity.class);
        startActivity(getIntent());
        // setContentView(R.layout.activity_connect_four);
    }
}