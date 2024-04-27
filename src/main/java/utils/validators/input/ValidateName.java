package utils.validators.input;

import core.Globals;
import utils.Utils;

public class ValidateName {
    // Validation functions
    public static Utils.ValidationResult<String> validate(String input) {
        if (input != null && input.length() > Globals.minNameSize && input.length() < Globals.maxNameSize) {
            return new Utils.ValidationResult<>(true, input, null);
        }
        return new Utils.ValidationResult<>(false, null, String.format("Invalid Input: '%s' should be between %s and %s characters long", input, Globals.minNameSize, Globals.maxNameSize));
    }
}