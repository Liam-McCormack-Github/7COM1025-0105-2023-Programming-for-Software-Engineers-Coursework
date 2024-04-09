package common;

import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GradeTest {
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
    public void testConstructor() {
        int level = Globals.minGrade + 1;

        Grade grade = new Grade(HJSS, level);

        assertNotNull("Grade should not be null", grade);
        assertEquals("Grade should match input", level, grade.getLevel());

        assertTrue("Learner should be added to HJSS learners list", HJSS.getGrades().contains(grade));
    }

    @Test
    public void testGettersAndSetter() {
        int level = Globals.minGrade + 1;
        Grade grade = new Grade(HJSS, level);

        assertNotNull("Grade should not be null", grade);
        assertNotNull("Grade ID should not be null", grade.getId());
    }


    @Test
    public void gradeCreationWithValidLevel() {
        Grade grade = new Grade(HJSS, 1);
        Assert.assertNotNull("Grade should be successfully created", grade);
        Assert.assertEquals("Grade level should be 1", 1, grade.getLevel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void gradeCreationWithInvalidLevel() {
        new Grade(HJSS, Globals.minGrade - 1);
    }

    @Test
    public void settingAndGettingComment() {
        Grade grade = new Grade(HJSS, 2);
        grade.setComment("Intermediate level");
        Assert.assertEquals("Comment should match the set value", "Intermediate level", grade.getComment());
    }

    @Test
    public void compareToDifferentGrades() {
        Grade lowerGrade = new Grade(HJSS, 1);
        Grade higherGrade = new Grade(HJSS, 2);

        Assert.assertTrue("Higher grade should be 'greater than' lower grade", higherGrade.compareTo(lowerGrade) > 0);
        Assert.assertTrue("Lower grade should be 'less than' higher grade", lowerGrade.compareTo(higherGrade) < 0);
    }
}
