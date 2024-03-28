package common;

import core.HatfieldJuniorSwimmingSchool;
import utils.UserInputValidator;
import utils.validators.input.ValidateGrade;

import java.util.ArrayList;
import java.util.UUID;


public class Grade implements Comparable<Grade> {
    private final UUID id;
    private final ArrayList<Lesson> lessons;
    private final ArrayList<Booking> bookings;
    private int level;
    private String comment;

    public Grade(HatfieldJuniorSwimmingSchool HJSS, int level) {
        this.id = UUID.randomUUID();
        this.setLevel(level);

        this.lessons = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.comment = null;

        HJSS.getGrades().add(this);
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
    @Override
    public int compareTo(Grade other) {
        return Integer.compare(this.level, other.level);
    }

    @Override
    public String toString() {
        return String.format(
                "Grade{UUID=%s,\tLevel=%d,\tComment=%s,\tLessons=[%d],\tBookings=[%d]}",
                this.id, this.level, this.comment, this.lessons.size(), this.bookings.size()
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

    public int getLevel() {
        return this.level;
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
    public void setLevel(int value) {
        UserInputValidator.ValidationResult<Integer> result = ValidateGrade.validate(String.valueOf(value));
        if (!result.isValid()) {
            throw new IllegalArgumentException(result.getErrorMessage());
        }
        this.level = result.getValue();
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String value) {
        this.comment = value;
    }

    public ArrayList<Lesson> getLessons() {
        return this.lessons;
    }

    public ArrayList<Booking> getBookings() {
        return this.bookings;
    }
}
