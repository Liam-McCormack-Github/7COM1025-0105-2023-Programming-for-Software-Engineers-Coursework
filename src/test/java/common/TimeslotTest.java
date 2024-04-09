package common;

import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import core.Seeder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.*;

public class TimeslotTest {
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
    public void testGettersAndSetter() {
        Timeslot timeslot = HJSS.getTimeslots().get(1);

        assertNotNull("Timeslot ID should not be null", timeslot.getId());
        assertNotNull("Timeslot Duration should not be null", timeslot.getDuration());
        assertNotEquals("Timeslot Duration in Minutes should not be 0", 0, timeslot.getDurationMinutes());
    }

    @Test
    public void testConstructor() {

        Day day = HJSS.getDayByNumber(1);
        String startTime = "01:00";
        String endTime = "02:00";

        Timeslot timeslot = new Timeslot(HJSS, day, startTime, endTime);

        assertNotNull("Timeslot should not be null", timeslot);

        assertEquals("Rating should match", day, timeslot.getDay());
        assertEquals("Comment should match", LocalTime.parse(startTime), timeslot.getStartTime());
        assertEquals("Lesson should match", LocalTime.parse(endTime), timeslot.getEndTime());

        assertTrue("Learner should be added to HJSS learners list", HJSS.getTimeslots().contains(timeslot));
    }
}
