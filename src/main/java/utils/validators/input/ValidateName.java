package utils.validators.input;

import utils.Utils;

public class ValidateName {
    // Validation functions
    public static Utils.ValidationResult<String> validate(String input) {
        if (input != null && input.length() > 1 && input.length() < 64) {
            return new Utils.ValidationResult<>(true, input, null);
        }
        return new Utils.ValidationResult<>(false, null, String.format("Invalid Input: '%s' should be longer than 1 character and less than 64", input));
    }
}