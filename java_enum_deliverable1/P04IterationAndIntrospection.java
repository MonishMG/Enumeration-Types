import java.lang.reflect.Method;

public class P04IterationAndIntrospection {
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
    }

    // Java does not allow duplicate enum constant names,
    // but multiple constants can still share the same custom field value.
    enum StatusCode {
        OK(1),
        SUCCESS(1),
        WARNING(2),
        ERROR(3);

        private final int code;

        StatusCode(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Program 4: Iteration and Introspection ===");
        System.out.println();

        // -------------------------------
        // Part 1: Built-in iteration
        // -------------------------------
        System.out.println("Built-in iteration over enum values:");
        System.out.println("Expected: Java should let us iterate directly over the enum using values().");

        for (Day day : Day.values()) {
            System.out.println(day.name() + " -> ordinal " + day.ordinal());
        }
        System.out.println("Actual: it works with very little code");
        System.out.println("Caught: not an error");
        System.out.println();

        // -------------------------------
        // Part 2: Introspection
        // -------------------------------
        // Java can inspect enum classes using reflection and built-in enum methods.
        System.out.println("Introspection in Java:");
        System.out.println("Expected: we can inspect methods and constants of an enum class.");

        Method[] methods = StatusCode.class.getDeclaredMethods();
        System.out.println("Declared methods in StatusCode:");
        for (Method method : methods) {
            System.out.println(" - " + method.getName());
        }
        System.out.println();

        // -------------------------------
        // Part 3: Beyond Normal Usage
        // -------------------------------
        System.out.println("Beyond Normal Usage: assign the same integer field to two enum constants");
        System.out.println("Expected: Java should allow duplicate custom field values,");
        System.out.println("but both enum constants should still appear separately during iteration.");
        System.out.println();

        System.out.println("Iterating over StatusCode:");
        for (StatusCode code : StatusCode.values()) {
            System.out.println(code.name() + " -> custom code " + code.getCode());
        }
        System.out.println();

        System.out.println("Checking whether OK and SUCCESS are the same enum constant:");
        System.out.println("StatusCode.OK == StatusCode.SUCCESS -> " + (StatusCode.OK == StatusCode.SUCCESS));
        System.out.println("Caught: not an error; Java treats them as different enum constants even if field values match");
        System.out.println();

        System.out.println("What appears during iteration?");
        System.out.println("Actual: both names appear because Java enum constants remain distinct.");
        System.out.println("This differs from Python alias behavior, where duplicate values can collapse into aliases.");
        System.out.println();

        // -------------------------------
        // Final takeaway
        // -------------------------------
        System.out.println("Conclusion:");
        System.out.println("Java has built-in support for enum iteration through values().");
        System.out.println("It also supports introspection, and duplicate custom field values do not merge enum constants.");
    }
}
