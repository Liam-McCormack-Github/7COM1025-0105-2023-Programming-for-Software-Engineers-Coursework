package utils;

import core.Globals;

public class Utils {
    public static void printInputPrompt(String string) {
        System.out.printf("%s[INPUT] %s%s%n", Globals.ANSI_YELLOW, string, Globals.ANSI_RESET);
    }

    public static void printErrorMessage(String string) {
        System.out.printf("%s[ERROR] %s%s%n", Globals.ANSI_PURPLE, string, Globals.ANSI_RESET);
    }

    public static void printOutputMessage(String string) {
        System.out.printf("%s[OUTPUT] %s%s%n", Globals.ANSI_CYAN, string, Globals.ANSI_RESET);
    }

    public static void printSeparator(String ansiBlue) {
        System.out.printf("%s------------------------%s%n", ansiBlue, Globals.ANSI_RESET);
    }


    public static class ValidationResult<T> {

        // Helper class for validation results
        public final boolean isValid;
        public final T value;
        public final String errorMessage;

        public ValidationResult(boolean isValid, T value, String errorMessage) {
            this.isValid = isValid;
            this.value = value;
            this.errorMessage = errorMessage;
        }

        public boolean isValid() {
            return isValid;
        }

        public T getValue() {
            return value;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

    }
}
