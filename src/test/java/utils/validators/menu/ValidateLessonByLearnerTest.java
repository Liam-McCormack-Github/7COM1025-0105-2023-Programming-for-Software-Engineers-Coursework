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

public class ValidateLessonByLearnerTest {
    private HatfieldJuniorSwimmingSchool HJSS;

    @Before
    public void setUp() {
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

    }

    @Test
    public void testMenuOptionsDisplaysAll() {
        ArrayList<String> options = ValidateLessonByLearner.menuOptions(HJSS);

        assertEquals("Options list should be empty when there are no Learners", HJSS.getLearners().size(), options.size());
    }

    @Test
    public void testMenuOptionsWithNoLearners() {
        HJSS.getLearners().clear();

        ArrayList<String> options = ValidateLessonByLearner.menuOptions(HJSS);

        assertTrue("Options list should be empty when there are no Learners", options.isEmpty());
    }


    @Test
    public void testMenuResultsWithExitCode() {
        int choice = Globals.exitCode;
        Utils.ValidationResult<ArrayList<Lesson>> result = ValidateLessonByLearner.menuResults(HJSS, choice);

        assertTrue("Result should be valid for a valid Learner selection", result.isValid());
        assertNull("Lessons list should be null for a exit selection", result.getValue());
        assertNotNull("Error message should be null for a exit selection", result.getErrorMessage());
    }

    @Test
    public void testMenuResultsWithValidSelectionLow() {
        int choice = 1;
        Utils.ValidationResult<ArrayList<Lesson>> result = ValidateLessonByLearner.menuResults(HJSS, choice);

        assertTrue("Result should be valid for a valid Learner selection", result.isValid());
        assertNotNull("Lessons list should not be null for a valid selection", result.getValue());
        assertNull("Error message should be null for a valid selection", result.getErrorMessage());
    }

    @Test
    public void testMenuResultsWithValidSelectionHigh() {
        int choice = HJSS.getLearners().size();
        Utils.ValidationResult<ArrayList<Lesson>> result = ValidateLessonByLearner.menuResults(HJSS, choice);

        assertTrue("Result should be valid for a valid Learner selection", result.isValid());
        assertNotNull("Lessons list should not be null for a valid selection", result.getValue());
        assertNull("Error message should be null for a valid selection", result.getErrorMessage());
    }

    @Test
    public void testMenuResultsWithValidSelectionBelow() {
        int choice = 0;
        Utils.ValidationResult<ArrayList<Lesson>> result = ValidateLessonByLearner.menuResults(HJSS, choice);

        assertFalse("Result should be invalid for choice 0", result.isValid());
        assertNull("No lessons should be returned for invalid choice", result.getValue());
        assertNotNull("Error message should be present for invalid choice", result.getErrorMessage());
    }

    @Test
    public void testMenuResultsWithValidSelectionAbove() {

        int choice = HJSS.getLearners().size() + 1;
        Utils.ValidationResult<ArrayList<Lesson>> result = ValidateLessonByLearner.menuResults(HJSS, choice);

        assertFalse("Result should be invalid for choice beyond Learners size", result.isValid());
        assertNull("No lessons should be returned for invalid choice", result.getValue());
        assertNotNull("Error message should be present for invalid choice", result.getErrorMessage());
    }
}
