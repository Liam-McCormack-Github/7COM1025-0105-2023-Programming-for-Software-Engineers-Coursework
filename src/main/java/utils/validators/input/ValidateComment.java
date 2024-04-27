package utils.validators.input;

import core.Globals;
import utils.Utils;

public class ValidateComment {

    public static Utils.ValidationResult<String> validate(String input) {
        if (input != null && input.length() >Globals.minCommentSize && input.length() < Globals.maxCommentSize) {
            return new Utils.ValidationResult<>(true, input, null);
        }
        return new Utils.ValidationResult<>(false, null, String.format("Invalid Input: '%s' should be between %s and %s characters long", input, Globals.minCommentSize, Globals.maxCommentSize));
    }
}
