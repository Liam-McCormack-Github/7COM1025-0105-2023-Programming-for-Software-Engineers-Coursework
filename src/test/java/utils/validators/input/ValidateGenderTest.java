package utils.validators.input;

import org.junit.Test;
import utils.Utils;

import static org.junit.Assert.*;

public class ValidateGenderTest {
    @Test
    public void testValidateWithValidInput() {
        String testString = "Test";
        Utils.ValidationResult<String> result = ValidateGender.validate(testString);
        assertTrue("Expected valid result for input: " + testString, result.isValid);
        assertEquals("Expected value to match input", testString, result.getValue());
        assertNull("Expected no error message for valid input", result.getErrorMessage());
    }

    @Test
    public void testValidateWithNullInput() {
        Utils.ValidationResult<String> result = ValidateGender.validate(null);
        assertFalse("Expected invalid result for null input", result.isValid);
        assertNull("Expected no value for invalid input", result.getValue());
        assertNotNull("Expected an error message for null input", result.getErrorMessage());
    }

    @Test
    public void testValidateWithEmptyString() {
        Utils.ValidationResult<String> result = ValidateGender.validate("");
        assertFalse("Expected invalid result for empty string", result.isValid);
        assertNull("Expected no value for invalid input", result.getValue());
        assertNotNull("Expected an error message for empty string", result.getErrorMessage());
    }

    @Test
    public void testValidateWithSingleCharacterString() {
        Utils.ValidationResult<String> result = ValidateGender.validate("a");
        assertFalse("Expected invalid result for single character string", result.isValid);
        assertNull("Expected no value for invalid input", result.getValue());
        assertNotNull("Expected an error message for single character string", result.getErrorMessage());
    }

    @Test
    public void testErrorMessageFormat() {
        String testInput = "a";
        Utils.ValidationResult<String> result = ValidateGender.validate(testInput);
        String expectedMessage = String.format("Invalid Input: '%s' should be between 2 and 32 characters long", testInput);
        assertEquals("Error message format mismatch", expectedMessage, result.getErrorMessage());
    }
}
