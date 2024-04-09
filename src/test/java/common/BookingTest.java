package common;

import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import core.Seeder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookingTest {


    private HatfieldJuniorSwimmingSchool HJSS;

    @Before
    public void setUp() {
        Globals.resetStaticTrees();
        class TestableHJSS extends HatfieldJuniorSwimmingSchool {
            @Override
            public void init() {
                Seeder.seedTimeslots(this);
                Seeder.seedCoaches(this);
                // Seeder.seedLearners(this);
                // Seeder.seedLessons(this);
                // Seeder.seedBookings(this);
                this.getLessonsForEachDay();
            }
        }
        this.HJSS = new TestableHJSS();
        this.HJSS.preInit();
        this.HJSS.init();
    }


    @After
    public void tearDown() {
        if (this.HJSS != null) {
            this.HJSS.resetState();
        }
        this.HJSS = null;

        Globals.resetStaticTrees();
    }

    @Test
    public void testConstructor() {
        Lesson lesson = new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(1));
        Learner learner = new Learner(HJSS, "testBookingCreation", "male", 10, "test@mail.com", "1234567898", HJSS.getGradeByNumber(0));
        Booking booking = new Booking(HJSS, lesson, learner);

        assertNotNull("Booking should not be null", booking);
        assertEquals("Lesson should match", lesson, booking.getLesson());
        assertEquals("Learner should match", learner, booking.getLearner());
        assertFalse("Booking should not be cancelled initially", booking.isCancelled());

        assertTrue("Learner should be added to HJSS learners list", HJSS.getBookings().contains(booking));
    }


    @Test
    public void testGettersAndSetter() {
        Lesson lesson = new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(1));
        Learner learner = new Learner(HJSS, "testBookingCreation", "male", 10, "test@mail.com", "1234567898", HJSS.getGradeByNumber(0));
        Booking booking = new Booking(HJSS, lesson, learner);

        assertNotNull("Booking should not be null", booking.toString());
        assertNotNull("Booking should not be null", booking);
        assertNotNull("Booking ID should not be null", booking.getId());
    }


    @Test
    public void testBookingCancellation() {


        Lesson lesson = new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(1));
        Learner learner = new Learner(HJSS, "testBookingCancellation", "male", 10, "test@mail.com", "1234567898", HJSS.getGradeByNumber(0));
        Booking booking = new Booking(HJSS, lesson, learner);

        booking.Cancel();

        assertTrue("Booking should be cancelled", booking.isCancelled());
    }

    @Test(expected = NullPointerException.class)
    public void testInvalidBookingCreationThrowsException() {
        new Booking(HJSS, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotCreateDuplicateBooking() {
        Lesson lesson = new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(1));
        Learner learner = new Learner(HJSS, "testCannotCreateDuplicateBooking", "male", 10, "test@mail.com", "1234567898", HJSS.getGradeByNumber(0));
        new Booking(HJSS, lesson, learner);
        new Booking(HJSS, lesson, learner);
    }


    @Test
    public void testCanBookSubsequent() {
        Learner learner = new Learner(HJSS, "testCanBookSubsequent", "male", 10, "test@mail.com", "1234567898", HJSS.getGradeByNumber(0));

        Lesson lesson_1 = new Lesson(HJSS, HJSS.getGradeByNumber(0), HJSS.getCoachByNumber(1));
        Lesson lesson_2 = new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(1));
        Lesson lesson_3 = new Lesson(HJSS, HJSS.getGradeByNumber(2), HJSS.getCoachByNumber(1));
        Lesson lesson_4 = new Lesson(HJSS, HJSS.getGradeByNumber(3), HJSS.getCoachByNumber(1));
        Lesson lesson_5 = new Lesson(HJSS, HJSS.getGradeByNumber(4), HJSS.getCoachByNumber(1));
        Lesson lesson_6 = new Lesson(HJSS, HJSS.getGradeByNumber(5), HJSS.getCoachByNumber(1));

        new Booking(HJSS, lesson_1, learner);
        new Booking(HJSS, lesson_2, learner);
        new Booking(HJSS, lesson_3, learner);
        new Booking(HJSS, lesson_4, learner);
        new Booking(HJSS, lesson_5, learner);
        new Booking(HJSS, lesson_6, learner);
    }

    @Test
    public void testCanCancellingBookingCancelsDependents() {
        Learner learner = new Learner(HJSS, "testCanCancellingBookingCancelsDependents", "male", 10, "test@mail.com", "1234567898", HJSS.getGradeByNumber(0));

        Lesson lesson_1 = new Lesson(HJSS, HJSS.getGradeByNumber(0), HJSS.getCoachByNumber(1));
        Lesson lesson_2 = new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(1));
        Lesson lesson_3 = new Lesson(HJSS, HJSS.getGradeByNumber(2), HJSS.getCoachByNumber(1));
        Lesson lesson_4 = new Lesson(HJSS, HJSS.getGradeByNumber(3), HJSS.getCoachByNumber(1));
        Lesson lesson_5 = new Lesson(HJSS, HJSS.getGradeByNumber(4), HJSS.getCoachByNumber(1));
        Lesson lesson_6 = new Lesson(HJSS, HJSS.getGradeByNumber(5), HJSS.getCoachByNumber(1));

        Booking booking_1 = new Booking(HJSS, lesson_1, learner);
        Booking booking_2 = new Booking(HJSS, lesson_2, learner);
        Booking booking_3 = new Booking(HJSS, lesson_3, learner);
        Booking booking_4 = new Booking(HJSS, lesson_4, learner);
        Booking booking_5 = new Booking(HJSS, lesson_5, learner);
        Booking booking_6 = new Booking(HJSS, lesson_6, learner);

        booking_1.Cancel();

        assertTrue("Booking should be cancelled", booking_1.isCancelled());
        assertTrue("Booking should be cancelled", booking_2.isCancelled());
        assertTrue("Booking should be cancelled", booking_3.isCancelled());
        assertTrue("Booking should be cancelled", booking_4.isCancelled());
        assertTrue("Booking should be cancelled", booking_5.isCancelled());
        assertTrue("Booking should be cancelled", booking_6.isCancelled());

    }


    @Test(expected = IllegalArgumentException.class)
    public void testCancelledLessonCannotBeUsedAsDependants() {
        Learner learner = new Learner(HJSS, "testCancelledLessonCannotBeUsedAsDependants", "male", 10, "test@mail.com", "1234567898", HJSS.getGradeByNumber(0));

        Lesson lesson_1 = new Lesson(HJSS, HJSS.getGradeByNumber(0), HJSS.getCoachByNumber(1));
        Lesson lesson_2 = new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(1));
        Lesson lesson_3 = new Lesson(HJSS, HJSS.getGradeByNumber(2), HJSS.getCoachByNumber(1));
        Lesson lesson_4 = new Lesson(HJSS, HJSS.getGradeByNumber(3), HJSS.getCoachByNumber(1));

        new Booking(HJSS, lesson_1, learner);
        new Booking(HJSS, lesson_2, learner);

        Booking booking_3 = new Booking(HJSS, lesson_3, learner);

        booking_3.Cancel();

        new Booking(HJSS, lesson_4, learner);

    }


    @Test(expected = IllegalArgumentException.class)
    public void testDependentLessonObeySpaceTime() {
        Learner learner = new Learner(HJSS, "testDependentLessonObeySpaceTime", "male", 10, "test@mail.com", "1234567898", HJSS.getGradeByNumber(0));

        Lesson lesson_1 = new Lesson(HJSS, HJSS.getGradeByNumber(0), HJSS.getCoachByNumber(1));
        Lesson lesson_2 = new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(1));
        Lesson lesson_3 = new Lesson(HJSS, HJSS.getGradeByNumber(2), HJSS.getCoachByNumber(1));
        Lesson lesson_4 = new Lesson(HJSS, HJSS.getGradeByNumber(3), HJSS.getCoachByNumber(1));
        Lesson lesson_5 = new Lesson(HJSS, HJSS.getGradeByNumber(4), HJSS.getCoachByNumber(1));

        new Booking(HJSS, lesson_1, learner);
        new Booking(HJSS, lesson_2, learner);
        new Booking(HJSS, lesson_4, learner);
        new Booking(HJSS, lesson_3, learner);
        new Booking(HJSS, lesson_5, learner);

    }

}
