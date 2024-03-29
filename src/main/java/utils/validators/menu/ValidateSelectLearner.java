package utils.validators.menu;

import common.Learner;
import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import utils.Utils;

import java.util.ArrayList;

public class ValidateSelectLearner {

    public static ArrayList<String> menuOptions(HatfieldJuniorSwimmingSchool HJSS) {
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < HJSS.getLearners().size(); i++) {
            output.add(HJSS.getLearners().get(i).getName());
        }
        return output;
    }

    public static Utils.ValidationResult<Learner> menuResults(HatfieldJuniorSwimmingSchool HJSS, int userChoice) {

        if (userChoice == Globals.exitCode) {
            return new Utils.ValidationResult<>(true, null, "Returning To Main Menu");
        }

        if (userChoice > 0 && userChoice <= HJSS.getLearners().size()) {
            return new Utils.ValidationResult<>(true, HJSS.getLearnerByNumber(userChoice), null);
        }

        return new Utils.ValidationResult<>(false, null, String.format("Invalid Selection: '%s' is not a valid option", userChoice));
    }

}
