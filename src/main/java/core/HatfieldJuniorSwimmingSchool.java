package core;

import common.*;
import utils.MenuOptionsValidator;
import utils.UserInputValidator;
import utils.Utils;
import utils.validators.input.*;
import utils.validators.menu.*;

import java.util.ArrayList;
import java.util.Scanner;

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
        this.seedDays();
        this.seedGrades();

        this.scanner = new Scanner(System.in);
    }

    private void seedDays() {
        for (int i = 1; i <= Globals.daysOfTheWeek.length; i++) {
            new Day(this, i);
        }
    }

    private void seedGrades() {
        for (int i = Globals.minGrade; i <= Globals.maxGrade; i++) {
            new Grade(this, i);
        }
    }

    public void init() {
        Seeder.seedTimeslots(this);
        Seeder.seedCoaches(this);
        Seeder.seedLearners(this);
        Seeder.seedLessons(this);
        Seeder.seedBookings(this);

        this.getLessonsForEachDay();
    }

    public void getLessonsForEachDay() {
        for (Day day : this.days) {
            day.getLessonsOfTheDay();
        }
    }

    public void run() {
        mainMenu();
        // TODO
    }


    private void mainMenu() {

        int selectedOptionFromMainMenu = MenuOptionsValidator.userInputMenu(Globals.menuNameMainMenu, ValidateMainMenu::menuOptions, ValidateMainMenu::menuResults, this);

        switch (selectedOptionFromMainMenu) {
            case 1:
                createLearner();
                System.out.println(this.selectedLearner);
                break;
            case 2:
                selectLearner();
                break;
            case 3:
                selectLesson();
                break;
                /*
            case 4:
                createBooking();
                break;
            case 5:
                cancelBooking();
                break;
            case 6:
                simulateLessons();
                break;
                test case
                */
            case 888:
                Learner debugging = new Learner(this, "human1", "other", 11, "human1@mail.com", "1234567898", this.getGradeByNumber(1));
                this.setSelectedLearner(debugging);
                break;
            default:
                break;
        }

        if (selectedOptionFromMainMenu == Globals.exitCode) {
            Utils.printOutputMessage("Application is terminating");
            // System.exit(0);
        } else {
            mainMenu();
        }
    }

    private void createLearner() {
        Learner learner = userInputNewLearner();
        Utils.printOutputMessage(String.format("Created new learner (%s)", learner.getInfo()));
        this.setSelectedLearner(learner);
        Utils.printOutputMessage(String.format("Selected learner (%s)", learner.getInfo()));
    }

    private Learner userInputNewLearner() {
        String name = UserInputValidator.validateInput("Enter learner's name: ", ValidateName::validate, this);
        String gender = UserInputValidator.validateInput("Enter learner's gender: ", ValidateGender::validate, this);
        int age = UserInputValidator.validateInput("Enter learner's age: ", ValidateAge::validate, this);
        String contact = UserInputValidator.validateInput("Enter emergency contact name: ", ValidateContact::validate, this);
        String number = UserInputValidator.validateInput("Enter phone number: ", ValidatePhoneNumber::validate, this);
        int grade = UserInputValidator.validateInput("Enter current grade level: ", ValidateGrade::validate, this);

        return new Learner(this, name, gender, age, contact, number, this.getGradeByNumber(grade));
    }

    private void selectLearner() {
        Learner learner = selectLearnerMenu();
        this.setSelectedLearner(learner);
    }

    private Learner selectLearnerMenu() {
        return MenuOptionsValidator.userInputMenu(Globals.menuNameSelectLearner, ValidateSelectLearner::menuOptions, ValidateSelectLearner::menuResults, this);
    }

    private void selectLesson() {
        Lesson lesson = selectLessonsMenu();
        this.setSelectedLesson(lesson);
    }


    private Lesson selectLessonsMenu() {
        int selectLessonsMenu;
        try {
            selectLessonsMenu = MenuOptionsValidator.userInputMenu(Globals.menuNameMainMenu, ValidateSelectLessonsBy::menuOptions, ValidateSelectLessonsBy::menuResults, this);

        } catch (NullPointerException e) {
            selectLessonsMenu = -1;
        }


        ArrayList<Lesson> selectedLessons;
        Lesson selectedLesson = null;

        switch (selectLessonsMenu) {
            case 1:
                selectedLessons = MenuOptionsValidator.userInputMenu(Globals.menuNameSelectLessonByDay, ValidateLessonByDay::menuOptions, ValidateLessonByDay::menuResults, this);
                if (selectedLessons != null) {
                    this.setSelectedLessons(selectedLessons);
                    selectedLesson = selectLessonFromLessons();
                }
                break;
            case 2:
                selectedLessons = MenuOptionsValidator.userInputMenu(Globals.menuNameSelectLessonByGrade, ValidateLessonByGrade::menuOptions, ValidateLessonByGrade::menuResults, this);
                if (selectedLessons != null) {
                    this.setSelectedLessons(selectedLessons);
                    selectedLesson = selectLessonFromLessons();
                }
                break;
            case 3:
                selectedLessons = MenuOptionsValidator.userInputMenu(Globals.menuNameSelectLessonByCoach, ValidateLessonByCoach::menuOptions, ValidateLessonByCoach::menuResults, this);
                if (selectedLessons != null) {
                    this.setSelectedLessons(selectedLessons);
                    selectedLesson = selectLessonFromLessons();
                }
                break;
            case 4:
                selectedLessons = MenuOptionsValidator.userInputMenu(Globals.menuNameSelectLessonByLearner, ValidateLessonByLearner::menuOptions, ValidateLessonByLearner::menuResults, this);
                if (selectedLessons != null) {
                    this.setSelectedLessons(selectedLessons);
                    selectedLesson = selectLessonFromLessons();
                }
                break;
            default:
                break;
        }
        return selectedLesson;
    }

    private Lesson selectLessonFromLessons() {
        return MenuOptionsValidator.userInputMenu(Globals.menuNameSelectLesson, ValidateSelectLessonFromLessons::menuOptions, ValidateSelectLessonFromLessons::menuResults, this);
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

    public Learner getSelectedLearner() {
        return this.selectedLearner;
    }

    public void setSelectedLearner(Learner selectedLearner) {
        this.selectedLearner = selectedLearner;
    }

    public Lesson getSelectedLesson() {
        return this.selectedLesson;
    }

    public void setSelectedLesson(Lesson selectedLesson) {
        this.selectedLesson = selectedLesson;
    }

    public ArrayList<Lesson> getSelectedLessons() {
        return this.selectedLessons;
    }

    public void setSelectedLessons(ArrayList<Lesson> selectedLessons) {
        this.selectedLessons = selectedLessons;
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
        return this.grades.get(gradeNumber);
    }

    public Day getDayByNumber(int dayNumber) {
        return this.days.get(dayNumber - 1);
    }
}
