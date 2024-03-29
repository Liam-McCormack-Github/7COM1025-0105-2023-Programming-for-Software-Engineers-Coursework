package utils.validators.menu;

import common.Grade;
import common.Lesson;
import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import utils.Utils;

import java.util.ArrayList;

public class ValidateLessonByGrade {

    public static ArrayList<String> menuOptions(HatfieldJuniorSwimmingSchool HJSS) {
        ArrayList<String> output = new ArrayList<>();


        if (!HJSS.getGrades().isEmpty()) {
            for (Grade grade : HJSS.getGrades()) {

                String colorCode = grade.getLessons().isEmpty() ? Globals.ANSI_RED : Globals.ANSI_GREEN;
                String gradeDetails = String.format("%sGrade %s%s", colorCode, grade.getLevel(), Globals.ANSI_RESET);

                output.add(gradeDetails);
            }
        }
        return output;
    }

    public static Utils.ValidationResult<ArrayList<Lesson>> menuResults(HatfieldJuniorSwimmingSchool HJSS, int userChoice) {

        if (userChoice == Globals.exitCode) {
            return new Utils.ValidationResult<>(true, null, "Returning To Main Menu");
        }

        if (userChoice > 0 && userChoice < HJSS.getGrades().size()) {
            return new Utils.ValidationResult<>(true, HJSS.getGradeByNumber(userChoice).getLessons(), null);
        }
        return new Utils.ValidationResult<>(false, null, String.format("Invalid Selection: '%s' is not a valid option", userChoice));
    }
}
