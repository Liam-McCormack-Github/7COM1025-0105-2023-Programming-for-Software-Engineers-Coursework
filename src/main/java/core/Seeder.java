package core;

import common.*;

public class Seeder {
    // Populate ArrayList Days
    public static void seedDays(HatfieldJuniorSwimmingSchool HJSS) {
        for (int i = 1; i <= Globals.daysOfTheWeek.length; i++) {
            new Day(HJSS, i);
        }
    }

    // Populate ArrayList Grades
    public static void seedGrades(HatfieldJuniorSwimmingSchool HJSS) {
        for (int i = Globals.minGrade; i <= Globals.maxGrade; i++) {
            new Grade(HJSS, i);
        }
        Globals.generateGlobalGradePositionMap(HJSS.getGrades());
        Globals.generateGlobalGradeTreeSet(HJSS.getGrades());
        Globals.generateGlobalGradeLevelMap(HJSS.getGrades());
    }

    // Populate ArrayList Timeslots
    public static void seedTimeslots(HatfieldJuniorSwimmingSchool HJSS) {
        new Timeslot(HJSS, HJSS.getDayByNumber(1), "16:00", "17:00");
        new Timeslot(HJSS, HJSS.getDayByNumber(1), "17:00", "18:00");
        new Timeslot(HJSS, HJSS.getDayByNumber(1), "18:00", "19:00");
        new Timeslot(HJSS, HJSS.getDayByNumber(3), "16:00", "17:00");
        new Timeslot(HJSS, HJSS.getDayByNumber(3), "17:00", "18:00");
        new Timeslot(HJSS, HJSS.getDayByNumber(3), "18:00", "19:00");
        new Timeslot(HJSS, HJSS.getDayByNumber(5), "16:00", "17:00");
        new Timeslot(HJSS, HJSS.getDayByNumber(5), "17:00", "18:00");
        new Timeslot(HJSS, HJSS.getDayByNumber(5), "18:00", "19:00");
        new Timeslot(HJSS, HJSS.getDayByNumber(6), "14:00", "15:00");
        new Timeslot(HJSS, HJSS.getDayByNumber(6), "15:00", "16:00");
    }

    // Populate ArrayList Coaches
    public static void seedCoaches(HatfieldJuniorSwimmingSchool HJSS) {
        new Coach(HJSS, "Helen");
        new Coach(HJSS, "Helena");
        new Coach(HJSS, "Henry");
        new Coach(HJSS, "Harry");
    }

    // Populate ArrayList Learners
    public static void seedLearners(HatfieldJuniorSwimmingSchool HJSS) {
        new Learner(HJSS, "bot1", "male", 4, "bot1@mail.com", "1234567898", HJSS.getGradeByNumber(0));
        new Learner(HJSS, "bot2", "female", 4, "bot2@mail.com", "1234567898", HJSS.getGradeByNumber(1));
        new Learner(HJSS, "bot3", "male", 5, "bot3@mail.com", "1234567898", HJSS.getGradeByNumber(0));
        new Learner(HJSS, "bot4", "female", 5, "bot4@mail.com", "1234567898", HJSS.getGradeByNumber(1));
        new Learner(HJSS, "bot5", "male", 6, "bot5@mail.com", "1234567898", HJSS.getGradeByNumber(1));
        new Learner(HJSS, "bot6", "female", 6, "bot6@mail.com", "1234567898", HJSS.getGradeByNumber(0));
        new Learner(HJSS, "bot7", "male", 7, "bot7@mail.com", "1234567898", HJSS.getGradeByNumber(0));
        new Learner(HJSS, "bot8", "female", 7, "bot8@mail.com", "1234567898", HJSS.getGradeByNumber(1));
        new Learner(HJSS, "bot9", "male", 8, "bot9@mail.com", "1234567898", HJSS.getGradeByNumber(0));
        new Learner(HJSS, "bot10", "female", 8, "bot10@mail.com", "1234567898", HJSS.getGradeByNumber(1));
        new Learner(HJSS, "bot11", "male", 9, "bot11@mail.com", "1234567898", HJSS.getGradeByNumber(1));
        new Learner(HJSS, "bot12", "female", 9, "bot12@mail.com", "1234567898", HJSS.getGradeByNumber(1));
        new Learner(HJSS, "bot13", "male", 10, "bot13@mail.com", "1234567898", HJSS.getGradeByNumber(1));
        new Learner(HJSS, "bot14", "female", 10, "bot14@mail.com", "1234567898", HJSS.getGradeByNumber(1));
        new Learner(HJSS, "bot15", "other", 11, "bot15@mail.com", "1234567898", HJSS.getGradeByNumber(1));
    }

    // Populate ArrayList Lessons
    public static void seedLessons(HatfieldJuniorSwimmingSchool HJSS) {
        // Week 1 - Monday
        new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(1));
        new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(2));
        new Lesson(HJSS, HJSS.getGradeByNumber(2), HJSS.getCoachByNumber(1));
        // Week 1 - Wednesday
        new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(2));
        new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(1));
        new Lesson(HJSS, HJSS.getGradeByNumber(2), HJSS.getCoachByNumber(2));
        // Week 1 - Friday
        new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(3));
        new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(4));
        new Lesson(HJSS, HJSS.getGradeByNumber(2), HJSS.getCoachByNumber(3));
        // Week 1 - Saturday
        new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(4));
        new Lesson(HJSS, HJSS.getGradeByNumber(2), HJSS.getCoachByNumber(3));
        // Week 2 - Monday
        new Lesson(HJSS, HJSS.getGradeByNumber(2), HJSS.getCoachByNumber(1));
        new Lesson(HJSS, HJSS.getGradeByNumber(2), HJSS.getCoachByNumber(2));
        new Lesson(HJSS, HJSS.getGradeByNumber(3), HJSS.getCoachByNumber(1));
        // Week 2 - Wednesday
        new Lesson(HJSS, HJSS.getGradeByNumber(2), HJSS.getCoachByNumber(2));
        new Lesson(HJSS, HJSS.getGradeByNumber(2), HJSS.getCoachByNumber(1));
        new Lesson(HJSS, HJSS.getGradeByNumber(3), HJSS.getCoachByNumber(2));
        // Week 2 - Friday
        new Lesson(HJSS, HJSS.getGradeByNumber(2), HJSS.getCoachByNumber(3));
        new Lesson(HJSS, HJSS.getGradeByNumber(2), HJSS.getCoachByNumber(4));
        new Lesson(HJSS, HJSS.getGradeByNumber(3), HJSS.getCoachByNumber(3));
        // Week 2 - Saturday
        new Lesson(HJSS, HJSS.getGradeByNumber(2), HJSS.getCoachByNumber(4));
        new Lesson(HJSS, HJSS.getGradeByNumber(3), HJSS.getCoachByNumber(3));
        // Week 3 - Monday
        new Lesson(HJSS, HJSS.getGradeByNumber(3), HJSS.getCoachByNumber(1));
        new Lesson(HJSS, HJSS.getGradeByNumber(3), HJSS.getCoachByNumber(2));
        new Lesson(HJSS, HJSS.getGradeByNumber(4), HJSS.getCoachByNumber(1));
        // Week 3 - Wednesday
        new Lesson(HJSS, HJSS.getGradeByNumber(3), HJSS.getCoachByNumber(2));
        new Lesson(HJSS, HJSS.getGradeByNumber(3), HJSS.getCoachByNumber(1));
        new Lesson(HJSS, HJSS.getGradeByNumber(4), HJSS.getCoachByNumber(2));
        // Week 3 - Friday
        new Lesson(HJSS, HJSS.getGradeByNumber(3), HJSS.getCoachByNumber(3));
        new Lesson(HJSS, HJSS.getGradeByNumber(3), HJSS.getCoachByNumber(4));
        new Lesson(HJSS, HJSS.getGradeByNumber(4), HJSS.getCoachByNumber(3));
        // Week 3 - Saturday
        new Lesson(HJSS, HJSS.getGradeByNumber(3), HJSS.getCoachByNumber(4));
        new Lesson(HJSS, HJSS.getGradeByNumber(4), HJSS.getCoachByNumber(3));
        // Week 4 - Monday
        new Lesson(HJSS, HJSS.getGradeByNumber(4), HJSS.getCoachByNumber(1));
        new Lesson(HJSS, HJSS.getGradeByNumber(4), HJSS.getCoachByNumber(2));
        new Lesson(HJSS, HJSS.getGradeByNumber(5), HJSS.getCoachByNumber(1));
        // Week 4 - Wednesday
        new Lesson(HJSS, HJSS.getGradeByNumber(4), HJSS.getCoachByNumber(2));
        new Lesson(HJSS, HJSS.getGradeByNumber(4), HJSS.getCoachByNumber(1));
        new Lesson(HJSS, HJSS.getGradeByNumber(5), HJSS.getCoachByNumber(2));
        // Week 4 - Friday
        new Lesson(HJSS, HJSS.getGradeByNumber(4), HJSS.getCoachByNumber(3));
        new Lesson(HJSS, HJSS.getGradeByNumber(4), HJSS.getCoachByNumber(4));
        new Lesson(HJSS, HJSS.getGradeByNumber(5), HJSS.getCoachByNumber(3));
        // Week 4 - Saturday
        new Lesson(HJSS, HJSS.getGradeByNumber(4), HJSS.getCoachByNumber(4));
        new Lesson(HJSS, HJSS.getGradeByNumber(5), HJSS.getCoachByNumber(3));
    }

    // Populate ArrayList Bookings
    public static void seedBookings(HatfieldJuniorSwimmingSchool HJSS) {
        // Week 1
        new Booking(HJSS, HJSS.getLessonByNumber(1), HJSS.getLearnerByNumber(1));
        new Booking(HJSS, HJSS.getLessonByNumber(1), HJSS.getLearnerByNumber(2));
        new Booking(HJSS, HJSS.getLessonByNumber(1), HJSS.getLearnerByNumber(3));
        new Booking(HJSS, HJSS.getLessonByNumber(2), HJSS.getLearnerByNumber(4));
        new Booking(HJSS, HJSS.getLessonByNumber(2), HJSS.getLearnerByNumber(5));
        new Booking(HJSS, HJSS.getLessonByNumber(3), HJSS.getLearnerByNumber(1));
        new Booking(HJSS, HJSS.getLessonByNumber(3), HJSS.getLearnerByNumber(2));
        new Booking(HJSS, HJSS.getLessonByNumber(3), HJSS.getLearnerByNumber(3));
        new Booking(HJSS, HJSS.getLessonByNumber(3), HJSS.getLearnerByNumber(4));
        new Booking(HJSS, HJSS.getLessonByNumber(3), HJSS.getLearnerByNumber(5));

        new Booking(HJSS, HJSS.getLessonByNumber(4), HJSS.getLearnerByNumber(6));
        new Booking(HJSS, HJSS.getLessonByNumber(4), HJSS.getLearnerByNumber(7));
        new Booking(HJSS, HJSS.getLessonByNumber(5), HJSS.getLearnerByNumber(8));
        new Booking(HJSS, HJSS.getLessonByNumber(5), HJSS.getLearnerByNumber(9));
        new Booking(HJSS, HJSS.getLessonByNumber(6), HJSS.getLearnerByNumber(10));
        new Booking(HJSS, HJSS.getLessonByNumber(7), HJSS.getLearnerByNumber(11));
        new Booking(HJSS, HJSS.getLessonByNumber(8), HJSS.getLearnerByNumber(12));
        new Booking(HJSS, HJSS.getLessonByNumber(9), HJSS.getLearnerByNumber(13));
        new Booking(HJSS, HJSS.getLessonByNumber(10), HJSS.getLearnerByNumber(14));
        new Booking(HJSS, HJSS.getLessonByNumber(11), HJSS.getLearnerByNumber(15));
        // Week 2
        new Booking(HJSS, HJSS.getLessonByNumber(12), HJSS.getLearnerByNumber(1));
        new Booking(HJSS, HJSS.getLessonByNumber(12), HJSS.getLearnerByNumber(2));
        new Booking(HJSS, HJSS.getLessonByNumber(12), HJSS.getLearnerByNumber(3));
        new Booking(HJSS, HJSS.getLessonByNumber(13), HJSS.getLearnerByNumber(4));
        new Booking(HJSS, HJSS.getLessonByNumber(13), HJSS.getLearnerByNumber(5));
        new Booking(HJSS, HJSS.getLessonByNumber(14), HJSS.getLearnerByNumber(1));
        new Booking(HJSS, HJSS.getLessonByNumber(14), HJSS.getLearnerByNumber(2));
        new Booking(HJSS, HJSS.getLessonByNumber(14), HJSS.getLearnerByNumber(3));
        new Booking(HJSS, HJSS.getLessonByNumber(14), HJSS.getLearnerByNumber(4));
        new Booking(HJSS, HJSS.getLessonByNumber(14), HJSS.getLearnerByNumber(5));

        new Booking(HJSS, HJSS.getLessonByNumber(15), HJSS.getLearnerByNumber(6));
        new Booking(HJSS, HJSS.getLessonByNumber(15), HJSS.getLearnerByNumber(7));
        new Booking(HJSS, HJSS.getLessonByNumber(16), HJSS.getLearnerByNumber(8));
        new Booking(HJSS, HJSS.getLessonByNumber(16), HJSS.getLearnerByNumber(9));
        new Booking(HJSS, HJSS.getLessonByNumber(17), HJSS.getLearnerByNumber(10));
        new Booking(HJSS, HJSS.getLessonByNumber(18), HJSS.getLearnerByNumber(11));
        new Booking(HJSS, HJSS.getLessonByNumber(19), HJSS.getLearnerByNumber(12));
        new Booking(HJSS, HJSS.getLessonByNumber(20), HJSS.getLearnerByNumber(13));
        new Booking(HJSS, HJSS.getLessonByNumber(21), HJSS.getLearnerByNumber(14));
        new Booking(HJSS, HJSS.getLessonByNumber(22), HJSS.getLearnerByNumber(15));
        // Week 3
        new Booking(HJSS, HJSS.getLessonByNumber(23), HJSS.getLearnerByNumber(1));
        new Booking(HJSS, HJSS.getLessonByNumber(23), HJSS.getLearnerByNumber(2));
        new Booking(HJSS, HJSS.getLessonByNumber(23), HJSS.getLearnerByNumber(3));
        new Booking(HJSS, HJSS.getLessonByNumber(24), HJSS.getLearnerByNumber(4));
        new Booking(HJSS, HJSS.getLessonByNumber(24), HJSS.getLearnerByNumber(5));
        new Booking(HJSS, HJSS.getLessonByNumber(25), HJSS.getLearnerByNumber(1));
        new Booking(HJSS, HJSS.getLessonByNumber(25), HJSS.getLearnerByNumber(2));
        new Booking(HJSS, HJSS.getLessonByNumber(25), HJSS.getLearnerByNumber(3));
        new Booking(HJSS, HJSS.getLessonByNumber(25), HJSS.getLearnerByNumber(4));
        new Booking(HJSS, HJSS.getLessonByNumber(25), HJSS.getLearnerByNumber(5));

        new Booking(HJSS, HJSS.getLessonByNumber(26), HJSS.getLearnerByNumber(6));
        new Booking(HJSS, HJSS.getLessonByNumber(26), HJSS.getLearnerByNumber(7));
        new Booking(HJSS, HJSS.getLessonByNumber(27), HJSS.getLearnerByNumber(8));
        new Booking(HJSS, HJSS.getLessonByNumber(27), HJSS.getLearnerByNumber(9));
        new Booking(HJSS, HJSS.getLessonByNumber(28), HJSS.getLearnerByNumber(10));
        new Booking(HJSS, HJSS.getLessonByNumber(29), HJSS.getLearnerByNumber(11));
        new Booking(HJSS, HJSS.getLessonByNumber(30), HJSS.getLearnerByNumber(12));
        new Booking(HJSS, HJSS.getLessonByNumber(31), HJSS.getLearnerByNumber(13));
        new Booking(HJSS, HJSS.getLessonByNumber(32), HJSS.getLearnerByNumber(14));
        new Booking(HJSS, HJSS.getLessonByNumber(33), HJSS.getLearnerByNumber(15));
        // Week 4
        new Booking(HJSS, HJSS.getLessonByNumber(34), HJSS.getLearnerByNumber(1));
        new Booking(HJSS, HJSS.getLessonByNumber(34), HJSS.getLearnerByNumber(2));
        new Booking(HJSS, HJSS.getLessonByNumber(34), HJSS.getLearnerByNumber(3));
        new Booking(HJSS, HJSS.getLessonByNumber(35), HJSS.getLearnerByNumber(4));
        new Booking(HJSS, HJSS.getLessonByNumber(35), HJSS.getLearnerByNumber(5));
        new Booking(HJSS, HJSS.getLessonByNumber(36), HJSS.getLearnerByNumber(1));
        new Booking(HJSS, HJSS.getLessonByNumber(36), HJSS.getLearnerByNumber(2));
        new Booking(HJSS, HJSS.getLessonByNumber(36), HJSS.getLearnerByNumber(3));
        new Booking(HJSS, HJSS.getLessonByNumber(36), HJSS.getLearnerByNumber(4));
        new Booking(HJSS, HJSS.getLessonByNumber(36), HJSS.getLearnerByNumber(5));

        new Booking(HJSS, HJSS.getLessonByNumber(37), HJSS.getLearnerByNumber(6));
        new Booking(HJSS, HJSS.getLessonByNumber(37), HJSS.getLearnerByNumber(7));
        new Booking(HJSS, HJSS.getLessonByNumber(38), HJSS.getLearnerByNumber(8));
        new Booking(HJSS, HJSS.getLessonByNumber(38), HJSS.getLearnerByNumber(9));
        new Booking(HJSS, HJSS.getLessonByNumber(39), HJSS.getLearnerByNumber(10));
        new Booking(HJSS, HJSS.getLessonByNumber(40), HJSS.getLearnerByNumber(11));
        new Booking(HJSS, HJSS.getLessonByNumber(41), HJSS.getLearnerByNumber(12));
        new Booking(HJSS, HJSS.getLessonByNumber(42), HJSS.getLearnerByNumber(13));
        new Booking(HJSS, HJSS.getLessonByNumber(43), HJSS.getLearnerByNumber(14));
        new Booking(HJSS, HJSS.getLessonByNumber(44), HJSS.getLearnerByNumber(15));
    }
}
