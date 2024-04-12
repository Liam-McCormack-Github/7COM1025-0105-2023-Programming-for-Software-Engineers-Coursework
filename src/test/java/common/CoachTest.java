package common;

import core.HatfieldJuniorSwimmingSchool;
import core.Seeder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoachTest {
    private HatfieldJuniorSwimmingSchool HJSS;

    @Before
    public void setUp() {
        class TestableHJSS extends HatfieldJuniorSwimmingSchool {
            @Override
            public void init() {
                Seeder.seedTimeslots(this);
                Seeder.seedCoaches(this);
                Seeder.seedLearners(this);
                Seeder.seedLessons(this);
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
    public void testConstructor() {
        String name = "Test Coach";

        Coach coach = new Coach(HJSS, name);

        assertNotNull("Coach should not be null", coach);
        assertEquals("Coach should match input", name, coach.getName());

        assertTrue("Learner should be added to HJSS learners list", HJSS.getCoaches().contains(coach));
    }


    @Test
    public void testGettersAndSetter() {
        Coach coach = new Coach(HJSS, "testGettersAndSetter");

        coach.printReport();

        assertNotNull("Coach should not be null", coach);
        assertNotNull("Coach should not be null", coach);
        assertNotNull("Coach ID should not be null", coach.getId());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCoachCreationWithInvalidName() {
        new Coach(HJSS, "");
    }

    @Test
    public void testAddingAndRetrievingLessons() {
        Coach coach = new Coach(HJSS, "John Doe");
        Lesson lesson = new Lesson(HJSS, HJSS.getGradeByNumber(1), coach);

        Assert.assertFalse("Coach lessons should not be empty", coach.getLessons().isEmpty());
        Assert.assertEquals("Coach should have 1 lesson", 1, coach.getLessons().size());
        Assert.assertSame("Retrieved lesson should be the same as added", lesson, coach.getLessons().get(0));
    }

    @Test
    public void testSettingNewValidNameForCoach() {
        Coach coach = new Coach(HJSS, "Initial Name");
        coach.setName("New Name");
        Assert.assertEquals("Coach name should be updated to 'New Name'", "New Name", coach.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSettingInvalidNameForCoach() {
        Coach coach = new Coach(HJSS, "Initial Name");
        coach.setName("");
    }


}
