package utils.validators.others;

import common.Booking;
import common.Grade;
import common.Learner;
import common.Lesson;
import core.Globals;
import utils.Utils;


public class ValidateBooking {
    public static Utils.ValidationResult<Booking> validate(Lesson lesson, Learner learner) {
        Booking dependantBooking = null;

        if (lesson.isFinished()) {
            return new Utils.ValidationResult<>(false, null, "Lesson cannot be booked in the past");
        }

        if (lesson.getLearners().size() >= Globals.maxLessonSize) {
            return new Utils.ValidationResult<>(false, null, "Lesson is full");
        }
        if (!(lesson.getGradeLevel() == learner.getGradeLevel() || lesson.getGradeLevel() == learner.getGradeLevel() + 1)) {
            return new Utils.ValidationResult<>(false, null, String.format("Lesson cannot be booked because this learner, grade %d, can only be booked to lessons at their grade or one higher. This lesson is grade %d", learner.getGradeLevel(), lesson.getGradeLevel()));
        }

        // TODO DEPENDENT STUFF IS BROKEN


        return new Utils.ValidationResult<>(true, dependantBooking, null);
    }
}
