package com.example.firstandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText name;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        loginButton = findViewById(R.id.loginButton);

        String nameString = name.getText().toString();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameString = name.getText().toString();

                if (nameString.isEmpty()) {
                    name.setError("You must insert an username !");
                }

                ArrayList<Character> arrayOfAllowedChars = new ArrayList<>();
                String stringOfAllowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                for (int i = 0; i < stringOfAllowedChars.length(); i++) {
                    arrayOfAllowedChars.add(stringOfAllowedChars.charAt(i));
                }

                int countOfSpecialChars = 0;

                for (int i = 0; i < nameString.length(); i++) {
                    if (!arrayOfAllowedChars.contains(nameString.charAt(i))) {
                        countOfSpecialChars++;
                    }
                }

                if (countOfSpecialChars > 0) {
                    name.setError("This field does not accept special characters!");
                } else {
                    if (!nameString.equals("Andrei")) {
                        name.setError("Unknown User");
                    }
                }

                if(nameString.equals("Andrei")){
                    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                    startActivity(intent);
                }


            }
        });
    }
}