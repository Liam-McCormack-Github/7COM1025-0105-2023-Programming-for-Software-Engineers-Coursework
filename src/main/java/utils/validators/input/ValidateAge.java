package utils.validators.input;

import core.Globals;
import utils.Utils;

public class ValidateAge {

    public static Utils.ValidationResult<Integer> validate(String input) {
        try {
            int age = Integer.parseInt(input);
            if (Globals.minAge <= age && age <= Globals.maxAge) {
                return new Utils.ValidationResult<>(true, age, null);
            }
        } catch (NumberFormatException ignored) {
        }
        return new Utils.ValidationResult<>(false, null, String.format("Invalid Input: '%s' should be an integer between %s and %s", input, Globals.minAge, Globals.maxAge));
    }
}
