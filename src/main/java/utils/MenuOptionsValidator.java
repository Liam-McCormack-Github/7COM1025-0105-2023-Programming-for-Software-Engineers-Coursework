package utils;


import core.Globals;
import core.HatfieldJuniorSwimmingSchool;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MenuOptionsValidator extends Utils {


    public static void printMenuOptions(String menuName, Function<HatfieldJuniorSwimmingSchool, ArrayList<String>> menuOptionsFunction, HatfieldJuniorSwimmingSchool HJSS) {
        ArrayList<String> menuOptions = menuOptionsFunction.apply(HJSS);
        Utils.printSeparator(Globals.ANSI_RED);
        System.out.println(menuName);
        Utils.printSeparator(Globals.ANSI_RED);
        for (int i = 0; i < menuOptions.size(); i++) {
            System.out.printf("%s%3d%s: %s%n", Globals.ANSI_CYAN, (i + 1), Globals.ANSI_RESET, menuOptions.get(i));
        }
        System.out.printf("%s%3d%s: Main Menu / Quit%n", Globals.ANSI_RED, Globals.exitCode, Globals.ANSI_RESET);
        Utils.printSeparator(Globals.ANSI_BLUE);
        Utils.printInputPrompt("Select an option");

    }


    public static <T> T userInputMenu(String menuName,
                                      Function<HatfieldJuniorSwimmingSchool, ArrayList<String>> menuOptionsFunction,
                                      BiFunction<HatfieldJuniorSwimmingSchool, Integer, ValidationResult<T>> filterFunction,
                                      HatfieldJuniorSwimmingSchool HJSS) {

        boolean continueLoop = true;
        T selectedOutput = null;

        while (continueLoop) {

            printMenuOptions(menuName, menuOptionsFunction, HJSS);

            String userInput = HJSS.getScanner().nextLine();
            Utils.printSeparator(Globals.ANSI_BLUE);

            try {
                int userChoice = Integer.parseInt(userInput);

                ValidationResult<T> result = filterFunction.apply(HJSS, userChoice);

                if (result.isValid()) {
                    if (result.getErrorMessage() != null) {
                        Utils.printOutputMessage(result.getErrorMessage());
                    }
                    selectedOutput = result.getValue();
                    continueLoop = false;
                } else {
                    Utils.printErrorMessage(result.getErrorMessage());
                }

            } catch (IllegalArgumentException e) {
                Utils.printErrorMessage(String.format("Invalid Selection: '%s' should be an integer", userInput));
            }
        }
        return selectedOutput;
    }


}
