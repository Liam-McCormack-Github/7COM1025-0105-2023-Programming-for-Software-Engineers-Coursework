package common;

import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import core.Seeder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReviewTest {
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
    public void testConstructor() {

        int rating = 4;
        String comment = "Test Comment";
        Lesson lesson = new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(1));
        Learner learner = new Learner(HJSS, "testConstructor", "male", 10, "test@mail.com", "1234567898", HJSS.getGradeByNumber(0));


        Review review = new Review(HJSS, rating, comment, lesson, learner);

        assertNotNull("Review should not be null", review);

        assertEquals("Rating should match", rating, review.getRating());
        assertEquals("Comment should match", comment, review.getComment());
        assertEquals("Lesson should match", lesson, review.getLesson());
        assertEquals("Learner should match", learner, review.getLearner());

        assertTrue("Learner should be added to HJSS learners list", HJSS.getReviews().contains(review));
    }


    @Test
    public void testGettersAndSetter() {

        int rating = 4;
        String comment = "Test Comment";
        Lesson lesson = new Lesson(HJSS, HJSS.getGradeByNumber(1), HJSS.getCoachByNumber(1));
        Learner learner = new Learner(HJSS, "testConstructor", "male", 10, "test@mail.com", "1234567898", HJSS.getGradeByNumber(0));


        Review review = new Review(HJSS, rating, comment, lesson, learner);

        assertNotNull("Review should not be null", review);
        assertNotNull("Review should not be null", review.toString());
        assertNotNull("Review ID should not be null", review.getId());
    }

}
