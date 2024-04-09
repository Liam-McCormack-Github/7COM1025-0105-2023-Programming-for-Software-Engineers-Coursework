package common;

import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import utils.UserInputValidator;
import utils.Utils;
import utils.validators.input.*;

import java.util.ArrayList;
import java.util.NavigableSet;
import java.util.UUID;

public class Learner {
    private final UUID id;
    private final ArrayList<Booking> bookings;
    private final ArrayList<Lesson> lessons;
    private final ArrayList<Lesson> lessonsHigherGradeAchieved;
    private final ArrayList<Review> lessonsReviewed;
    private Grade grade;
    private String name;
    private String gender;
    private int age;
    private String contact;
    private String number;


    // Constructor
    public Learner(HatfieldJuniorSwimmingSchool HJSS, String name, String gender, int age, String contact, String number, Grade grade) {
        this.id = UUID.randomUUID();
        this.setName(name);
        this.setGender(gender);
        this.setAge(age);
        this.setContact(contact);
        this.setNumber(number);

        this.grade = grade;


        this.lessons = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.lessonsHigherGradeAchieved = new ArrayList<>();
        this.lessonsReviewed = new ArrayList<>();

        HJSS.getLearners().add(this);
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
    public String getInfo() {
        return String.format("(%s, lvl: %d)",
                this.name, this.getGradeLevel()
        );

    }

    public void increaseGrade(NavigableSet<Grade> grades) {
        // Find the next higher grade
        Grade nextGrade = grades.higher(this.grade);

        // If there is a next grade, change grade to that next grade
        if (nextGrade != null) {
            this.grade = nextGrade;
        }
    }

    public void printReport() {
        ArrayList<String> bookedLessons = new ArrayList<>();
        ArrayList<String> cancelledLessons = new ArrayList<>();
        ArrayList<String> attendedLessons = new ArrayList<>();

        for (Booking booking : this.bookings) {
            String lessonTitle = booking.getLesson().getLessonTitle();

            bookedLessons.add(lessonTitle);

            for (Lesson lesson : this.lessonsHigherGradeAchieved) {
                if (booking.getLesson().getId() == lesson.getId()) {
                    lessonTitle = String.format("%s%s%s\tLearner progressed to grade level %d", Globals.ANSI_GREEN, lessonTitle, Globals.ANSI_RESET, lesson.getGradeLevel());
                }
            }

            if (booking.isCancelled()) {
                cancelledLessons.add(lessonTitle);
            } else {
                attendedLessons.add(lessonTitle);
            }
        }

        System.out.printf("Learner: %s,\tFinal grade level: %s,\tAge: %d%n", this.name, this.getGradeLevel(), this.age);

        System.out.printf("\tBooked Lessons: %d%n", this.bookings.size());
        for (String x : bookedLessons) {
            System.out.printf("\t\t%s%n", x);
        }

        System.out.printf("\t%sCancelled%s Lessons: %d%n", Globals.ANSI_RED, Globals.ANSI_RESET, cancelledLessons.size());
        for (String x : cancelledLessons) {
            System.out.printf("\t\t%s%n", x);
        }

        System.out.printf("\t%sAttended%s Lessons: %d%n", Globals.ANSI_GREEN, Globals.ANSI_RESET, attendedLessons.size());
        for (String x : attendedLessons) {
            System.out.printf("\t\t%s%n", x);
        }

        Utils.printSeparator(Globals.ANSI_BLUE);
    }


    @Override
    public String toString() {

        return String.format(
                "Learner{UUID=%s,\tName=%s,\tGender=%s,\tAge=%d,\tContact=%s,\tPhoneNumber=%s,\tGrade=[%s],\tLessons=[%d],\tLessons Where Higher Grade=[%d],\tBookings=[%d],\tLessons Reviewed=[%d]}",
                this.id, this.name, this.gender, this.age, this.contact, this.number, this.grade, this.lessons.size(), this.lessonsHigherGradeAchieved.size(), this.bookings.size(), this.lessonsReviewed.size()
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
    private void setName(String value) {
        UserInputValidator.ValidationResult<String> result = ValidateName.validate(String.valueOf(value));
        if (!result.isValid()) {
            throw new IllegalArgumentException(result.getErrorMessage());
        }
        this.name = result.getValue();
    }

    public int getAge() {
        return this.age;
    }

    private void setAge(int value) {
        UserInputValidator.ValidationResult<Integer> result = ValidateAge.validate(String.valueOf(value));
        if (!result.isValid()) {
            throw new IllegalArgumentException(result.getErrorMessage());
        }
        this.age = result.getValue();
    }

    public String getGender() {
        return this.gender;
    }

    private void setGender(String value) {
        UserInputValidator.ValidationResult<String> result = ValidateGender.validate(String.valueOf(value));
        if (!result.isValid()) {
            throw new IllegalArgumentException(result.getErrorMessage());
        }
        this.gender = result.getValue();
    }

    public String getNumber() {
        return this.number;
    }

    private void setNumber(String value) {
        UserInputValidator.ValidationResult<String> result = ValidatePhoneNumber.validate(String.valueOf(value));
        if (!result.isValid()) {
            throw new IllegalArgumentException(result.getErrorMessage());
        }
        this.number = result.getValue();
    }

    public String getContact() {
        return this.contact;
    }

    private void setContact(String value) {
        UserInputValidator.ValidationResult<String> result = ValidateContact.validate(String.valueOf(value));
        if (!result.isValid()) {
            throw new IllegalArgumentException(result.getErrorMessage());
        }
        this.contact = result.getValue();
    }

    public Grade getGrade() {
        return this.grade;
    }

    public int getGradeLevel() {
        return this.grade.getLevel();
    }

    public ArrayList<Booking> getBookings() {
        return this.bookings;
    }

    public ArrayList<Lesson> getLessons() {
        return this.lessons;
    }

    public ArrayList<Lesson> getLessonsHigherGradeAchieved() {
        return this.lessonsHigherGradeAchieved;
    }

    public ArrayList<Review> getLessonsReviewed() {
        return this.lessonsReviewed;
    }

}
