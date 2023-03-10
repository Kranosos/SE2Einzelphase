package com.example.se2einzelphase;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMatrikelnummer(View v) throws InterruptedException {

        TextInputLayout matrikelnummerFeld = findViewById(R.id.number_input);

        String matrikelnummer = String.valueOf(matrikelnummerFeld.getEditText().getText());
        ServerCommunication serverCommunication = new ServerCommunication("143.205.174.165", 53212, matrikelnummer);
        Thread thread = new Thread(serverCommunication);    //neuer Thread, da Serverkommunikation nicht auf Haupt-Thread stattfinden soll/kann
        thread.start();
        thread.join();
        updateOutput(serverCommunication.response);
    }

    public void calculate(View v) {
        TextInputLayout matrikelnummerFeld = findViewById(R.id.number_input);
        String matrikelnummer = String.valueOf(matrikelnummerFeld.getEditText().getText());
        ArrayList<Character> chars = new ArrayList<>();

        //Hinzufügen aller nicht-Primzahlen zum Array
        for (char ch : matrikelnummer.toCharArray()) {
            if(!isPrime(Character.getNumericValue(ch))) {
                chars.add(ch);
            }
        }
        Collections.sort(chars);

        StringBuilder builder = new StringBuilder();
        for (Character value : chars) {
            builder.append(value);
        }

        updateOutput(builder.toString());
    }

    public void updateOutput(String textToDisplay) {
        TextView textView = findViewById(R.id.output);
        textView.setSingleLine(false);
        textView.setText(textToDisplay);

    }

    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}