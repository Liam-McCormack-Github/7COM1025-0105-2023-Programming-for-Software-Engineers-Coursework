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

public class ValidateSelectLessonFromLessonsTest {
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

        HJSS.setSelectedLessons(HJSS.getGradeByNumber(1).getLessons());

        ArrayList<String> options = ValidateSelectLessonFromLessons.menuOptions(HJSS);

        assertEquals("Options list should be empty when there are no selected Lessons", HJSS.getSelectedLessons().size(), options.size());
    }


    @Test
    public void testValidLessonSelection() {
        HJSS.setSelectedLessons(HJSS.getGradeByNumber(1).getLessons());
        int validChoice = 1;

        Utils.ValidationResult<Lesson> result = ValidateSelectLessonFromLessons.menuResults(HJSS, validChoice);

        assertTrue("Expected valid result for a valid lesson selection", result.isValid());
        assertNotNull("Expected a lesson object for valid selection", result.getValue());
        assertNull("Expected no error message for a valid selection", result.getErrorMessage());
    }

    @Test
    public void testSelectionUsingExitCode() {
        int exitChoice = Globals.exitCode;

        Utils.ValidationResult<Lesson> result = ValidateSelectLessonFromLessons.menuResults(HJSS, exitChoice);

        assertTrue("Expected valid result for exit code selection", result.isValid());
        assertNull("Expected no lesson object when using exit code", result.getValue());
        assertEquals("Expected specific message for exiting", "Returning To Main Menu", result.getErrorMessage());
    }


    @Test
    public void testInvalidSelectionAboveRange() {
        int invalidChoice = HJSS.getSelectedLessons().size() + 1;

        Utils.ValidationResult<Lesson> result = ValidateSelectLessonFromLessons.menuResults(HJSS, invalidChoice);

        assertFalse("Expected invalid result for selection above valid range", result.isValid());
        assertNull("Expected no lesson object for invalid selection", result.getValue());
        assertTrue("Expected error message for invalid selection", result.getErrorMessage().contains("Invalid Selection:"));
    }

    @Test
    public void testInvalidSelectionBelowRange() {
        int invalidChoice = 0;

        Utils.ValidationResult<Lesson> result = ValidateSelectLessonFromLessons.menuResults(HJSS, invalidChoice);

        assertFalse("Expected invalid result for selection below valid range", result.isValid());
        assertNull("Expected no lesson object for invalid selection", result.getValue());
        assertTrue("Expected error message for invalid selection", result.getErrorMessage().contains("Invalid Selection:"));
    }


}
