package utils.validators.menu;

import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import utils.Utils;

import java.util.ArrayList;

public class ValidateSelectLessonsBy {


    public static ArrayList<String> menuOptions(HatfieldJuniorSwimmingSchool HJSS) {
        ArrayList<String> output = new ArrayList<>();
        output.add(Globals.menuNameSelectLessonByDay);
        output.add(Globals.menuNameSelectLessonByGrade);
        output.add(Globals.menuNameSelectLessonByCoach);
        output.add(Globals.menuNameSelectLessonByLearner);
        output.add(Globals.menuNameSelectLessonByLessonNumber);
        return output;
    }

    public static Utils.ValidationResult<Integer> menuResults(HatfieldJuniorSwimmingSchool HJSS, int userChoice) {

        if (userChoice == Globals.exitCode) {
            return new Utils.ValidationResult<>(true, null, "Returning To Main Menu");
        }


        if (userChoice > 0 && userChoice <= 5) {

            return new Utils.ValidationResult<>(true, userChoice, null);
        }

        return new Utils.ValidationResult<>(false, null, String.format("Invalid Selection: '%s' is not a valid option", userChoice));
    }
}
