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

public class ValidateLessonByGradeTest {
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


    /*
        public UUID getId() {
        return this.id;
    }

    public int getDayOfTheWeek() {
        return this.dayOfTheWeek;
    }

    */

    @Test
    public void testMenuOptionsDisplaysAll() {
        ArrayList<String> options = ValidateLessonByGrade.menuOptions(HJSS);

        assertEquals("Options list should be empty when there are no Grades", HJSS.getGrades().size() - 1, options.size());
    }

    @Test
    public void testMenuOptionsWithNoGrades() {
        HJSS.getGrades().clear();

        ArrayList<String> options = ValidateLessonByGrade.menuOptions(HJSS);

        assertTrue("Options list should be empty when there are no Grades", options.isEmpty());
    }


    @Test
    public void testMenuResultsWithExitCode() {
        int choice = Globals.exitCode;
        Utils.ValidationResult<ArrayList<Lesson>> result = ValidateLessonByGrade.menuResults(HJSS, choice);

        assertTrue("Result should be valid for a valid Grade selection", result.isValid());
        assertNull("Lessons list should be null for a exit selection", result.getValue());
        assertNotNull("Error message should be null for a exit selection", result.getErrorMessage());
    }

    @Test
    public void testMenuResultsWithValidSelectionLow() {
        int choice = 1;
        Utils.ValidationResult<ArrayList<Lesson>> result = ValidateLessonByGrade.menuResults(HJSS, choice);

        assertTrue("Result should be valid for a valid Grade selection", result.isValid());
        assertNotNull("Lessons list should not be null for a valid selection", result.getValue());
        assertNull("Error message should be null for a valid selection", result.getErrorMessage());
    }

    @Test
    public void testMenuResultsWithValidSelectionHigh() {
        int choice = HJSS.getGrades().size() - 1;
        Utils.ValidationResult<ArrayList<Lesson>> result = ValidateLessonByGrade.menuResults(HJSS, choice);

        assertTrue("Result should be valid for a valid Grade selection", result.isValid());
        assertNotNull("Lessons list should not be null for a valid selection", result.getValue());
        assertNull("Error message should be null for a valid selection", result.getErrorMessage());
    }

    @Test
    public void testMenuResultsWithValidSelectionBelow() {
        int choice = 0;
        Utils.ValidationResult<ArrayList<Lesson>> result = ValidateLessonByGrade.menuResults(HJSS, choice);

        assertFalse("Result should be invalid for choice 0", result.isValid());
        assertNull("No lessons should be returned for invalid choice", result.getValue());
        assertNotNull("Error message should be present for invalid choice", result.getErrorMessage());
    }

    @Test
    public void testMenuResultsWithValidSelectionAbove() {

        int choice = HJSS.getGrades().size() + 1;
        Utils.ValidationResult<ArrayList<Lesson>> result = ValidateLessonByGrade.menuResults(HJSS, choice);

        assertFalse("Result should be invalid for choice beyond Grades size", result.isValid());
        assertNull("No lessons should be returned for invalid choice", result.getValue());
        assertNotNull("Error message should be present for invalid choice", result.getErrorMessage());
    }
}
