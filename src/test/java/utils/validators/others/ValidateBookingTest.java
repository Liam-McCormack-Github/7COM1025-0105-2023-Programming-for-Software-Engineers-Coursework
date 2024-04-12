package utils.validators.others;

import common.Booking;
import common.Learner;
import common.Lesson;
import core.HatfieldJuniorSwimmingSchool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.Utils;

import static org.junit.Assert.assertTrue;

public class ValidateBookingTest {
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
    public void testLearnerAlreadyBookedForTheLesson() {
        Lesson lesson = new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(1));
        Learner learner = new Learner(HJSS, "testLearnerAlreadyBookedForTheLesson", "male", 10, "test@mail.com", "1234567898", HJSS.getGradeByNumber(0));

        Utils.ValidationResult<Booking> result = ValidateBooking.validate(lesson, learner);

        assertTrue(result.isValid);
    }


    @Test
    public void testSuccessfulBookingBasedOnGradeLevel() {
        Lesson lesson = new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(1));
        Learner learner = new Learner(HJSS, "testSuccessfulBookingBasedOnGradeLevel", "male", 10, "test@mail.com", "1234567898", HJSS.getGradeByNumber(0));

        Utils.ValidationResult<Booking> result = ValidateBooking.validate(lesson, learner);

        assertTrue(result.isValid);
    }


}
