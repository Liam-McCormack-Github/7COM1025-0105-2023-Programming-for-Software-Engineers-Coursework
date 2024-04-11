package utils.validators.input;

import core.Globals;
import org.junit.Test;
import utils.Utils;

import static org.junit.Assert.*;

public class ValidateGradeTest {
    @Test
    public void testValidateWithValidGrade() {
        int testGrade = (Globals.minGrade + Globals.maxGrade) / 2;
        Utils.ValidationResult<Integer> result = ValidateGrade.validate(String.valueOf(testGrade));
        assertTrue("Expected valid result for grade: " + testGrade, result.isValid);
        assertEquals("Expected value to match input grade", Integer.valueOf(testGrade), result.getValue());
        assertNull("Expected no error message for valid grade", result.getErrorMessage());
    }

    @Test
    public void testValidateGradeBelowMinimum() {
        int testGrade = Globals.minGrade - 1;
        Utils.ValidationResult<Integer> result = ValidateGrade.validate(String.valueOf(testGrade));
        assertFalse("Expected invalid result for grade below minimum", result.isValid);
        assertNull("Expected no value for invalid grade", result.getValue());
        assertNotNull("Expected an error message for grade below minimum", result.getErrorMessage());
    }

    @Test
    public void testValidateGradeAboveMaximum() {
        int testGrade = Globals.maxGrade + 1;
        Utils.ValidationResult<Integer> result = ValidateGrade.validate(String.valueOf(testGrade));
        assertFalse("Expected invalid result for grade above maximum", result.isValid);
        assertNull("Expected no value for invalid grade", result.getValue());
        assertNotNull("Expected an error message for grade above maximum", result.getErrorMessage());
    }

    @Test
    public void testValidateWithNonIntegerInput() {
        Utils.ValidationResult<Integer> result = ValidateGrade.validate("abc");
        assertFalse("Expected invalid result for non-integer input", result.isValid);
        assertNull("Expected no value for non-integer input", result.getValue());
        assertNotNull("Expected an error message for non-integer input", result.getErrorMessage());
    }


    @Test
    public void testValidateWithMinimumGrade() {
        Utils.ValidationResult<Integer> result = ValidateGrade.validate(String.valueOf(Globals.minGrade));
        assertTrue("Expected valid result for minimum grade", result.isValid);
    }

    @Test
    public void testValidateWithMaximumGrade() {
        Utils.ValidationResult<Integer> result = ValidateGrade.validate(String.valueOf(Globals.maxGrade));
        assertTrue("Expected valid result for maximum grade", result.isValid);
    }
}
