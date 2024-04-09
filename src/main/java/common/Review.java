package common;

import core.HatfieldJuniorSwimmingSchool;
import utils.UserInputValidator;
import utils.validators.input.ValidateComment;
import utils.validators.input.ValidateRating;

import java.util.UUID;

public class Review {
    private final UUID id;
    private final Learner learner;
    private final Lesson lesson;
    private int rating;
    private String comment;

    // Constructor
    public Review(HatfieldJuniorSwimmingSchool HJSS, int rating, String comment, Lesson lesson, Learner learner) {
        this.id = UUID.randomUUID();
        this.setRating(rating);
        this.setComment(comment);
        this.lesson = lesson;
        this.learner = learner;

        lesson.getCoach().getReviews().add(this);

        HJSS.getReviews().add(this);
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
    public String toString() {
        return String.format(
                "Review{UUID=%s,\tRating=%d,\tComment=%s,\tLearner=%s,\tLesson=%s}",
                this.id, this.rating, this.comment, this.learner, this.lesson
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

    public int getRating() {
        return this.rating;
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
    public void setRating(int value) {
        UserInputValidator.ValidationResult<Integer> result = ValidateRating.validate(String.valueOf(value));
        if (!result.isValid()) {
            throw new IllegalArgumentException(result.getErrorMessage());
        }
        this.rating = value;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String value) {
        UserInputValidator.ValidationResult<String> result = ValidateComment.validate(String.valueOf(value));
        if (!result.isValid()) {
            throw new IllegalArgumentException(result.getErrorMessage());
        }
        this.comment = value;
    }

    public Learner getLearner() {
        return this.learner;
    }

    public Lesson getLesson() {
        return this.lesson;
    }
}
