package com.socket.server;

/**
 * Handles business logic of application.
 */
public class InputHandler {

    private static final int LoginUserName = 0;
    private static final int LoginPassword = 1;
    private static final int AuthenticateUser = 2;
    private static final int AuthSuccess = 3;
    private int state = LoginUserName;
    private String userName = null;
    private String userPassword = null;

    public String processInput(String clientRequest) {
        
        String reply = null;
        
        try {
            if (clientRequest != null && clientRequest.equalsIgnoreCase("login")) {
                state = LoginPassword;
            }
            if (clientRequest != null && clientRequest.equalsIgnoreCase("exit")) {
                return "exit";
            }
            if (clientRequest != null && clientRequest.equalsIgnoreCase("terminate")) {
                return "terminate";
            }
            if (state == LoginUserName) {
                reply = "Please Enter your user name: ";
                state = LoginPassword;
            } else if (state == LoginPassword) {
                userName = clientRequest;
                reply = "Please Enter your password: ";
                state = AuthenticateUser;
            } else if (state == AuthenticateUser) {
                userPassword = clientRequest;
                if (userName.equalsIgnoreCase("John") && userPassword.equals("doe")) {
                    reply = "Login Successful...";
                    state = AuthSuccess;
                } else {
                    reply = "Invalid Credentials!!! Please try again. Enter you user name: ";
                    state = LoginPassword;
                }
            } else {
                reply = "Invalid Request!!!";
            }
        } catch (Exception e) {
            System.out.println("input process failed: " + e.getMessage());
            return "exit";
        }
        return reply;
    }
}
