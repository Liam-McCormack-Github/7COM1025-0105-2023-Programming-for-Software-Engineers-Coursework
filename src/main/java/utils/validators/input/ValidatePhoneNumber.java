package utils.validators.input;

import utils.Utils;

public class ValidatePhoneNumber {

    public static Utils.ValidationResult<String> validate(String input) {
        if (input != null && input.length() >= 8) {
            return new Utils.ValidationResult<>(true, input, null);
        }
        return new Utils.ValidationResult<>(false, null, String.format("Invalid Input: '%s' should be at least 8 digits", input));

    }


}
