package com.example.se2einzelphase;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
public class ServerCommunication implements Runnable{


    String host;
    int port;
    String message;
    String response;

    public ServerCommunication(String host, int port, String message) {
        this.host = host;
        this.port = port;
        this.message = message;
    }

    @Override
    public void run()  {

        try {
            Socket clientSocket;
            clientSocket = new Socket(host, port);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeBytes(message + '\n');

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            response = inFromServer.readLine();
            inFromServer.close();
            outToServer.close();
            clientSocket.close();

        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
        }

    }


}
