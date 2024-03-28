package utils;


import core.Globals;
import core.HatfieldJuniorSwimmingSchool;

import java.util.function.BiFunction;
import java.util.function.Function;

public class UserInputValidator extends Utils {

    // Generic input validation method
    public static <T> T validateInput(String prompt,
                                      Function<String, ValidationResult<T>> validationFunction,
                                      HatfieldJuniorSwimmingSchool HJSS) {
        // Adapter to make Function fit into BiFunction interface
        BiFunction<HatfieldJuniorSwimmingSchool, String, ValidationResult<T>> biFunctionAdapter = (hjss, input) -> validationFunction.apply(input);
        return validateInput(prompt, biFunctionAdapter, HJSS);
    }

    public static <T> T validateInput(String prompt,
                                      BiFunction<HatfieldJuniorSwimmingSchool, String, ValidationResult<T>> validationBiFunction,
                                      HatfieldJuniorSwimmingSchool HJSS) {
        while (true) {
            try {

                Utils.printInputPrompt(prompt);
                String userInput = HJSS.getScanner().nextLine();
                Utils.printSeparator(Globals.ANSI_BLUE);

                ValidationResult<T> result = validationBiFunction.apply(HJSS, userInput);
                if (result.isValid()) {
                    return result.getValue();
                } else {
                    throw new IllegalArgumentException(result.getErrorMessage());
                }
            } catch (IllegalArgumentException e) {
                Utils.printErrorMessage(e.getMessage());
            }
        }
    }
}