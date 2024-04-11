package utils.validators.others;

import common.Lesson;
import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import core.Seeder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.Utils;

import static org.junit.Assert.*;

public class ValidateCancellationTest {
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
    public void testLessonCanBeCancelled() {
        Lesson lesson = new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(1));

        Utils.ValidationResult<Lesson> result = ValidateCancellation.validate(lesson);

        assertTrue("Lesson should be valid for cancellation", result.isValid());
        assertNull("No lesson should be returned", result.getValue());
        assertNull("There should be no error message", result.getErrorMessage());
    }

    @Test
    public void testLessonCannotBeCancelled() {
        Lesson lesson = new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(1));
        lesson.setFinished(true);

        Utils.ValidationResult<Lesson> result = ValidateCancellation.validate(lesson);

        assertFalse("Lesson should not be valid for cancellation", result.isValid());
        assertNull("No lesson should be returned for a past lesson", result.getValue());
        assertEquals("Error message should indicate the lesson is in the past", "You can't cancel a lesson in the past", result.getErrorMessage());
    }


}
