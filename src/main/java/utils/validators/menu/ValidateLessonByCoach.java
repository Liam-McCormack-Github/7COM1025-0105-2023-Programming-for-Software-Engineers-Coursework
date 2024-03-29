package utils.validators.menu;

import common.Coach;
import common.Lesson;
import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import utils.Utils;

import java.util.ArrayList;

public class ValidateLessonByCoach {
    public static ArrayList<String> menuOptions(HatfieldJuniorSwimmingSchool HJSS) {
        ArrayList<String> output = new ArrayList<>();

        for (Coach coach : HJSS.getCoaches()) {

            String colorCode = coach.getLessons().isEmpty() ? Globals.ANSI_RED : Globals.ANSI_GREEN;
            String coachDetails = String.format("%s%s%s", colorCode, coach.getName(), Globals.ANSI_RESET);

            output.add(coachDetails);
        }
        return output;
    }

    public static Utils.ValidationResult<ArrayList<Lesson>> menuResults(HatfieldJuniorSwimmingSchool HJSS, Integer userChoice) {

        if (userChoice == Globals.exitCode) {
            return new Utils.ValidationResult<>(true, null, "Returning To Main Menu");
        }

        if (userChoice > 0 && userChoice <= HJSS.getCoaches().size()) {
            return new Utils.ValidationResult<>(true, HJSS.getCoachByNumber(userChoice).getLessons(), null);
        }
        return new Utils.ValidationResult<>(false, null, String.format("Invalid Selection: '%s' is not a valid option", userChoice));
    }


}
