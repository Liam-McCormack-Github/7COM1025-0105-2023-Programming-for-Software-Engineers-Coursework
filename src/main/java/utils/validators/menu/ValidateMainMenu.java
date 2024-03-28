package utils.validators.menu;

import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import utils.Utils;

import java.util.ArrayList;

public class ValidateMainMenu {
    public static ArrayList<String> menuOptions(HatfieldJuniorSwimmingSchool HJSS) {
        ArrayList<String> output = new ArrayList<>();
        output.add(Globals.createLearner);
        output.add(Globals.selectLearner);
        output.add(Globals.selectLesson);
        output.add(Globals.bookSelectedLesson);
        output.add(Globals.cancelNextLesson);
        output.add(Globals.simulateUntilNextLesson);
        return output;
    }

    public static Utils.ValidationResult<Integer> menuResults(HatfieldJuniorSwimmingSchool HJSS, int userChoice) {

        if (userChoice == Globals.exitCode) {
            return new Utils.ValidationResult<>(true, userChoice, "Returning To Main Menu");
        }

        if (userChoice == 1) {
            return new Utils.ValidationResult<>(true, userChoice, null);
        }

        String errorMessage = String.format("Invalid Selection: '%s' is not a valid option", userChoice);

        return new Utils.ValidationResult<>(false, null, errorMessage);
    }
}
