package common;

import core.HatfieldJuniorSwimmingSchool;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

public class Timeslot {
    private final UUID id;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final Duration duration;
    private final Day day;
    private final ArrayList<Lesson> lessons;

    public Timeslot(HatfieldJuniorSwimmingSchool HJSS, Day day, String startTime, String endTime) {
        this.id = UUID.randomUUID();
        this.day = day;
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);

        this.duration = Duration.between(this.startTime, this.endTime);

        this.lessons = new ArrayList<>();

        day.getTimeslots().add(this);

        HJSS.getTimeslots().add(this);
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
                "Timeslot{UUID=%s,\tStart Time=%s,\tEnd Time=%s,\tDuration=%s,\tDay=%s,\tLessons=[%d]}",
                this.id, this.startTime, this.endTime, this.duration, this.day, this.lessons.size()
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

    public Day getDay() {
        return this.day;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public long getDurationMinutes() {
        return duration.toMinutes();
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
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


}
