package utils.validators.input;

import org.junit.Test;
import utils.Utils;

import static org.junit.Assert.*;

public class ValidateContactTest {

    @Test
    public void testValidateWithValidString() {
        String validInput = "Hello";
        Utils.ValidationResult<String> result = ValidateContact.validate(validInput);
        assertTrue("The string should be considered valid", result.isValid);
        assertEquals("The value should be the input string", validInput, result.getValue());
        assertNull("There should be no error message", result.getErrorMessage());
    }

    @Test
    public void testValidateWithSingleCharacterString() {
        Utils.ValidationResult<String> result = ValidateContact.validate("a");
        assertFalse("A single-character string should be invalid", result.isValid);
        assertNull("There should be no value for invalid input", result.getValue());
        assertEquals("Error message should match expected output",
                "Invalid Input: 'a' should be between 3 and 256 characters long", result.getErrorMessage());
    }

    @Test
    public void testValidateWithEmptyString() {
        Utils.ValidationResult<String> result = ValidateContact.validate("");
        assertFalse("An empty string should be invalid", result.isValid);
        assertNull("There should be no value for invalid input", result.getValue());
        assertEquals("Error message should match expected output","Invalid Input: '' should be between 3 and 256 characters long", result.getErrorMessage());
    }

    @Test
    public void testValidateWithNullInput() {
        Utils.ValidationResult<String> result = ValidateContact.validate(null);
        assertFalse("Null should be considered invalid", result.isValid);
        assertNull("There should be no value for invalid input", result.getValue());
        assertEquals("Error message should match expected output","Invalid Input: 'null' should be between 3 and 256 characters long", result.getErrorMessage());
    }
}
