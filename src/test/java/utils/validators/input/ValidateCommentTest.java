package utils.validators.input;

import org.junit.Test;
import utils.Utils;

import static org.junit.Assert.*;

public class ValidateCommentTest {

    @Test
    public void testValidateWithValidString() {
        String validInput = "Hello";
        Utils.ValidationResult<String> result = ValidateComment.validate(validInput);
        assertTrue(result.isValid);
        assertEquals(validInput, result.getValue());
        assertNull(result.getErrorMessage());
    }

    @Test
    public void testValidateWithSingleCharacterString() {
        Utils.ValidationResult<String> result = ValidateComment.validate("a");
        assertFalse(result.isValid);
        assertNull(result.getValue());
        assertEquals("Invalid Input: 'a' should be longer than 1 character", result.getErrorMessage());
    }

    @Test
    public void testValidateWithEmptyString() {
        Utils.ValidationResult<String> result = ValidateComment.validate("");
        assertFalse(result.isValid);
        assertNull(result.getValue());
        assertEquals("Invalid Input: '' should be longer than 1 character", result.getErrorMessage());
    }

    @Test
    public void testValidateWithNullInput() {
        Utils.ValidationResult<String> result = ValidateComment.validate(null);
        assertFalse(result.isValid);
        assertNull(result.getValue());
        assertEquals("Invalid Input: 'null' should be longer than 1 character", result.getErrorMessage());
    }
}
