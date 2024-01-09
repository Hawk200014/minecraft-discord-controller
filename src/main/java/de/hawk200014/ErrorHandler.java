package de.hawk200014;

/**
 * Utility class for logging errors.
 */
public class ErrorHandler {

    /**
     * Logs an error message.
     *
     * @param message The error message to be logged.
     */
    public static void logError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Logs an error message along with details from the provided exception.
     *
     * @param message The error message to be logged.
     * @param e       The exception containing additional details.
     */
    public static void logError(String message, Exception e) {
        System.out.println("Error: " + message);
        if (e != null) {
            System.out.println("Exception Details: " + e.getMessage());
        }
    }
}