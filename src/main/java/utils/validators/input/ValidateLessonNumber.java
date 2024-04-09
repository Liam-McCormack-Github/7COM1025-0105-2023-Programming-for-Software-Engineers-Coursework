package utils.validators.input;

import common.Lesson;
import core.HatfieldJuniorSwimmingSchool;
import utils.Utils;

public class ValidateLessonNumber {
    public static Utils.ValidationResult<Lesson> validate(HatfieldJuniorSwimmingSchool HJSS, String input) {
        try {
            int lessonNumber = Integer.parseInt(input);
            if (1 <= lessonNumber && lessonNumber <= HJSS.getNumberOfLessons()) {
                return new Utils.ValidationResult<>(true, HJSS.getLessonByNumber(lessonNumber), null);
            }
        } catch (NumberFormatException ignored) {
        }
        return new Utils.ValidationResult<>(false, null, String.format("Invalid Input: '%s' should be an integer between 1 and %s", input, HJSS.getNumberOfLessons()));
    }
}
