package utils.validators.input;

import common.Lesson;
import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.Utils;

import static org.junit.Assert.*;

public class ValidateLessonNumberTest {
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
    public void testValidateWithValidLessonNumber() {
        int validLessonNumber = 5;
        Utils.ValidationResult<Lesson> result = ValidateLessonNumber.validate(HJSS, String.valueOf(validLessonNumber));
        assertTrue("Expected valid result for lesson number: " + validLessonNumber, result.isValid);
        assertNotNull("Expected a Lesson object for valid input", result.getValue());
        assertNull("Expected no error message for valid lesson number", result.getErrorMessage());
    }

    @Test
    public void testValidateLessonNumberBelowRange() {
        Utils.ValidationResult<Lesson> result = ValidateLessonNumber.validate(HJSS, "0");
        assertFalse("Expected invalid result for lesson number below range", result.isValid);
        assertNull("Expected no Lesson object for invalid input", result.getValue());
        assertNotNull("Expected an error message for lesson number below range", result.getErrorMessage());
    }

    @Test
    public void testValidateLessonNumberAboveRange() {
        Utils.ValidationResult<Lesson> result = ValidateLessonNumber.validate(HJSS, String.valueOf(HJSS.getLessons().size() + 1));
        assertFalse("Expected invalid result for lesson number above range", result.isValid);
        assertNull("Expected no Lesson object for invalid input", result.getValue());
        assertNotNull("Expected an error message for lesson number above range", result.getErrorMessage());
    }

    @Test
    public void testValidateWithNonIntegerInput() {
        Utils.ValidationResult<Lesson> result = ValidateLessonNumber.validate(HJSS, "lesson one");
        assertFalse("Expected invalid result for non-integer input", result.isValid);
        assertNull("Expected no Lesson object for non-integer input", result.getValue());
        assertNotNull("Expected an error message for non-integer input", result.getErrorMessage());
    }
}
