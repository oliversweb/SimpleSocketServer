package com.socket.server;

import java.net.*;
import java.io.*;

/**
 *
 * @author Administrator
 */
public class Listener extends Thread {

    final static int _portNumber = 9080; //Arbitrary port number

    public static void main(String[] args) {
        try {
            new Listener().startServer();
        } catch (Exception e) {
            System.out.println("I/O failure: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void startServer() throws Exception {
        
        ServerSocket serverSocket = null;
        
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(_portNumber);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + _portNumber);
            System.err.println("Reason: " + e.getMessage());
            System.exit(-1);
        }

        while (listening) {
            handleClientRequest(serverSocket);
        }

        if (serverSocket != null)
            serverSocket.close();
    }

    private void handleClientRequest(ServerSocket serverSocket) {
        try {
            new ConnectionRequestHandler(serverSocket.accept()).run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
