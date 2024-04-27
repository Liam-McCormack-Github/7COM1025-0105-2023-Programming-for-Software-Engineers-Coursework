package core;

import common.Grade;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Globals {
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
    public static final String[] daysOfTheWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    public static final int exitCode = 999;
    public static final int minAge = 4;
    public static final int maxAge = 11;
    public static final int minGrade = 0;
    public static final int maxGrade = 5;
    public static final int maxLessonSize = 5;
    public static final int minRating = 1;
    public static final int maxRating = 5;
    // additional constraints
    public static final int minCommentSize = 3;
    public static final int maxCommentSize = 256;
    public static final int minContactSize = 3;
    public static final int maxContactSize = 256;
    public static final int minGenderSize = 2;
    public static final int maxGenderSize = 32;
    public static final int minNameSize = 2;
    public static final int maxNameSize = 64;
    public static final int minPhoneNumberSize = 3;
    public static final int maxPhoneNumberSize = 16;


    // String names
    public static final String createLearner = "Create a new learner";
    public static final String selectLearner = "Select a learner";
    public static final String selectLesson = "Select a lesson";
    public static final String bookSelectedLesson = "Create a new booking for the selected learner at the selected lesson";
    public static final String cancelNextLesson = "Cancel the booking for the selected learner at the selected lesson";
    public static final String simulateUntilNextLesson = "Simulate lessons (Until: next user lesson || end)";
    public static final String menuNameMainMenu = "Main Menu";
    public static final String menuNameSelectLearner = "Select Learner";
    public static final String menuNameSelectLessonByDay = "Select lesson by Day";
    public static final String menuNameSelectLessonByGrade = "Select lesson by Grade";
    public static final String menuNameSelectLessonByCoach = "Select lesson by Coach";
    public static final String menuNameSelectLessonByLearner = "Select lesson by Learner";
    public static final String menuNameSelectLessonByLessonNumber = "Select lesson by Lesson Number";
    public static final String menuNameSelectLesson = "Select Lesson";


    // https://en.wikipedia.org/wiki/ANSI_escape_code#Colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

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
    public static NavigableSet<Grade> gradesTree = new TreeSet<>();
    public static Map<Integer, Grade> gradeMap = new HashMap<>();
    public static Map<Integer, Grade> gradeLevelMap = new HashMap<>();

    private Globals() {
        // Private constructor to prevent instantiation
    }

    public static void generateGlobalGradeTreeSet(NavigableSet<Grade> grades) {
        gradesTree.addAll(grades);
    }

    public static void generateGlobalGradePositionMap(NavigableSet<Grade> grades) {
        int position = 1;
        for (Grade grade : grades) {
            gradeMap.put(position++, grade);
        }
    }

    public static void generateGlobalGradeLevelMap(NavigableSet<Grade> grades) {
        for (Grade grade : grades) {
            gradeLevelMap.put(grade.getLevel(), grade);
        }
    }

    public static void resetStaticTrees() {
        gradesTree.clear();
        gradeMap.clear();
        gradeLevelMap.clear();
    }

    public static Grade getGradeByLevel(int level) {
        return gradeLevelMap.get(level);
    }
}
