package utils.validators.others;

import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import core.Seeder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.Utils;

import static org.junit.Assert.*;

public class ValidateDayTest {
    private HatfieldJuniorSwimmingSchool HJSS;

    @Before
    public void setUp() {
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

    }

    @Test
    public void testValidateWithValidInput() {
        for (int day = 1; day <= 7; day++) {
            Utils.ValidationResult<String> result = ValidateDay.validate(String.valueOf(day));
            assertTrue("Day " + day + " should be valid", result.isValid());
            assertEquals("Incorrect day for " + day, Globals.daysOfTheWeek[day - 1], result.getValue());
            assertNull("There should be no error message for a valid day", result.getErrorMessage());
        }
    }

    @Test
    public void testValidateWithInputBelowRange() {
        Utils.ValidationResult<String> result = ValidateDay.validate("0");
        assertFalse("Day 0 should be invalid", result.isValid());
        assertNull("There should be no value for an invalid day", result.getValue());
        assertEquals("Invalid Input: '0' should be an integer between 1 and 7", result.getErrorMessage());
    }

    @Test
    public void testValidateWithInputAboveRange() {
        Utils.ValidationResult<String> result = ValidateDay.validate("8");
        assertFalse("Day 8 should be invalid", result.isValid());
        assertNull("There should be no value for an invalid day", result.getValue());
        assertEquals("Invalid Input: '8' should be an integer between 1 and 7", result.getErrorMessage());
    }

    @Test
    public void testValidateWithNonIntegerInput() {
        Utils.ValidationResult<String> result = ValidateDay.validate("Monday");
        assertFalse("Non-integer input should be invalid", result.isValid());
        assertNull("There should be no value for a non-integer input", result.getValue());
        assertEquals("Invalid Input: 'Monday' should be an integer between 1 and 7", result.getErrorMessage());
    }

}
