package utils.validators.input;

import utils.Utils;

public class ValidateGender {
    public static Utils.ValidationResult<String> validate(String input) {
        if (input != null && input.length() > 2 && input.length() < 16) {
            return new Utils.ValidationResult<>(true, input, null);
        }
        return new Utils.ValidationResult<>(false, null, String.format("Invalid Input: '%s' should be longer than 1 character", input));
    }
}
