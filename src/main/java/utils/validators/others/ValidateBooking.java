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

        // Check if lesson has already occurred
        if (lesson.isFinished()) {
            return new Utils.ValidationResult<>(false, null, "Lesson cannot be booked in the past");
        }

        // Check if the lesson is not full
        if (lesson.getLearners().size() >= Globals.maxLessonSize) {
            return new Utils.ValidationResult<>(false, null, "Lesson is full");
        }

        // Check if the learner has any bookings
        if (learner.getBookings().isEmpty()) {

            // Learner has no previous bookings

            if (!(lesson.getGradeLevel() == learner.getGradeLevel() || lesson.getGradeLevel() == learner.getGradeLevel() + 1)) {
                return new Utils.ValidationResult<>(false, null, String.format("Lesson cannot be booked because this learner, grade %d, can only be booked to lessons at their grade or one higher. This lesson is grade %d", learner.getGradeLevel(), lesson.getGradeLevel()));
            }
        } else {

            // Learner has previous bookings

            // Check if the learner is already booked for the lesson
            for (Learner bookedLearner : lesson.getLearners()) {
                if (bookedLearner.getId().equals(learner.getId())) {
                    return new Utils.ValidationResult<>(false, null, "Learner is already booked to this lesson");
                }
            }

            // check if learner can book lesson


            // do basic check
            boolean learnerCanBookThisLesson = lesson.getGradeLevel() == learner.getGradeLevel() || lesson.getGradeLevel() == learner.getGradeLevel() + 1;

            // find if previous bookings would allow this booking to occur
            Grade thisLessonGrade = lesson.getGrade();

            if (!learnerCanBookThisLesson) {
                for (Booking booking : thisLessonGrade.getBookings()) {
                    if (booking.getLearner().getId() == learner.getId() && !booking.isCancelled() && booking.getLesson().getLessonNumber() < lesson.getLessonNumber()) {
                        learnerCanBookThisLesson = true;
                        dependantBooking = booking;
                        break;
                    }
                }
            }

            Grade thisLessonGradeBelow = Globals.gradesTree.lower(thisLessonGrade);
            if (!learnerCanBookThisLesson && thisLessonGradeBelow != null) {
                for (Booking booking : thisLessonGradeBelow.getBookings()) {
                    if (booking.getLearner().getId() == learner.getId() && !booking.isCancelled() && booking.getLesson().getLessonNumber() < lesson.getLessonNumber()) {
                        learnerCanBookThisLesson = true;
                        dependantBooking = booking;
                        break;
                    }
                }
            }


            if (!learnerCanBookThisLesson) {
                return new Utils.ValidationResult<>(false, null, String.format("Lesson cannot be booked because this learner, grade %d, can only be booked to lessons at their grade or one higher. This lesson is grade %d. Potential bookings can not be used, as they might not exist or they have been cancelled or happened after this lesson and cannot be used to justify booking this lesson", learner.getGradeLevel(), lesson.getGradeLevel()));
            }
        }

        return new Utils.ValidationResult<>(true, dependantBooking, null);
    }
}
