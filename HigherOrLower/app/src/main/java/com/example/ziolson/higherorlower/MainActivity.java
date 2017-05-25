package com.example.ziolson.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int randomNumber;

    public void checkGuess(View view) {
        EditText guessedNumber = (EditText)findViewById(R.id.guessedNumber);
        String guessedNumberString = guessedNumber.getText().toString();

        int guessedNumberInt = Integer.parseInt(guessedNumberString);

        String message = "";

        if(guessedNumberInt > randomNumber)
            message = "Za duża!";
        else if (guessedNumberInt < randomNumber)
            message = "Za mała!";
        else {
            message = "Gratuluję, zgadłeś! :)";
        }

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random randomGenerator = new Random();
        randomNumber = randomGenerator.nextInt(21);
    }
}
