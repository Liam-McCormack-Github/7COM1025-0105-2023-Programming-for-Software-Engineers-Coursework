package common;

import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import core.Seeder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LearnerTest {
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
        String name = "tester";
        String gender = "Male";
        int age = 10;
        String contact = "test@mail.com";
        String number = "1234567898";
        Grade grade = HJSS.getGradeByNumber(0);

        Learner learner = new Learner(HJSS, name, gender, age, contact, number, grade);

        assertNotNull("Learner should not be null", learner);
        assertEquals("Name should match input", name, learner.getName());
        assertEquals("Gender should match input", gender, learner.getGender());
        assertEquals("Age should match input", age, learner.getAge());
        assertEquals("Contact should match input", contact, learner.getContact());
        assertEquals("Number should match input", number, learner.getNumber());
        assertEquals("Grade should match input", grade, learner.getGrade());

        assertTrue("Learner should be added to HJSS learners list", HJSS.getLearners().contains(learner));
    }

    @Test
    public void testGettersAndSetter() {
        Learner learner = new Learner(HJSS, "testGettersAndSetter", "male", Globals.minAge, "test@mail.com", "1234567898", HJSS.getGradeByNumber(0));

        learner.printReport();
        assertNotNull("Learner should not be null", learner);
        assertTrue("Learner list when higher grade achieved should be empty when there are no simulated lessons", learner.getLessonsHigherGradeAchieved().isEmpty());
        assertTrue("Reviews by Learner achieved should be empty when there are no reviews", learner.getLessonsReviewed().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidAge() {
        new Learner(HJSS, "testLearnerConstructorValidData", "male", Globals.minAge - 1, "test@mail.com", "1234567898", HJSS.getGradeByNumber(0));
    }

    @Test
    public void testIncreaseGrade() {
        Learner learner = new Learner(HJSS, "testLearnerConstructorValidData", "male", 10, "test@mail.com", "1234567898", HJSS.getGradeByNumber(0));
        learner.increaseGrade(HJSS.getGrades());
        assertEquals("Grade should be increased to the next level", learner.getGrade(), HJSS.getGradeByNumber(1));
    }


}
