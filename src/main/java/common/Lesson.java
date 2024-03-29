package common;

import core.HatfieldJuniorSwimmingSchool;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

public class Lesson {
    private final UUID id;
    private final int week;
    private final int lessonNumberWeek;
    private final Day day;
    private final Timeslot timeslot;
    private final ArrayList<Learner> learners;
    private final ArrayList<Booking> bookings;
    private int lessonNumber;
    private Grade grade;
    private Coach coach;
    private boolean finished;

    public Lesson(HatfieldJuniorSwimmingSchool HJSS, Grade grade, Coach coach) {
        this.id = UUID.randomUUID();
        this.setLessonNumber(HJSS);
        this.grade = grade;
        this.coach = coach;
        this.learners = new ArrayList<>();
        this.bookings = new ArrayList<>();

        ArrayList<Timeslot> timeslots = HJSS.getTimeslots();

        int weeklyTimeslots = timeslots.size();
        int remainder = (this.lessonNumber - 1) % weeklyTimeslots;
        this.week = ((this.lessonNumber - 1) / weeklyTimeslots) + 1;
        this.lessonNumberWeek = remainder + 1;
        this.timeslot = timeslots.get(remainder);

        this.day = this.timeslot.getDay();
        this.finished = false;

        // TODO add to timeslots, coach, grade

        HJSS.getLessons().add(this);
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

    // Lesson details without learners
    public String getLessonTitle() {
        return String.format("Lesson: #%d\t(w%d\tl%d) \tGrade: %d \tCoach: %s \tTime: %s (%s->%s)", this.lessonNumber, this.week, this.lessonNumberWeek, this.getGradeLevel(), this.getCoach().getName(), this.day.getName(), this.getTimeslot().getStartTime(), this.getTimeslot().getEndTime());
    }

    // Full Lesson details with learners
    public String getLessonDetails() {
        return String.format("Lesson: #%d\t(w%d l%d) \tGrade: %d \tCoach: %s \tTime: %s (%s->%s) \tLearners: [%s]", this.lessonNumber, this.week, this.lessonNumberWeek, this.getGradeLevel(), this.getCoach().getName(), this.day.getName(), this.getTimeslot().getStartTime(), this.getTimeslot().getEndTime(), this.getLearnerNames());
    }

    // Method to get the names of all learners in the classroom as a single String
    public String getLearnerNames() {
        return learners.stream().map(Learner::getInfo).collect(Collectors.joining(", ")); // Map each Learner to their name. Join names with a comma and a space
    }


    @Override
    public String toString() {
        return String.format("Lesson{UUID=%s,\tLesson Number=%d,\tWeek=%d,\tLesson Number off the Week=%d,\tGrade=%s,\tCoach=%s,\tDay=%s,\tTimeslot=%s,\tLearners=[%d],\tBookings=[%d],\tFinished=%s}", this.id, this.lessonNumber, this.week, this.lessonNumberWeek, this.grade, this.coach, this.day, this.timeslot, this.learners.size(), this.bookings.size(), this.finished);
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

    public int getLessonNumber() {
        return this.lessonNumber;
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
    private void setLessonNumber(HatfieldJuniorSwimmingSchool HJSS) {
        int lessonNum = HJSS.getNumberOfLessons();
        lessonNum++;
        this.lessonNumber = lessonNum;
        HJSS.setNumberOfLessons(lessonNum);
    }

    public Grade getGrade() {
        return this.grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public int getGradeLevel() {
        return this.grade.getLevel();
    }

    public Coach getCoach() {
        return this.coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public int getWeek() {
        return this.week;
    }

    public int getLessonNumberWeek() {
        return this.lessonNumberWeek;
    }

    public Day getDay() {
        return this.day;
    }

    public Timeslot getTimeslot() {
        return this.timeslot;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public ArrayList<Learner> getLearners() {
        return this.learners;
    }

    public ArrayList<Booking> getBookings() {
        return this.bookings;
    }

}
