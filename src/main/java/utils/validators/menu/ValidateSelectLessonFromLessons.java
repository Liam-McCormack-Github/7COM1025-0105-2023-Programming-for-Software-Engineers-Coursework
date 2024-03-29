package utils.validators.menu;

import common.Lesson;
import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import utils.Utils;

import java.util.ArrayList;

public class ValidateSelectLessonFromLessons {

    public static ArrayList<String> menuOptions(HatfieldJuniorSwimmingSchool HJSS) {


        ArrayList<String> output = new ArrayList<>();
        for (Lesson lesson : HJSS.getSelectedLessons()) {
            String colorCode = (lesson.getLearners().size() == Globals.maxLessonSize || lesson.isFinished()) ? Globals.ANSI_RED : Globals.ANSI_GREEN;
            String lessonDetails = String.format("%s%s%s", colorCode, lesson.getLessonDetails(), Globals.ANSI_RESET);

            output.add(lessonDetails);
        }
        return output;
    }

    public static Utils.ValidationResult<Lesson> menuResults(HatfieldJuniorSwimmingSchool HJSS, Integer userChoice) {

        if (userChoice == Globals.exitCode) {
            return new Utils.ValidationResult<>(true, null, "Returning To Main Menu");
        }

        if (userChoice > 0 && userChoice <= HJSS.getSelectedLessons().size()) {

            Lesson lesson = HJSS.getSelectedLessons().get(userChoice - 1);

            return new Utils.ValidationResult<>(true, lesson, null);
        }

        return new Utils.ValidationResult<>(false, null, String.format("Invalid Selection: '%s' is not a valid option", userChoice));
    }
}
