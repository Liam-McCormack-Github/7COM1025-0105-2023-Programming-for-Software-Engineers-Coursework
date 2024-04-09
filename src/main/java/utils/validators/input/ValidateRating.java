package utils.validators.input;

import core.Globals;
import utils.Utils;

public class ValidateRating {

    public static Utils.ValidationResult<Integer> validate(String input) {
        try {
            int rating = Integer.parseInt(input);
            if (Globals.minRating <= rating && rating <= Globals.maxRating) {
                return new Utils.ValidationResult<>(true, rating, null);
            }
        } catch (NumberFormatException ignored) {
        }
        return new Utils.ValidationResult<>(false, null, String.format("Invalid Input: '%s' should be an integer between %s and %s", input, Globals.minRating, Globals.maxRating));
    }
}
