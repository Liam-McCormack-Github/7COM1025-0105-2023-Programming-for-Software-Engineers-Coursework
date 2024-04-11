package utils.validators.input;

import core.Globals;
import org.junit.Test;
import utils.Utils;

import static org.junit.Assert.*;

public class ValidateAgeTest {
    @Test
    public void testValidateWithValidAge() {

        int testAge = (Globals.minAge + Globals.maxAge) / 2;
        Utils.ValidationResult<Integer> result = ValidateAge.validate(String.valueOf(testAge));
        assertTrue(result.isValid);
        assertEquals(Integer.valueOf(testAge), result.getValue());
        assertNull(result.getErrorMessage());
    }

    @Test
    public void testValidateWithAgeBelowMinimum() {
        Utils.ValidationResult<Integer> result = ValidateAge.validate(String.valueOf(Globals.minAge - 1));
        assertFalse(result.isValid);
        assertNull(result.getValue());
        assertNotNull(result.getErrorMessage());
    }

    @Test
    public void testValidateWithAgeAboveMaximum() {
        Utils.ValidationResult<Integer> result = ValidateAge.validate(String.valueOf(Globals.maxAge + 1));
        assertFalse(result.isValid);
        assertNull(result.getValue());
        assertNotNull(result.getErrorMessage());
    }

    @Test
    public void testValidateWithNonIntegerInput() {
        Utils.ValidationResult<Integer> result = ValidateAge.validate("abc");
        assertFalse(result.isValid);
        assertNull(result.getValue());
        assertNotNull(result.getErrorMessage());
    }

    @Test
    public void testValidateWithEmptyString() {
        Utils.ValidationResult<Integer> result = ValidateAge.validate("");
        assertFalse(result.isValid);
        assertNull(result.getValue());
        assertNotNull(result.getErrorMessage());
    }
}
