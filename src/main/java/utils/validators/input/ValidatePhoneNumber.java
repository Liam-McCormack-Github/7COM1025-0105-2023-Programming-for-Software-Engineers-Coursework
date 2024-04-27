package utils.validators.input;

import core.Globals;
import utils.Utils;

public class ValidatePhoneNumber {

    public static Utils.ValidationResult<String> validate(String input) {
        if (input != null && input.length() >= Globals.minPhoneNumberSize && input.length() < Globals.maxPhoneNumberSize) {
            return new Utils.ValidationResult<>(true, input, null);
        }
        return new Utils.ValidationResult<>(false, null, String.format("Invalid Input: '%s' should be between %s and %s characters long", input, Globals.minPhoneNumberSize, Globals.maxPhoneNumberSize));
    }


}
