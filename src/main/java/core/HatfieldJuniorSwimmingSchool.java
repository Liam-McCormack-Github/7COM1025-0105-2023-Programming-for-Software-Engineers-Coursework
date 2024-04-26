package core;

import common.*;
import utils.MenuOptionsValidator;
import utils.UserInputValidator;
import utils.Utils;
import utils.validators.input.*;
import utils.validators.menu.*;
import utils.validators.others.ValidateCancellation;

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
    private final NavigableSet<Grade> grades;

    private int numberOfLessons;
    private int currentLessonNumber;
    private Learner selectedLearner;
    private Lesson selectedLesson;
    private ArrayList<Lesson> selectedLessons;
    private Scanner scanner;
    private boolean allLessonsFinished;


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

        this.grades = new TreeSet<>();

        this.selectedLearner = null;
        this.selectedLessons = new ArrayList<>();
        this.selectedLesson = null;

        this.allLessonsFinished = false;
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

        // Reset boolean flags
        this.allLessonsFinished = false;
    }

    public void preInit() {
        Globals.resetStaticTrees();
        Seeder.seedDays(this);
        Seeder.seedGrades(this);

        this.scanner = new Scanner(System.in);
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
        if (this.allLessonsFinished) {
            this.printReports();
        }
    }


    private void mainMenu() {

        int selectedOptionFromMainMenu = MenuOptionsValidator.userInputMenu(Globals.menuNameMainMenu, ValidateMainMenu::menuOptions, ValidateMainMenu::menuResults, this);

        switch (selectedOptionFromMainMenu) {
            case 1:
                createLearner();
                break;
            case 2:
                selectLearner();
                break;
            case 3:
                selectLesson();
                break;
            case 4:
                createBooking();
                break;
            case 5:
                cancelBooking();
                break;
            case 6:
                simulateLessons();
                break;
                /*
                test case
            case 888:  // TODO remove
                Learner debugging = new Learner(this, "human1", "other", 11, "human1@mail.com", "1234567898", this.getGradeByNumber(1));
                this.setSelectedLearner(debugging);
                this.setSelectedLesson(this.getLessonByNumber(12));
                break;
                */
            default:
                break;
        }

        if (selectedOptionFromMainMenu == Globals.exitCode) {
            Utils.printOutputMessage("Application is terminating");
            // System.exit(0); -- Unnecessary
        } else if (this.allLessonsFinished) {
            Utils.printSeparator(Globals.ANSI_RED);
            Utils.printOutputMessage("Printing Reports");
            Utils.printSeparator(Globals.ANSI_RED);
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

        return new Learner(this, name, gender, age, contact, number, Globals.getGradeByLevel(grade));
    }

    private void selectLearner() {
        Learner learner = selectLearnerMenu();
        this.setSelectedLearner(learner);
        if (learner != null) {
            Utils.printOutputMessage(String.format("Selected learner (%s)", learner.getInfo()));
        }
    }

    private Learner selectLearnerMenu() {
        return MenuOptionsValidator.userInputMenu(Globals.menuNameSelectLearner, ValidateSelectLearner::menuOptions, ValidateSelectLearner::menuResults, this);
    }

    private void selectLesson() {
        Lesson lesson = selectLessonsMenu();
        this.setSelectedLesson(lesson);
        if (lesson != null) {
            Utils.printOutputMessage(String.format("Selected lesson (%s)", lesson.getLessonTitle()));
        }
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
            case 5:
                selectedLesson = UserInputValidator.validateInput("Enter lesson number: ", ValidateLessonNumber::validate, this);
                break;
            default:
                break;
        }
        return selectedLesson;
    }

    private Lesson selectLessonFromLessons() {
        return MenuOptionsValidator.userInputMenu(Globals.menuNameSelectLesson, ValidateSelectLessonFromLessons::menuOptions, ValidateSelectLessonFromLessons::menuResults, this);
    }

    public void createBooking() {
        try {
            new Booking(this, this.selectedLesson, this.selectedLearner);
            Utils.printOutputMessage(String.format("Booked lesson (%s)", this.selectedLesson.getLessonTitle()));
        } catch (IllegalArgumentException e) {
            Utils.printErrorMessage(e.getLocalizedMessage());
            Utils.printErrorMessage("Could not book Learner to that lesson, please choose a different lesson");
        }
    }

    private void cancelBooking() {
        ArrayList<Booking> bookedLessons = this.selectedLearner.getBookings();
        try {
            if (this.selectedLesson.isFinished()) {
                UserInputValidator.ValidationResult<Lesson> result = ValidateCancellation.validate(this.selectedLesson);
                if (!result.isValid()) {
                    throw new IllegalArgumentException(result.getErrorMessage());
                }

            }

            boolean lessonToCancel = false;
            for (Booking bookedLesson : bookedLessons) {
                if (bookedLesson.getLesson().getId() == this.selectedLesson.getId()) {
                    bookedLesson.Cancel();
                    lessonToCancel = true;
                }
            }

            if (!lessonToCancel) {
                throw new IllegalArgumentException("Lesson has no booking to cancel");
            }

            Utils.printOutputMessage(String.format("Cancelled booking for (%s)", this.selectedLesson.getLessonTitle()));
        } catch (IllegalArgumentException e) {
            Utils.printErrorMessage(e.getLocalizedMessage());
            Utils.printErrorMessage("Could not cancel learner from lesson, please choose a different lesson");
        }
    }

    private void simulateLessons() {

        boolean continueLoop;
        do {
            continueLoop = simulateNextLesson();
        } while (continueLoop);

        if (this.currentLessonNumber >= this.lessons.size()) {
            this.allLessonsFinished = true;
        }
    }

    public boolean simulateNextLesson() {
        if (this.currentLessonNumber >= this.numberOfLessons) {
            return false;
        }

        Lesson currentLesson = this.lessons.get(this.currentLessonNumber);
        ArrayList<Booking> learnersBookToThisLesson = currentLesson.getBookings();
        ArrayList<Booking> usersBookings = new ArrayList<>();

        if (this.selectedLearner != null) {
            usersBookings = this.selectedLearner.getBookings();
        }

        boolean userIsInAttendance = false;

        for (Booking learnersBooking : learnersBookToThisLesson) {
            for (Booking userBooking : usersBookings) {
                if (learnersBooking.getId() == userBooking.getId()) {
                    if (!userBooking.isCancelled()) {
                        Utils.printOutputMessage("User is to attending this lesson");
                        userIsInAttendance = true;
                    }
                }

            }
        }

        this.currentLessonNumber++;
        currentLesson.setFinished(true);

        Utils.printOutputMessage(String.format("Simulating: {%s}", currentLesson.getLessonDetails()));
        for (Learner learner : currentLesson.getLearners()) {
            if (currentLesson.getGradeLevel() == (learner.getGradeLevel() + 1)) {
                learner.increaseGrade(this.grades);
                learner.getLessonsHigherGradeAchieved().add(currentLesson);
            }
        }

        if (userIsInAttendance) {
            Review review = userInputNewReview();
            Utils.printOutputMessage(String.format("Created new review (%s)", review.getLesson().getLessonTitle()));
            return false;
        }

        return this.currentLessonNumber <= this.numberOfLessons;
    }

    private Review userInputNewReview() {

        int rating = UserInputValidator.validateInput("Enter rating (1-5): ", ValidateRating::validate, this);
        String comment = UserInputValidator.validateInput("Enter comment: ", ValidateComment::validate, this);

        return new Review(this, rating, comment, this.getLessonByNumber(this.currentLessonNumber), this.selectedLearner);
    }

    private void printReports() {
        generateLearnerLessonReport();
        generateCoachRatingReport();
    }

    private void generateLearnerLessonReport() {
        Utils.printSeparator(Globals.ANSI_RED);
        Utils.printOutputMessage("Learner Activity Report");
        Utils.printSeparator(Globals.ANSI_RED);
        for (Learner learner : this.learners) {
            learner.printReport();
        }
    }

    private void generateCoachRatingReport() {
        Utils.printSeparator(Globals.ANSI_RED);
        Utils.printOutputMessage("Coach Rating Summary");
        Utils.printSeparator(Globals.ANSI_RED);
        for (Coach coach : this.coaches) {
            coach.printReport();
        }
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

    public NavigableSet<Grade> getGrades() {
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
        return Globals.gradeMap.get(gradeNumber + 1);
    }

    public Day getDayByNumber(int dayNumber) {
        return this.days.get(dayNumber - 1);
    }
}
