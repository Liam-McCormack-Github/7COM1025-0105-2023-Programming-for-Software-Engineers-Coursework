package utils.validators.input;

import org.junit.Test;
import utils.Utils;

import static org.junit.Assert.*;

public class ValidatePhoneNumberTest {
    @Test
    public void testValidateWithValidInput() {
        String input = "12345678";
        Utils.ValidationResult<String> result = ValidatePhoneNumber.validate(input);
        assertTrue("Expected valid result for input: " + input, result.isValid);
        assertEquals("Expected value to match input", input, result.getValue());
        assertNull("Expected no error message for valid input", result.getErrorMessage());
    }

    @Test
    public void testValidateWithLongerValidInput() {
        String input = "123456789";
        Utils.ValidationResult<String> result = ValidatePhoneNumber.validate(input);
        assertTrue("Expected valid result for longer input", result.isValid);
        assertEquals("Expected value to match input", input, result.getValue());
        assertNull("Expected no error message for valid input", result.getErrorMessage());
    }

    @Test
    public void testValidateWithNullInput() {
        Utils.ValidationResult<String> result = ValidatePhoneNumber.validate(null);
        assertFalse("Expected invalid result for null input", result.isValid);
        assertNull("Expected no value for invalid input", result.getValue());
        assertNotNull("Expected an error message for null input", result.getErrorMessage());
    }

    @Test
    public void testValidateWithShortInput() {
        String input = "1234567";
        Utils.ValidationResult<String> result = ValidatePhoneNumber.validate(input);
        assertFalse("Expected invalid result for short input", result.isValid);
        assertNull("Expected no value for invalid input", result.getValue());
        assertNotNull("Expected an error message for short input", result.getErrorMessage());
    }
}
