package utils.validators.others;

import core.Globals;
import utils.Utils;

public class ValidateDay {

    public static Utils.ValidationResult<String> validate(String input) {
        try {
            int dayOfTheWeek = Integer.parseInt(input);
            if (1 <= dayOfTheWeek && dayOfTheWeek <= 7) {
                return new Utils.ValidationResult<>(true, Globals.daysOfTheWeek[dayOfTheWeek - 1], null);
            }
        } catch (NumberFormatException ignored) {
        }
        return new Utils.ValidationResult<>(false, null, String.format("Invalid Input: '%s' should be an integer between 1 and 7", input));

    }
}
