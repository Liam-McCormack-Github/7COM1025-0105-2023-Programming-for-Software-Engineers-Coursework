package common;

import core.HatfieldJuniorSwimmingSchool;
import utils.UserInputValidator;
import utils.validators.input.ValidateName;

import java.util.ArrayList;
import java.util.UUID;

public class Coach {
    private final UUID id;
    private final ArrayList<Lesson> lessons;
    private final ArrayList<Review> reviews;
    private String name;


    public Coach(HatfieldJuniorSwimmingSchool HJSS, String name) {
        this.id = UUID.randomUUID();
        this.setName(name);
        this.lessons = new ArrayList<>();
        this.reviews = new ArrayList<>();

        HJSS.getCoaches().add(this);
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
                "Coach{UUID=%s,\tName=%s,\tLessons=[%d],\tReviews=[%d]}",
                this.id, this.name, this.lessons.size(), this.reviews.size()
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

    public String getName() {
        return this.name;
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
    public void setName(String value) {
        UserInputValidator.ValidationResult<String> result = ValidateName.validate(String.valueOf(value));
        if (!result.isValid()) {
            throw new IllegalArgumentException(result.getErrorMessage());
        }
        this.name = result.getValue();
    }

    public ArrayList<Lesson> getLessons() {
        return this.lessons;
    }

    public ArrayList<Review> getReviews() {
        return this.reviews;
    }
}
