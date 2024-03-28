package core;

import common.*;

import java.util.ArrayList;
import java.util.NavigableSet;
import java.util.Scanner;
import java.util.TreeSet;

public class HatfieldJuniorSwimmingSchool {
    private final ArrayList<Day> days;
    private final ArrayList<Timeslot> timeslots;
    private final ArrayList<Coach> coaches;
    private final ArrayList<Learner> learners;
    private final ArrayList<Lesson> lessons;
    private final ArrayList<Review> reviews;
    private final ArrayList<Booking> bookings;
    private final ArrayList<Grade> grades;

    private int numberOfLessons;
    private int currentLessonNumber;
    private Learner selectedLearner;
    private Lesson selectedLesson;
    private ArrayList<Lesson> selectedLessons;
    private Scanner scanner;


    public HatfieldJuniorSwimmingSchool() {
        this.numberOfLessons = 0;
        this.currentLessonNumber = 0;

        this.days = new ArrayList<>();
        this.timeslots = new ArrayList<>();
        this.coaches = new ArrayList<>();
        this.learners = new ArrayList<>();
        this.lessons = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.grades = new ArrayList<>();

        this.selectedLearner = null;
        this.selectedLessons = new ArrayList<>();
        this.selectedLesson = null;
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
    public void resetState() {
        // Clear all collections
        this.days.clear();
        this.timeslots.clear();
        this.coaches.clear();
        this.learners.clear();
        this.lessons.clear();
        this.reviews.clear();
        this.bookings.clear();
        this.grades.clear();

        // Reset fields to their initial values
        this.numberOfLessons = 0;
        this.currentLessonNumber = 0;

        // Reset object references to null
        this.selectedLearner = null;
        this.selectedLesson = null;
        this.selectedLessons.clear();
    }

    public void preInit() {
        this.scanner = new Scanner(System.in);
    }

    public void init() {
    }

    public void run() {

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
    public Scanner getScanner() {
        return this.scanner;
    }

    public ArrayList<Day> getDays() {
        return this.days;
    }

    public ArrayList<Timeslot> getTimeslots() {
        return this.timeslots;
    }

    public ArrayList<Coach> getCoaches() {
        return this.coaches;
    }

    public ArrayList<Learner> getLearners() {
        return this.learners;
    }

    public ArrayList<Lesson> getLessons() {
        return this.lessons;
    }

    public ArrayList<Review> getReviews() {
        return this.reviews;
    }

    public ArrayList<Booking> getBookings() {
        return this.bookings;
    }

    public ArrayList<Grade> getGrades() {
        return this.grades;
    }

    public int getCurrentLessonNumber() {
        return this.currentLessonNumber;
    }

    public int getNumberOfLessons() {
        return this.numberOfLessons;
    }


    public Learner getSelectedLearner() {
        return this.selectedLearner;
    }
    public Lesson getSelectedLesson() {
        return this.selectedLesson;
    }
    public ArrayList<Lesson> getSelectedLessons() {
        return this.selectedLessons;
    }
    // Helpers
    public Learner getLearnerByNumber(int learnerNumber) {
        return this.learners.get(learnerNumber - 1);
    }

    public Lesson getLessonByNumber(int lessonNumber) {
        return this.lessons.get(lessonNumber - 1);
    }

    public Coach getCoachByNumber(int coachNumber) {
        return this.coaches.get(coachNumber - 1);
    }

    public Grade getGradeByNumber(int gradeNumber) {
        return this.grades.get(gradeNumber + 1);
    }

    public Day getDayByNumber(int dayNumber) {
        return this.days.get(dayNumber - 1);
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
    public void setNumberOfLessons(int numberOfLessons) {
        this.numberOfLessons = numberOfLessons;
    }
    public void setSelectedLearner(Learner selectedLearner) {
        this.selectedLearner = selectedLearner;
    }
    public void setSelectedLesson(Lesson selectedLesson) {
        this.selectedLesson = selectedLesson;
    }
    public void setSelectedLessons(ArrayList<Lesson> selectedLessons) {
        this.selectedLessons = selectedLessons;
    }
}
