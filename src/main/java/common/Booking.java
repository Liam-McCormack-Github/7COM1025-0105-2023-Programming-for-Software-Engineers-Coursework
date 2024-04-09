package common;

import core.HatfieldJuniorSwimmingSchool;
import utils.UserInputValidator;
import utils.Utils;
import utils.validators.others.ValidateBooking;
import utils.validators.others.ValidateCancellation;

import java.util.ArrayList;
import java.util.UUID;

public class Booking {
    private final UUID id;
    private final ArrayList<Booking> dependantBookings;
    private Learner learner;
    private Lesson lesson;
    private boolean cancelled;

    // Constructor
    public Booking(HatfieldJuniorSwimmingSchool HJSS, Lesson lesson, Learner learner) {
        this.id = UUID.randomUUID();
        this.dependantBookings = new ArrayList<>();
        this.cancelled = false;
        this.setLessonAndLearner(lesson, learner);


        lesson.getBookings().add(this);
        lesson.getGrade().getBookings().add(this);

        learner.getBookings().add(this);

        HJSS.getBookings().add(this);
    }

    /*
     *   __  __ ______ _______ _    _  ____  _____   _____
     *  |  \/  |  ____|__   __| |  | |/ __ \|  __ \ / ____|
     *  | \  / | |__     | |  | |__| | |  | | |  | | (___
     *  | |\/| |  __|    | |  |  __  | |  | | |  | |\___ \
     *  | |  | | |____   | |  | |  | | |__| | |__| |____) |
     *  |_|  |_|______|  |_|  |_|  |_|\____/|_____/|_____/
     *
     * Methods
     */
    private void setLessonAndLearner(Lesson lesson, Learner learner) {
        UserInputValidator.ValidationResult<Booking> result = ValidateBooking.validate(lesson, learner);
        if (!result.isValid()) {
            throw new IllegalArgumentException(result.getErrorMessage());
        }

        this.lesson = lesson;
        this.learner = learner;
        Booking dependentBooking = result.getValue();

        if (dependentBooking != null) {
            this.dependantBookings.add(dependentBooking);
            if (dependentBooking.getDependantBookings() != null) {
                this.dependantBookings.addAll(dependentBooking.getDependantBookings());

            }
        }

        learner.getLessons().add(lesson);
        lesson.getLearners().add(learner);
    }

    public void Cancel() {
        UserInputValidator.ValidationResult<Lesson> result = ValidateCancellation.validate(this.lesson);
        if (!result.isValid()) {
            throw new IllegalArgumentException(result.getErrorMessage());
        }
        this.lesson.getLearners().remove(this.learner);
        this.learner.getLessons().remove(this.lesson);
        this.cancelled = true;

        for (Booking booking : this.learner.getBookings()) {
            if (booking.getLesson().getLessonNumber() > this.getLesson().getLessonNumber()) { // || !this.complete
                booking.getLesson().getLearners().remove(this.learner);
                booking.getLearner().getLessons().remove(this.lesson);
                booking.setCancelled(true);
                Utils.printOutputMessage(String.format("Cancelling dependent booking/s: (%s)", booking.getLesson().getLessonTitle()));
            }
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Booking{UUID=%s,\tLearner=%s,\tLesson=%s,\tCancelled=%s, \tBookings=[%d]}",
                this.id, this.learner, this.lesson, this.cancelled, this.dependantBookings.size()
        );
    }

    /*
     *   _____ ______ _______ _______ ______ _____   _____
     *  / ____|  ____|__   __|__   __|  ____|  __ \ / ____|
     * | |  __| |__     | |     | |  | |__  | |__) | (___
     * | | |_ |  __|    | |     | |  |  __| |  _  / \___ \
     * | |__| | |____   | |     | |  | |____| | \ \ ____) |
     *  \_____|______|  |_|     |_|  |______|_|  \_\_____/
     *
     * Getters
     */
    public UUID getId() {
        return this.id;
    }

    public Learner getLearner() {
        return this.learner;
    }

    public Lesson getLesson() {
        return this.lesson;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    /*
     *   _____ ______ _______ _______ ______ _____   _____
     *  / ____|  ____|__   __|__   __|  ____|  __ \ / ____|
     * | (___ | |__     | |     | |  | |__  | |__) | (___
     *  \___ \|  __|    | |     | |  |  __| |  _  / \___ \
     *  ____) | |____   | |     | |  | |____| | \ \ ____) |
     * |_____/|______|  |_|     |_|  |______|_|  \_\_____/
     *
     * Setters
     */
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public ArrayList<Booking> getDependantBookings() {
        return this.dependantBookings;
    }
}

