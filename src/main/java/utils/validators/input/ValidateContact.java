package utils.validators.input;

import core.Globals;
import utils.Utils;

public class ValidateContact {
    public static Utils.ValidationResult<String> validate(String input) {
        if (input != null && input.length() > Globals.minContactSize && input.length() < Globals.maxContactSize) {
            return new Utils.ValidationResult<>(true, input, null);
        }
        return new Utils.ValidationResult<>(false, null, String.format("Invalid Input: '%s' should be between %s and %s characters long", input, Globals.minContactSize, Globals.maxContactSize));
    }
}
