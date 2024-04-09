package common;

import core.HatfieldJuniorSwimmingSchool;
import utils.UserInputValidator;
import utils.validators.input.ValidateName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Coach {
    private final UUID id;
    private final ArrayList<Lesson> lessons;
    private final ArrayList<Review> reviews;
    private String name;


    public Coach(HatfieldJuniorSwimmingSchool HJSS, String name) {
        this.id = UUID.randomUUID();
        this.setName(name);
        this.lessons = new ArrayList<>();
        this.reviews = new ArrayList<>();

        HJSS.getCoaches().add(this);
    }

    /*
     *   __  __ ______ _______ _    _  ____  _____   _____
     *  |  \/  |  ____|__   __| |  | |/ __ \|  __ \ / ____|
     *  | \  / | |__     | |  | |__| | |  | | |  | | (___
     *  | |\/| |  __|    | |  |  __  | |  | | |  | |\___ \
     *  | |  | | |____   | |  | |  | | |__| | |__| |____) |
     *  |_|  |_|______|  |_|  |_|  |_|\____/|_____/|_____/
     *
     * Methods
     */
    public void printReport() {
        // Assuming 'this.reviews' is a collection of Review objects available in the Coach class
        int totalRatings = this.reviews.size();
        double sumRatings = 0;
        Map<Integer, Integer> ratingCount = new HashMap<>(); // Keeps count of ratings 1 through 5

        // Initialize rating count map
        for (int i = 1; i <= 5; i++) {
            ratingCount.put(i, 0);
        }

        // Process each review to calculate sum of ratings and count each rating level
        for (Review review : this.reviews) {
            int rating = review.getRating();
            sumRatings += rating;

            // Increment rating count
            ratingCount.put(rating, ratingCount.get(rating) + 1);
        }

        // Calculate average rating
        double averageRating = totalRatings > 0 ? sumRatings / totalRatings : 0;

        // Print report
        System.out.printf("Coach: %s%n", this.name);
        System.out.printf("\tAverage Rating: %.2f%n", averageRating);
        System.out.printf("\tRated Lessons: %d%n", totalRatings);
        System.out.println("\tRatings Breakdown:");

        // Print rating breakdown
        for (int i = 5; i >= 1; i--) {
            String ratingDescription = "";
            switch (i) {
                case 5:
                    ratingDescription = "Very Satisfied";
                    break;
                case 4:
                    ratingDescription = "Satisfied";
                    break;
                case 3:
                    ratingDescription = "Ok";
                    break;
                case 2:
                    ratingDescription = "Dissatisfied";
                    break;
                case 1:
                    ratingDescription = "Very Dissatisfied";
                    break;
            }
            System.out.printf("\t* %d (%s): %d lessons%n", i, ratingDescription, ratingCount.get(i));
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Coach{UUID=%s,\tName=%s,\tLessons=[%d],\tReviews=[%d]}",
                this.id, this.name, this.lessons.size(), this.reviews.size()
        );
    }

    /*
     *   _____ ______ _______ _______ ______ _____   _____
     *  / ____|  ____|__   __|__   __|  ____|  __ \ / ____|
     * | |  __| |__     | |     | |  | |__  | |__) | (___
     * | | |_ |  __|    | |     | |  |  __| |  _  / \___ \
     * | |__| | |____   | |     | |  | |____| | \ \ ____) |
     *  \_____|______|  |_|     |_|  |______|_|  \_\_____/
     *
     * Getters
     */
    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    /*
     *   _____ ______ _______ _______ ______ _____   _____
     *  / ____|  ____|__   __|__   __|  ____|  __ \ / ____|
     * | (___ | |__     | |     | |  | |__  | |__) | (___
     *  \___ \|  __|    | |     | |  |  __| |  _  / \___ \
     *  ____) | |____   | |     | |  | |____| | \ \ ____) |
     * |_____/|______|  |_|     |_|  |______|_|  \_\_____/
     *
     * Setters
     */
    public void setName(String value) {
        UserInputValidator.ValidationResult<String> result = ValidateName.validate(String.valueOf(value));
        if (!result.isValid()) {
            throw new IllegalArgumentException(result.getErrorMessage());
        }
        this.name = result.getValue();
    }

    public ArrayList<Lesson> getLessons() {
        return this.lessons;
    }

    public ArrayList<Review> getReviews() {
        return this.reviews;
    }

}
