package utils.validators.input;

import core.Globals;
import utils.Utils;

public class ValidateGender {
    public static Utils.ValidationResult<String> validate(String input) {
        if (input != null && input.length() > Globals.minGenderSize && input.length() < Globals.maxGenderSize) {
            return new Utils.ValidationResult<>(true, input, null);
        }
        return new Utils.ValidationResult<>(false, null, String.format("Invalid Input: '%s' should be between %s and %s characters long", input, Globals.minGenderSize, Globals.maxGenderSize));
    }
}
