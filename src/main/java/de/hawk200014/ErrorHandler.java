package de.hawk200014;

public class ErrorHandler {

    public static void logError(String message){
        System.out.println(message);
    }

    public static void logError(String message, Exception e){
        System.out.println(message + "\n" + e.getMessage());
    }

}
