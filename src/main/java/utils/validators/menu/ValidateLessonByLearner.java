package utils.validators.menu;

import common.Learner;
import common.Lesson;
import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import utils.Utils;

import java.util.ArrayList;

public class ValidateLessonByLearner {

    public static ArrayList<String> menuOptions(HatfieldJuniorSwimmingSchool HJSS) {
        ArrayList<String> output = new ArrayList<>();


        for (Learner learner : HJSS.getLearners()) {

            String colorCode = learner.getLessons().isEmpty() ? Globals.ANSI_RED : Globals.ANSI_GREEN;
            String learnerDetails = String.format("%s%s%s", colorCode, learner.getName(), Globals.ANSI_RESET);

            output.add(learnerDetails);
        }
        return output;
    }

    public static Utils.ValidationResult<ArrayList<Lesson>> menuResults(HatfieldJuniorSwimmingSchool HJSS, Integer userChoice) {

        if (userChoice == Globals.exitCode) {
            return new Utils.ValidationResult<>(true, null, "Returning To Main Menu");
        }


        if (userChoice > 0 && userChoice <= HJSS.getLearners().size()) {
            ArrayList<Lesson> lessonsByLearner = HJSS.getLearnerByNumber(userChoice).getLessons();

            if (!lessonsByLearner.isEmpty()) {
                return new Utils.ValidationResult<>(true, lessonsByLearner, null);
            } else {
                return new Utils.ValidationResult<>(false, null, "That learner has not booked any lessons");
            }
        }
        return new Utils.ValidationResult<>(false, null, String.format("Invalid Selection: '%s' is not a valid option", userChoice));
    }

}
