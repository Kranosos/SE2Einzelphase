package com.example.se2einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sendMatrikelnummer(View v) throws InterruptedException {
        TextView matrikelnummerFeld = (TextView) findViewById(R.id.number_input);

        String matrikelnummer = String.valueOf(matrikelnummerFeld.getText());
        ServerCommunication serverCommunication = new ServerCommunication("143.205.174.165", 53212, matrikelnummer);
        Thread thread = new Thread(serverCommunication);
        thread.start();
        thread.join();


        updateOutput(serverCommunication.response);

    }

    public void updateOutput(String matrikelnummer) {
        TextView textView = findViewById(R.id.output);
        textView.setSingleLine(false);
        textView.setText(matrikelnummer);

    }
}