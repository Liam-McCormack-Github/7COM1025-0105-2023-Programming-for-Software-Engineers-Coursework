package utils.validators.input;

import core.Globals;
import org.junit.Test;
import utils.Utils;

import static org.junit.Assert.*;

public class ValidateRatingTest {

    @Test
    public void testValidateWithValidRating() {
        int validRating = (Globals.minRating + Globals.maxRating) / 2; // A rating in the middle of the range
        Utils.ValidationResult<Integer> result = ValidateRating.validate(String.valueOf(validRating));
        assertTrue("Expected valid result for rating: " + validRating, result.isValid);
        assertEquals("Expected value to match input rating", Integer.valueOf(validRating), result.getValue());
        assertNull("Expected no error message for valid rating", result.getErrorMessage());
    }

    @Test
    public void testValidateRatingBelowMinimum() {
        int belowMinRating = Globals.minRating - 1;
        Utils.ValidationResult<Integer> result = ValidateRating.validate(String.valueOf(belowMinRating));
        assertFalse("Expected invalid result for rating below minimum", result.isValid);
        assertNull("Expected no value for invalid rating", result.getValue());
        assertNotNull("Expected an error message for rating below minimum", result.getErrorMessage());
    }

    @Test
    public void testValidateRatingAboveMaximum() {
        int aboveMaxRating = Globals.maxRating + 1;
        Utils.ValidationResult<Integer> result = ValidateRating.validate(String.valueOf(aboveMaxRating));
        assertFalse("Expected invalid result for rating above maximum", result.isValid);
        assertNull("Expected no value for invalid rating", result.getValue());
        assertNotNull("Expected an error message for rating above maximum", result.getErrorMessage());
    }

    @Test
    public void testValidateWithNonIntegerInput() {
        Utils.ValidationResult<Integer> result = ValidateRating.validate("notAnInteger");
        assertFalse("Expected invalid result for non-integer input", result.isValid);
        assertNull("Expected no value for non-integer input", result.getValue());
        assertNotNull("Expected an error message for non-integer input", result.getErrorMessage());
    }

    @Test
    public void testValidateWithMinimumRating() {
        Utils.ValidationResult<Integer> result = ValidateRating.validate(String.valueOf(Globals.minRating));
        assertTrue("Expected valid result for minimum rating", result.isValid);
    }

    @Test
    public void testValidateWithMaximumRating() {
        Utils.ValidationResult<Integer> result = ValidateRating.validate(String.valueOf(Globals.maxRating));
        assertTrue("Expected valid result for maximum rating", result.isValid);
    }
}
