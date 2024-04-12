package utils.validators.menu;

import common.Learner;
import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import core.Seeder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.Utils;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ValidateSelectLearnerTest {
    private HatfieldJuniorSwimmingSchool HJSS;

    @Before
    public void setUp() {
        Globals.resetStaticTrees();
        class TestableHJSS extends HatfieldJuniorSwimmingSchool {
            @Override
            public void init() {
                Seeder.seedTimeslots(this);
                Seeder.seedCoaches(this);
                Seeder.seedLessons(this);
                // Seeder.seedLearners(this);
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
    public void testMenuOptionsDisplaysAll() {
        new Learner(HJSS, "testMenuOptionsDisplaysAll", "male", 10, "test@mail.com", "1234567898", HJSS.getGradeByNumber(0));

        ArrayList<String> options = ValidateSelectLearner.menuOptions(HJSS);

        assertEquals("Options list should be empty when there are no selected Lessons", HJSS.getLearners().size(), options.size());
    }

    @Test
    public void testValidLearnerSelection() {
        int userChoice = 1;

        Learner learner = new Learner(HJSS, "testValidLearnerSelection", "male", 10, "test@mail.com", "1234567898", HJSS.getGradeByNumber(0));

        Utils.ValidationResult<Learner> result = ValidateSelectLearner.menuResults(HJSS, userChoice);

        assertTrue("Selection should be valid", result.isValid());
        assertNotNull("Expected a Learner object", result.getValue());
        assertEquals("Learner name should match", learner.getName(), result.getValue().getName());
    }


    @Test
    public void testSelectionBelowRange() {
        int userChoice = 0;
        Utils.ValidationResult<Learner> result = ValidateSelectLearner.menuResults(HJSS, userChoice);

        assertFalse("Selection should be invalid", result.isValid());
        assertNull("No Learner should be returned", result.getValue());
        assertTrue("Error message should indicate invalid selection", result.getErrorMessage().contains("Invalid Selection:"));
    }

    @Test
    public void testSelectionAboveRange() {
        int userChoice = 1;
        Utils.ValidationResult<Learner> result = ValidateSelectLearner.menuResults(HJSS, userChoice);

        assertFalse("Selection should be invalid for empty learners list", result.isValid());
        assertTrue("Error message should indicate invalid selection", result.getErrorMessage().contains("Invalid Selection:"));
    }

    @Test
    public void testUsingExitCode() {
        int userChoice = Globals.exitCode;
        Utils.ValidationResult<Learner> result = ValidateSelectLearner.menuResults(HJSS, userChoice);

        assertTrue("Using exit code should be considered a valid action", result.isValid());
        assertNull("No Learner should be returned when using exit code", result.getValue());
        assertEquals("Expected message for returning to main menu", "Returning To Main Menu", result.getErrorMessage());
    }


}
