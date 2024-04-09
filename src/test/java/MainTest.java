import core.Globals;
import core.HatfieldJuniorSwimmingSchool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;


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

    @Test(expected = NoSuchElementException.class)
    public void testUserInputSequence_() {
        provideInput("1\n999\n");
    }

    @Test(expected = NoSuchElementException.class)
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

        provideInput(inputString);

        HatfieldJuniorSwimmingSchool HJSS = new HatfieldJuniorSwimmingSchool();
        HJSS.preInit();
        HJSS.init();
        HJSS.run();
    }
}
