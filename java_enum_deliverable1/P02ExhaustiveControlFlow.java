public class P02ExhaustiveControlFlow {
    enum OrderStatus {
        NEW, PAID, SHIPPED, DELIVERED
    }

    enum OrderStatusWithReturned {
        NEW, PAID, SHIPPED, DELIVERED, RETURNED
    }

    // Classic switch statement with full coverage.
    static String handleWithClassicSwitch(OrderStatus status) {
        switch (status) {
            case NEW:
                return "Order created";
            case PAID:
                return "Payment received";
            case SHIPPED:
                return "Order sent out";
            case DELIVERED:
                return "Order finished";
            default:
                return "Unhandled status in classic switch: " + status;
        }
    }

    // Enhanced switch expression with full coverage.
    static String handleWithEnhancedSwitch(OrderStatus status) {
        return switch (status) {
            case NEW -> "Order created";
            case PAID -> "Payment received";
            case SHIPPED -> "Order sent out";
            case DELIVERED -> "Order finished";
        };
    }

    // Classic switch with one case deliberately left out.
    // Java allows this, so the program still runs and reaches default.
    static String handleClassicSwitchMissingCase(OrderStatus status) {
        switch (status) {
            case NEW:
                return "Order created";
            case PAID:
                return "Payment received";
            case SHIPPED:
                return "Order sent out";
            default:
                return "Unhandled status in classic switch: " + status;
        }
    }

    // For an enhanced switch expression, leaving out a case with no default
    // would cause a compile-time error.
    // So here we include default to keep the program runnable and demo-friendly.
    static String handleEnhancedSwitchMissingCase(OrderStatus status) {
        return switch (status) {
            case NEW -> "Order created";
            case PAID -> "Payment received";
            case SHIPPED -> "Order sent out";
            default -> "Unhandled status in enhanced switch: " + status;
        };
    }

    // Simulate adding a new enum constant later without updating old logic.
    static String handleNewVariantClassic(OrderStatusWithReturned status) {
        switch (status) {
            case NEW:
                return "Order created";
            case PAID:
                return "Payment received";
            case SHIPPED:
                return "Order sent out";
            case DELIVERED:
                return "Order finished";
            default:
                return "Unhandled NEW enum variant in classic switch: " + status;
        }
    }

    static String handleNewVariantEnhanced(OrderStatusWithReturned status) {
        return switch (status) {
            case NEW -> "Order created";
            case PAID -> "Payment received";
            case SHIPPED -> "Order sent out";
            case DELIVERED -> "Order finished";
            default -> "Unhandled NEW enum variant in enhanced switch: " + status;
        };
    }

    public static void main(String[] args) {
        System.out.println("=== Program 2: Exhaustive Control Flow ===");
        System.out.println();

        // -------------------------------
        // Part 1: Full coverage
        // -------------------------------
        System.out.println("Full coverage with classic switch:");
        for (OrderStatus status : OrderStatus.values()) {
            System.out.println(status.name() + " -> " + handleWithClassicSwitch(status));
        }
        System.out.println();

        System.out.println("Full coverage with enhanced switch:");
        for (OrderStatus status : OrderStatus.values()) {
            System.out.println(status.name() + " -> " + handleWithEnhancedSwitch(status));
        }
        System.out.println();

        // -------------------------------
        // Part 2: Missing one case on purpose
        // -------------------------------
        System.out.println("Beyond Normal Usage: deliberately leave out one case");
        System.out.println("Expected: classic switch can continue running if a case is missing and default is present.");
        System.out.println("Expected: enhanced switch expression is stricter, so we include default to keep it runnable.");
        System.out.println();

        System.out.println("Missing DELIVERED in classic switch:");
        System.out.println("Actual: " + handleClassicSwitchMissingCase(OrderStatus.DELIVERED));
        System.out.println("Caught: no compile-time error because default handles it");
        System.out.println();

        System.out.println("Missing DELIVERED in enhanced switch:");
        System.out.println("Actual: " + handleEnhancedSwitchMissingCase(OrderStatus.DELIVERED));
        System.out.println("Caught: no runtime crash because default handles it");
        System.out.println("Note: without default, this style would fail to compile when not exhaustive.");
        System.out.println();

        // -------------------------------
        // Part 3: Add a new enum variant later
        // -------------------------------
        System.out.println("Adding a new enum variant without updating control flow:");
        System.out.println("Expected: with default present, the program should keep running and report the unhandled case.");
        OrderStatusWithReturned newStatus = OrderStatusWithReturned.RETURNED;
        System.out.println("Classic switch -> " + handleNewVariantClassic(newStatus));
        System.out.println("Enhanced switch -> " + handleNewVariantEnhanced(newStatus));
        System.out.println("Caught: not a crash; default branch handles it");
        System.out.println();

        // -------------------------------
        // Final comparison
        // -------------------------------
        System.out.println("Difference between classic and enhanced switch in Java:");
        System.out.println("Classic switch is more permissive.");
        System.out.println("Enhanced switch expressions are stricter and better support exhaustiveness checking.");
    }
}
