package utils.validators.others;

import common.Lesson;
import utils.Utils;

public class ValidateCancellation {
    public static Utils.ValidationResult<Lesson> validate(Lesson lesson) {
        // Check if lesson has already occurred
        if (lesson.isFinished()) {
            return new Utils.ValidationResult<>(false, null, "You can't cancel a lesson in the past");
        }

        return new Utils.ValidationResult<>(true, null, null);
    }
}
