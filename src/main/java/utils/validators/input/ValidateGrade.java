package utils.validators.input;

import core.Globals;
import utils.Utils;

public class ValidateGrade {


    public static Utils.ValidationResult<Integer> validate(String input) {
        try {
            int grade = Integer.parseInt(input);
            if (Globals.minGrade <= grade && grade <= Globals.maxGrade) {
                return new Utils.ValidationResult<>(true, grade, null);
            }
        } catch (NumberFormatException ignored) {
        }
        return new Utils.ValidationResult<>(false, null, String.format("Invalid Input: '%s' should be an integer between %s and %s", input, Globals.minGrade, Globals.maxGrade));
    }

}
