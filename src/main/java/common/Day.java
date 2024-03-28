package common;

import core.HatfieldJuniorSwimmingSchool;
import utils.UserInputValidator;
import utils.validators.others.ValidateDay;

import java.util.ArrayList;
import java.util.UUID;

public class Day {

    private final UUID id;
    private final int dayOfTheWeek;
    private final ArrayList<Timeslot> timeslots;
    private final ArrayList<Lesson> lessons;
    private String name;

    public Day(HatfieldJuniorSwimmingSchool HJSS, int dayOfTheWeek) {
        this.id = UUID.randomUUID();
        this.dayOfTheWeek = dayOfTheWeek;

        this.setDay(dayOfTheWeek);
        this.timeslots = new ArrayList<>();
        this.lessons = new ArrayList<>();


        HJSS.getDays().add(this);
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
    public void getLessonsOfTheDay() {
        for (Timeslot timeslot : this.timeslots) {
            this.lessons.addAll(timeslot.getLessons());
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Day{DayOfTheWeek=%s,\tName=%s,\tTimeslots=[%d],\tLessons=[%d]}",
                this.dayOfTheWeek, this.name, this.timeslots.size(), this.lessons.size()
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

    public int getDayOfTheWeek() {
        return this.dayOfTheWeek;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Timeslot> getTimeslots() {
        return this.timeslots;
    }

    public ArrayList<Lesson> getLessons() {
        return this.lessons;
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
    public void setDay(int value) {
        UserInputValidator.ValidationResult<String> result = ValidateDay.validate(String.valueOf(value));
        if (!result.isValid()) {
            throw new IllegalArgumentException(result.getErrorMessage());
        }
        this.name = result.getValue();
    }
}
