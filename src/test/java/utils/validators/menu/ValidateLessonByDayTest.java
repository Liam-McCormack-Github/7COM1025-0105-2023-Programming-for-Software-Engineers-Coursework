package utils.validators.menu;

import common.Lesson;
import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.Utils;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ValidateLessonByDayTest {
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
    public void testMenuOptionsDisplaysAll() {
        ArrayList<String> options = ValidateLessonByDay.menuOptions(HJSS);

        assertEquals("Options list should be empty when there are no Days", HJSS.getDays().size(), options.size());
    }

    @Test
    public void testMenuOptionsWithNoDays() {
        HJSS.getDays().clear();

        ArrayList<String> options = ValidateLessonByDay.menuOptions(HJSS);

        assertTrue("Options list should be empty when there are no Days", options.isEmpty());
    }


    @Test
    public void testMenuResultsWithExitCode() {
        int choice = Globals.exitCode;
        Utils.ValidationResult<ArrayList<Lesson>> result = ValidateLessonByDay.menuResults(HJSS, choice);

        assertTrue("Result should be valid for a valid Day selection", result.isValid());
        assertNull("Lessons list should be null for a exit selection", result.getValue());
        assertNotNull("Error message should be null for a exit selection", result.getErrorMessage());
    }

    @Test
    public void testMenuResultsWithValidSelectionLow() {
        int choice = 1;
        Utils.ValidationResult<ArrayList<Lesson>> result = ValidateLessonByDay.menuResults(HJSS, choice);

        assertTrue("Result should be valid for a valid Day selection", result.isValid());
        assertNotNull("Lessons list should not be null for a valid selection", result.getValue());
        assertNull("Error message should be null for a valid selection", result.getErrorMessage());
    }

    @Test
    public void testMenuResultsWithValidSelectionBelow() {
        int choice = 0;
        Utils.ValidationResult<ArrayList<Lesson>> result = ValidateLessonByDay.menuResults(HJSS, choice);

        assertFalse("Result should be invalid for choice 0", result.isValid());
        assertNull("No lessons should be returned for invalid choice", result.getValue());
        assertNotNull("Error message should be present for invalid choice", result.getErrorMessage());
    }

    @Test
    public void testMenuResultsWithValidSelectionAbove() {

        int choice = HJSS.getDays().size() + 1;
        Utils.ValidationResult<ArrayList<Lesson>> result = ValidateLessonByDay.menuResults(HJSS, choice);

        assertFalse("Result should be invalid for choice beyond Days size", result.isValid());
        assertNull("No lessons should be returned for invalid choice", result.getValue());
        assertNotNull("Error message should be present for invalid choice", result.getErrorMessage());
    }
}
