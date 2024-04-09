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

        if (userChoice == 1 ||
                userChoice == 888 || // TODO remove
                userChoice == 2 ||
                userChoice == 3
        ) {
            return new Utils.ValidationResult<>(true, userChoice, null);
        }

        String errorMessage = String.format("Invalid Selection: '%s' is not a valid option", userChoice);

        if (userChoice == 4) {
            if (HJSS.getSelectedLearner() != null && HJSS.getSelectedLesson() != null) {
                return new Utils.ValidationResult<>(true, userChoice, null);
            } else {
                errorMessage = "You need to select a user and a lesson before booking";
            }
        }

        if (userChoice == 5) {
            if (!HJSS.getBookings().isEmpty() && HJSS.getSelectedLesson() != null && HJSS.getSelectedLearner() != null) {
                return new Utils.ValidationResult<>(true, userChoice, null);
            } else {
                errorMessage = "You can't cancel lessons if you haven't booked any";
            }
        }

        return new Utils.ValidationResult<>(false, null, errorMessage);
    }
}
