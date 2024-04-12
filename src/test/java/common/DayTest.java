package common;

import core.HatfieldJuniorSwimmingSchool;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class DayTest {
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
    public void testGettersAndSetter() {
        Day day = HJSS.getDayByNumber(1);

        assertNotNull("Day should not be null", day);
        assertNotNull("Day ID should not be null", day.getId());
        assertNotEquals("Day of the Week should not be 0", 0, day.getDayOfTheWeek());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetDayWithInvalidInput() {
        new Day(HJSS, 8);
    }

    @Test
    public void testGetLessonsOfTheDay() {
        // Calculate total lessons across all days
        int totalLessonsAcrossDays = 0;
        for (Day day : HJSS.getDays()) {
            totalLessonsAcrossDays += day.getLessons().size();
        }

        Assert.assertEquals("Total lessons across all days should match total lessons in HJSS", HJSS.getLessons().size(), totalLessonsAcrossDays);
    }

}
