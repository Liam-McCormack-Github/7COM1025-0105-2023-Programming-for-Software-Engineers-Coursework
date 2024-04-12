package utils.validators.menu;

import common.Booking;
import common.Learner;
import common.Lesson;
import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.Utils;

import static org.junit.Assert.*;

public class ValidateMainMenuTest {
    private HatfieldJuniorSwimmingSchool HJSS;

    @Before
    public void setUp() {
        Globals.resetStaticTrees();
        class TestableHJSS extends HatfieldJuniorSwimmingSchool {
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
    public void testValidSelection() {
        int userChoice = 1;

        Utils.ValidationResult<Integer> result = ValidateMainMenu.menuResults(HJSS, userChoice);

        assertTrue("Expected valid result for straightforward choice", result.isValid());
        assertEquals("Expected choice to be returned", Integer.valueOf(userChoice), result.getValue());
        assertNull("Expected no error message for valid choice", result.getErrorMessage());
    }

    @Test
    public void testBookingWithoutSelection() {
        int userChoice = 4;

        Utils.ValidationResult<Integer> result = ValidateMainMenu.menuResults(HJSS, userChoice);

        assertFalse("Expected invalid result for booking without selections", result.isValid());
        assertNotNull("Expected error message for missing selections", result.getErrorMessage());

    }

    @Test
    public void testCancelWithoutBookings() {
        int userChoice = 5;

        Utils.ValidationResult<Integer> result = ValidateMainMenu.menuResults(HJSS, userChoice);

        assertFalse("Expected invalid result for canceling without bookings", result.isValid());
        assertNotNull("Expected specific error message for no bookings", result.getErrorMessage());

    }

    @Test
    public void testExitCode() {
        int userChoice = Globals.exitCode;

        Utils.ValidationResult<Integer> result = ValidateMainMenu.menuResults(HJSS, userChoice);

        assertTrue("Expected valid result for exit code", result.isValid());
        assertNotNull("Expected message for returning to main menu", result.getErrorMessage());
    }

    @Test
    public void testSuccessfulBookingWithSelection() {
        int userChoice = 4;

        Lesson lesson = new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(1));
        Learner learner = new Learner(HJSS, "testSuccessfulBookingWithSelection", "male", 10, "test@mail.com", "1234567898", HJSS.getGradeByNumber(0));

        HJSS.setSelectedLesson(lesson);
        HJSS.setSelectedLearner(learner);


        Utils.ValidationResult<Integer> result = ValidateMainMenu.menuResults(HJSS, userChoice);


        assertTrue("Expected valid result for successful booking", result.isValid());
        assertNull("Expected no error message for successful booking", result.getErrorMessage());
    }

    @Test
    public void testSuccessfulCancellationWithBookings() {
        int userChoice = 5;

        Lesson lesson = new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(1));
        Learner learner = new Learner(HJSS, "testBookingCancellation", "male", 10, "test@mail.com", "1234567898", HJSS.getGradeByNumber(0));

        HJSS.setSelectedLesson(lesson);
        HJSS.setSelectedLearner(learner);

        new Booking(HJSS, lesson, learner);

        Utils.ValidationResult<Integer> result = ValidateMainMenu.menuResults(HJSS, userChoice);

        assertTrue("Expected valid result for successful cancellation", result.isValid());
        assertNull("Expected no error message for successful cancellation", result.getErrorMessage());
    }


    @Test
    public void testInvalidSelection() {
        int userChoice = 0;

        Utils.ValidationResult<Integer> result = ValidateMainMenu.menuResults(HJSS, userChoice);

        assertFalse("Expected invalid result for an invalid choice", result.isValid());
        assertNotNull("Expected message for invalid selection to main menu", result.getErrorMessage());
    }


}
