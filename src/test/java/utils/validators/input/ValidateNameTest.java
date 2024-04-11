package utils.validators.input;

import org.junit.Test;
import utils.Utils;

import static org.junit.Assert.*;

public class ValidateNameTest {
    @Test
    public void testValidateWithValidInput() {
        String input = "Hello";
        Utils.ValidationResult<String> result = ValidateName.validate(input);
        assertTrue("Expected valid result for input: " + input, result.isValid);
        assertEquals("Expected value to match input", input, result.getValue());
        assertNull("Expected no error message for valid input", result.getErrorMessage());
    }

    @Test
    public void testValidateWithNullInput() {
        Utils.ValidationResult<String> result = ValidateName.validate(null);
        assertFalse("Expected invalid result for null input", result.isValid);
        assertNull("Expected no value for invalid input", result.getValue());
        assertNotNull("Expected an error message for null input", result.getErrorMessage());
    }

    @Test
    public void testValidateWithEmptyString() {
        Utils.ValidationResult<String> result = ValidateName.validate("");
        assertFalse("Expected invalid result for empty string", result.isValid);
        assertNull("Expected no value for invalid input", result.getValue());
        assertNotNull("Expected an error message for empty string", result.getErrorMessage());
    }

    @Test
    public void testValidateWithSingleCharacterString() {
        Utils.ValidationResult<String> result = ValidateName.validate("a");
        assertFalse("Expected invalid result for single character string", result.isValid);
        assertNull("Expected no value for invalid input", result.getValue());
        assertNotNull("Expected an error message for single character string", result.getErrorMessage());
    }
}
