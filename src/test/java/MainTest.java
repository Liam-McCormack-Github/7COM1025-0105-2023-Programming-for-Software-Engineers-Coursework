import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class MainTest {
    private final InputStream originalIn = System.in;

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Before
    public void setUp() {
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(originalIn);
    }


    // @Test(expected = NoSuchElementException.class)


    @Test
    public void testUserInputSequence_() {
        provideInput("1\n999\n");
    }

    @Test
    public void testUserInput_exampleApproach() {
        String createNewLearner = String.format("Tester%nMale%n%d%ntester@test.com%n123456789%n%d%n", Globals.minAge, Globals.minGrade);
        String inputString = "";

        inputString += String.format("1%n"); // Select '1: Create a new learner'
        inputString += createNewLearner; // Fill in details for new Learner

        // Book Lesson #1, Grade 1
        inputString += String.format("3%n"); // Select '3: Select a lesson'
        inputString += String.format("2%n"); // Select '2: Select lesson by Grade'
        inputString += String.format("1%n"); // Select '1: Grade 1'
        inputString += String.format("1%n"); // Select '1: Lesson: #1'
        inputString += String.format("4%n"); // Select '4: Create a new booking for the selected learner at the selected lesson'

        // Book Lesson #21, Grade 2
        inputString += String.format("3%n"); // Select '3: Select a lesson'
        inputString += String.format("2%n"); // Select '2: Select lesson by Grade'
        inputString += String.format("2%n"); // Select '2: Grade 2'
        inputString += String.format("11%n"); // Select '11: Lesson: #21'
        inputString += String.format("4%n"); // Select '4: Create a new booking for the selected learner at the selected lesson'

        // Book Lesson #17, Grade 3 - Should fail
        inputString += String.format("3%n"); // Select '3: Select a lesson'
        inputString += String.format("2%n"); // Select '2: Select lesson by Grade'
        inputString += String.format("3%n"); // Select '3: Grade 3'
        inputString += String.format("2%n"); // Select '2: Lesson: #17'
        inputString += String.format("4%n"); // Select '4: Create a new booking for the selected learner at the selected lesson'

        // Cancel Lesson #21, Grade 2
        inputString += String.format("3%n"); // Select '3: Select a lesson'
        inputString += String.format("2%n"); // Select '2: Select lesson by Grade'
        inputString += String.format("2%n"); // Select '2: Grade 2'
        inputString += String.format("11%n"); // Select '11: Lesson: #21'
        inputString += String.format("5%n"); // Select '5: Cancel the booking for the selected learner at the selected lesson'

        // Cancel Lesson #6, Grade 2
        inputString += String.format("3%n"); // Select '3: Select a lesson'
        inputString += String.format("2%n"); // Select '2: Select lesson by Grade'
        inputString += String.format("2%n"); // Select '2: Grade 2'
        inputString += String.format("2%n"); // Select '2: Lesson: #6'
        inputString += String.format("4%n"); // Select '4: Create a new booking for the selected learner at the selected lesson'

        // Simulate next Lesson #1, Grade 1
        inputString += String.format("6%n"); // Select '6: Simulate lessons (Until: next user lesson || end)'
        inputString += String.format("4%n"); // Enter rating (1-5):
        inputString += String.format("Good lesson, learned a lot%n"); // Enter comment (str):

        // Simulate next Lesson #6, Grade 2
        inputString += String.format("6%n"); // Select '6: Simulate lessons (Until: next user lesson || end)'
        inputString += String.format("5%n"); // Enter rating (1-5):
        inputString += String.format("Great lesson, learned a lot%n"); // Enter comment (str):

        // Book Lesson #17, Grade 3
        inputString += String.format("3%n"); // Select '3: Select a lesson'
        inputString += String.format("2%n"); // Select '2: Select lesson by Grade'
        inputString += String.format("3%n"); // Select '3: Grade 3'
        inputString += String.format("2%n"); // Select '2: Lesson: #17'
        inputString += String.format("4%n"); // Select '4: Create a new booking for the selected learner at the selected lesson'

        // Book Lesson #28, Grade 4
        inputString += String.format("3%n"); // Select '3: Select a lesson'
        inputString += String.format("2%n"); // Select '2: Select lesson by Grade'
        inputString += String.format("4%n"); // Select '4: Grade 4'
        inputString += String.format("2%n"); // Select '2: Lesson: #28'
        inputString += String.format("4%n"); // Select '4: Create a new booking for the selected learner at the selected lesson'

        // Book Lesson #39, Grade 5
        inputString += String.format("3%n"); // Select '3: Select a lesson'
        inputString += String.format("2%n"); // Select '2: Select lesson by Grade'
        inputString += String.format("5%n"); // Select '5: Grade 5'
        inputString += String.format("2%n"); // Select '2: Lesson: #39'
        inputString += String.format("4%n"); // Select '4: Create a new booking for the selected learner at the selected lesson'

        // Cancel Lesson #28, Grade 4 - Should cancel Lesson #39 as well
        inputString += String.format("3%n"); // Select '3: Select a lesson'
        inputString += String.format("2%n"); // Select '2: Select lesson by Grade'
        inputString += String.format("4%n"); // Select '4: Grade 4'
        inputString += String.format("2%n"); // Select '2: Lesson: #28'
        inputString += String.format("5%n"); // Select '5: Cancel the booking for the selected learner at the selected lesson'

        // Simulate next Lesson #17, Grade 3
        inputString += String.format("6%n"); // Select '6: Simulate lessons (Until: next user lesson || end)'
        inputString += String.format("5%n"); // Enter rating (1-5):
        inputString += String.format("Great lesson, learned a lot%n"); // Enter comment (str):

        // Simulate next Lessons
        inputString += String.format("6%n"); // Select '6: Simulate lessons (Until: next user lesson || end)'

        provideInput(inputString);

        /*
        Learner: Tester,	Final grade level: 3,	Age: 4
            Booked Lessons: 6
                Lesson: #1	(w1	l1) 	Grade: 1 	Coach: Helen 	Time: Monday (16:00->17:00)
                Lesson: #21	(w2	l10) 	Grade: 2 	Coach: Harry 	Time: Saturday (14:00->15:00)
                Lesson: #6	(w1	l6) 	Grade: 2 	Coach: Helena 	Time: Wednesday (18:00->19:00)
                Lesson: #17	(w2	l6) 	Grade: 3 	Coach: Helena 	Time: Wednesday (18:00->19:00)
                Lesson: #28	(w3	l6) 	Grade: 4 	Coach: Helena 	Time: Wednesday (18:00->19:00)
                Lesson: #39	(w4	l6) 	Grade: 5 	Coach: Helena 	Time: Wednesday (18:00->19:00)
            Cancelled Lessons: 3
                Lesson: #21	(w2	l10) 	Grade: 2 	Coach: Harry 	Time: Saturday (14:00->15:00)
                Lesson: #28	(w3	l6) 	Grade: 4 	Coach: Helena 	Time: Wednesday (18:00->19:00)
                Lesson: #39	(w4	l6) 	Grade: 5 	Coach: Helena 	Time: Wednesday (18:00->19:00)
            Attended Lessons: 3
                Lesson: #1	(w1	l1) 	Grade: 1 	Coach: Helen 	Time: Monday (16:00->17:00)	Learner progressed to grade level 1
                Lesson: #6	(w1	l6) 	Grade: 2 	Coach: Helena 	Time: Wednesday (18:00->19:00)	Learner progressed to grade level 2
                Lesson: #17	(w2	l6) 	Grade: 3 	Coach: Helena 	Time: Wednesday (18:00->19:00)	Learner progressed to grade level 3
        */

        HatfieldJuniorSwimmingSchool HJSS = new HatfieldJuniorSwimmingSchool();
        HJSS.preInit();
        HJSS.init();
        HJSS.run();
    }
}
