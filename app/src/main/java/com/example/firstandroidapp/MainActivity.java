package com.example.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText name;
    Button continueButton;
    TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        continueButton = findViewById(R.id.continueButton);
        errorMessage = findViewById(R.id.errorMessage);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameString = name.getText().toString();
                errorMessage.setVisibility(View.INVISIBLE);

                if (nameString.isEmpty()) {
                    errorMessage.setVisibility(View.VISIBLE);
                    errorMessage.setText("You must insert a name !");
                    errorMessage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_error_24,0,0,0);
                } else {
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
                        errorMessage.setVisibility(View.VISIBLE);
                        errorMessage.setText("This field does not accept special characters!");
                        errorMessage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_error_24,0,0,0);
                    } else {
                        if (!nameString.equals("Andrei")) {
                            errorMessage.setVisibility(View.VISIBLE);
                            errorMessage.setText("Unknown User !");
                            errorMessage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_error_24,0,0,0);
                        }
                    }

                    if (nameString.equals("Andrei")) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}