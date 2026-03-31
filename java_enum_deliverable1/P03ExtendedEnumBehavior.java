public class P03ExtendedEnumBehavior {
    enum Shape {
        CIRCLE("circle", 3),
        SQUARE("square", 4),
        TRIANGLE("triangle", 3);

        private final String label;
        private final int sides;

        Shape(String label, int sides) {
            this.label = label;
            this.sides = sides;
        }

        public String getLabel() {
            return label;
        }

        public int getSides() {
            return sides;
        }

        public String describe() {
            return label + " has " + sides + " side(s) in this simplified example";
        }
    }

    enum ErrorCode {
        NOT_FOUND(404),
        SERVER_ERROR(500);

        private final int code;

        ErrorCode(int code) {
            this.code = code;
        }

        public String message() {
            if (this == NOT_FOUND) {
                return "Item was not found";
            }
            return "Internal server error";
        }

        public int getCode() {
            return code;
        }
    }

    // Java enums can store fixed data per enum constant,
    // but they do not create fresh payloads each time they are used.
    // This wrapper class is a workaround for per-use associated data.
    static class EnumData<T> {
        private final T kind;
        private final Object extraData;

        EnumData(T kind, Object extraData) {
            this.kind = kind;
            this.extraData = extraData;
        }

        @Override
        public String toString() {
            return "EnumData(kind=" + kind + ", extraData=" + extraData + ")";
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Program 3: Extended Enum Behavior ===");
        System.out.println();

        // -------------------------------
        // Part 1: What Java enums can do directly
        // -------------------------------
        System.out.println("Direct enum behavior in Java:");
        System.out.println("Java enums can store fields, constructors, and methods.");

        Shape shape = Shape.CIRCLE;
        System.out.println("Enum value: " + shape);
        System.out.println("Attached label: " + shape.getLabel());
        System.out.println("Attached sides: " + shape.getSides());
        System.out.println("Method call: " + shape.describe());
        System.out.println();

        ErrorCode error = ErrorCode.NOT_FOUND;
        System.out.println("Another enum with behavior:");
        System.out.println("Enum value: " + error);
        System.out.println("Stored code: " + error.getCode());
        System.out.println("Method call: " + error.message());
        System.out.println();

        // -------------------------------
        // Part 2: Workaround for associated data
        // -------------------------------
        System.out.println("Workaround for carrying case-specific data like a radius or message:");
        System.out.println("Java enums can store fixed data per constant,");
        System.out.println("but they do not create fresh per-use payloads like algebraic data types.");
        System.out.println("A common workaround is to pair the enum with another class or object.");

        EnumData<Shape> shapeWithRadius = new EnumData<>(Shape.CIRCLE, "radius=10");
        EnumData<ErrorCode> errorWithMessage = new EnumData<>(ErrorCode.SERVER_ERROR, "message=Disk is full");
        System.out.println(shapeWithRadius);
        System.out.println(errorWithMessage);
        System.out.println();

        // -------------------------------
        // Part 3: Beyond Normal Usage
        // -------------------------------
        System.out.println("Beyond Normal Usage: invoke method/access data on a null variable");
        System.out.println("Expected: using null like an enum should fail at runtime with a NullPointerException.");

        Shape unsetShape = null;
        try {
            System.out.println(unsetShape.describe());
            System.out.println("Caught: not caught");
        } catch (NullPointerException e) {
            System.out.println("Actual: exception -> " + e);
            System.out.println("Caught: runtime");
        }
        System.out.println();

        System.out.println("Expected: accessing an enum method through null should also fail at runtime.");
        try {
            System.out.println(unsetShape.getLabel());
            System.out.println("Caught: not caught");
        } catch (NullPointerException e) {
            System.out.println("Actual: exception -> " + e);
            System.out.println("Caught: runtime");
        }
        System.out.println();

        // -------------------------------
        // Final takeaway
        // -------------------------------
        System.out.println("Conclusion:");
        System.out.println("Java enums support fields, constructors, and methods very well.");
        System.out.println("But for true per-instance associated values, a wrapper object is still needed.");
    }
}
