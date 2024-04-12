package utils.validators.menu;

import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.Utils;

import static org.junit.Assert.*;

public class ValidateSelectLessonsByTest {
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
    public void testValidSelectionMenuOption1() {
        int userChoice = 1;

        Utils.ValidationResult<Integer> result = ValidateSelectLessonsBy.menuResults(HJSS, userChoice);

        assertTrue("Expected valid result for selection 1", result.isValid());
        assertEquals("Expected selected option to match user choice", Integer.valueOf(userChoice), result.getValue());
        assertNull("Expected no error message for valid selection", result.getErrorMessage());
    }


    @Test
    public void testSelectionUsingExitCode() {
        int userChoice = Globals.exitCode;

        Utils.ValidationResult<Integer> result = ValidateSelectLessonsBy.menuResults(HJSS, userChoice);

        assertTrue("Expected valid result for using exit code", result.isValid());
        assertNull("Expected no value when using exit code", result.getValue());
        assertEquals("Expected specific message for exiting", "Returning To Main Menu", result.getErrorMessage());
    }

    @Test
    public void testInvalidSelectionBelowRange() {
        int userChoice = 0;

        Utils.ValidationResult<Integer> result = ValidateSelectLessonsBy.menuResults(HJSS, userChoice);

        assertFalse("Expected invalid result for selection below range", result.isValid());
        assertTrue("Expected error message for invalid selection", result.getErrorMessage().contains("Invalid Selection:"));
    }

    @Test
    public void testInvalidSelectionAboveRange() {
        int userChoice = 6;

        Utils.ValidationResult<Integer> result = ValidateSelectLessonsBy.menuResults(HJSS, userChoice);

        assertFalse("Expected invalid result for selection above range", result.isValid());
        assertTrue("Expected error message for invalid selection", result.getErrorMessage().contains("Invalid Selection:"));
    }

}
