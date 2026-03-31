public class P01EnumerationBasics {
    enum TrafficLight {
        RED, YELLOW, GREEN
    }

    enum Priority {
        LOW(1), MEDIUM(2), HIGH(3);

        private final int code;

        Priority(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        // Safe runtime lookup from integer to enum.
        // This lets us demonstrate an invalid integer without crashing the program.
        public static Priority fromCode(int code) {
            for (Priority p : values()) {
                if (p.code == code) {
                    return p;
                }
            }
            throw new IllegalArgumentException("No Priority constant with code " + code);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Program 1: Enumeration Basics ===");
        System.out.println();

        // -------------------------------
        // Part 1: Basic enum declaration and usage
        // -------------------------------
        System.out.println("Normal enum declaration and usage:");

        TrafficLight light = TrafficLight.RED;
        System.out.println("Assigned variable: " + light);
        System.out.println("Name: " + light.name());
        System.out.println("Ordinal: " + light.ordinal());
        System.out.println();

        System.out.println("Enum with explicit integer field:");
        Priority priority = Priority.HIGH;
        System.out.println("Assigned variable: " + priority);
        System.out.println("Name: " + priority.name());
        System.out.println("Stored code: " + priority.getCode());
        System.out.println();

        // -------------------------------
        // Part 2: Beyond Normal Usage
        // -------------------------------
        // In Java, you cannot directly cast an arbitrary int to an enum type
        // the way C-style enums sometimes allow.
        // So we simulate the "invalid integer value" scenario with a safe lookup method.
        System.out.println("Beyond Normal Usage: use an integer outside the defined enum range");
        System.out.println("Expected: trying to map 99 to Priority should fail at runtime.");
        try {
            Priority badPriority = Priority.fromCode(99);
            System.out.println("Actual: created value " + badPriority);
            System.out.println("Caught: not caught");
        } catch (IllegalArgumentException e) {
            System.out.println("Actual: exception -> " + e);
            System.out.println("Caught: runtime");
        }
        System.out.println();

        // Demonstrate another common Java enum lookup behavior.
        System.out.println("Expected: valueOf with an invalid name should also fail at runtime.");
        try {
            TrafficLight badLight = TrafficLight.valueOf("BLUE");
            System.out.println("Actual: created value " + badLight);
            System.out.println("Caught: not caught");
        } catch (IllegalArgumentException e) {
            System.out.println("Actual: exception -> " + e);
            System.out.println("Caught: runtime");
        }
        System.out.println();

        // -------------------------------
        // Part 3: Compile-time note
        // -------------------------------
        // This is important for the demo:
        // Java enums are not raw integers, so many invalid uses are prevented by the type system.
        System.out.println("Compile-time note:");
        System.out.println("Java does not allow assigning an int directly to an enum variable.");
        System.out.println("Example that would NOT compile: TrafficLight x = 2;");
        System.out.println();

        // -------------------------------
        // Final takeaway
        // -------------------------------
        System.out.println("Conclusion:");
        System.out.println("Java enums are type-safe and are not just named integers.");
        System.out.println("Invalid values are usually caught either by the compiler or at runtime through lookup methods.");
    }
}
