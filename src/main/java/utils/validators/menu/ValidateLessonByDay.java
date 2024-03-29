package utils.validators.menu;

import common.Day;
import common.Lesson;
import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import utils.Utils;

import java.util.ArrayList;

public class ValidateLessonByDay {


    public static ArrayList<String> menuOptions(HatfieldJuniorSwimmingSchool HJSS) {
        ArrayList<String> output = new ArrayList<>();
        for (Day day : HJSS.getDays()) {
            String lessonColor = day.getLessons().isEmpty() ? Globals.ANSI_RED : Globals.ANSI_GREEN;
            String lessonOptions = String.format("%sDay %s%s", lessonColor, day.getName(), Globals.ANSI_RESET);

            output.add(lessonOptions);
        }
        return output;
    }

    public static Utils.ValidationResult<ArrayList<Lesson>> menuResults(HatfieldJuniorSwimmingSchool HJSS, Integer userChoice) {

        if (userChoice == Globals.exitCode) {
            return new Utils.ValidationResult<>(true, null, "Returning To Main Menu");
        }

        if (!(userChoice > 0 && userChoice <= HJSS.getDays().size())) {
            return new Utils.ValidationResult<>(false, null, String.format("Invalid Selection: '%s' is not a valid option", userChoice));
        }
        Day selectedDay = HJSS.getDayByNumber(userChoice);
        if (selectedDay.getLessons().isEmpty()) {
            return new Utils.ValidationResult<>(false, null, "No lessons for that day");
        }
        return new Utils.ValidationResult<>(true, selectedDay.getLessons(), null);
    }

}
