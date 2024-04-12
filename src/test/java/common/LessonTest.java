package common;

import core.HatfieldJuniorSwimmingSchool;
import core.Seeder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LessonTest {
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
    public void testConstructor() {

        Grade grade = HJSS.getGradeByNumber(1);
        Coach coach = HJSS.getCoachByNumber(1);

        Lesson lesson = new Lesson(HJSS, grade, coach);

        assertNotNull("Lesson should not be null", lesson);
        assertEquals("Lesson should match", grade, lesson.getGrade());
        assertEquals("Learner should match", coach, lesson.getCoach());
        assertFalse("Finished should not be complete initially", lesson.isFinished());

        assertTrue("Learner should be added to HJSS learners list", HJSS.getLessons().contains(lesson));
    }

    @Test
    public void testGettersAndSetter() {
        Grade grade = HJSS.getGradeByNumber(1);
        Coach coach = HJSS.getCoachByNumber(1);
        Lesson lesson = new Lesson(HJSS, grade, coach);

        assertNotNull("Lesson Title should not be null", lesson.getLessonTitle());
        assertNotNull("Lesson ID should not be null", lesson.getId());
        assertNotNull("Day should not be null", lesson.getDay());
        assertNotEquals("Week should not be 0", 0, lesson.getWeek());
        assertNotEquals("Lesson Number Week should not be 0", 0, lesson.getLessonNumberWeek());
        Grade newGrade = HJSS.getGradeByNumber(2);
        Coach newCoach = HJSS.getCoachByNumber(2);
        lesson.setGrade(newGrade);
        lesson.setCoach(newCoach);
        assertEquals("Grade should match", newGrade, lesson.getGrade());
        assertEquals("Lesson should match", newCoach, lesson.getCoach());
    }


    @Test
    public void testLessonNumberIncrement() {
        Grade grade = HJSS.getGradeByNumber(1);
        Coach coach = HJSS.getCoachByNumber(1);
        Lesson firstLesson = new Lesson(HJSS, grade, coach);
        Lesson secondLesson = new Lesson(HJSS, grade, coach);

        assertTrue("Lesson number should increment", secondLesson.getLessonNumber() > firstLesson.getLessonNumber());
    }


    @Test
    public void testAddLearnerToLesson() {
        Grade grade = HJSS.getGradeByNumber(1);
        Coach coach = HJSS.getCoachByNumber(1);
        Lesson lesson = new Lesson(HJSS, grade, coach);

        Learner learner = new Learner(HJSS, "testBookingCreation", "male", 10, "test@mail.com", "1234567898", HJSS.getGradeByNumber(0));

        lesson.getLearners().add(learner);

        assertTrue("Lesson should contain added learner", lesson.getLearners().contains(learner));
    }


    @Test
    public void testLessonCompletion() {
        Grade grade = HJSS.getGradeByNumber(1);
        Coach coach = HJSS.getCoachByNumber(1);
        Lesson lesson = new Lesson(HJSS, grade, coach);

        lesson.setFinished(true);

        assertTrue("Lesson should be marked as finished", lesson.isFinished());
    }

}
