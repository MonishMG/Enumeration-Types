public class P05AppliedEnumeration {
    // These enums define the fixed choices used by the vending machine.
    // The goal is to show enums driving real logic, not just acting as labels.

    enum ProductType {
        SODA, CHIPS, CANDY
    }

    enum MachineState {
        IDLE, HAS_MONEY, DISPENSING, OUT_OF_STOCK
    }

    enum Price {
        SODA(2),
        CHIPS(1),
        CANDY(1);

        private final int amount;

        Price(int amount) {
            this.amount = amount;
        }

        public int getAmount() {
            return amount;
        }
    }

    static class VendingMachine {
        private MachineState state;
        private int balance;
        private final int[] stock;

        VendingMachine() {
            this.state = MachineState.IDLE;
            this.balance = 0;

            // Use enum ordinals to map stock to product positions.
            // This is okay here for a small demo, but in large systems,
            // relying too heavily on ordinal() can be fragile.
            this.stock = new int[ProductType.values().length];
            stock[ProductType.SODA.ordinal()] = 1;
            stock[ProductType.CHIPS.ordinal()] = 2;
            stock[ProductType.CANDY.ordinal()] = 0;
        }

        public void insertMoney(int amount) {
            if (amount <= 0) {
                System.out.println("Rejected invalid money amount: " + amount);
                return;
            }

            balance += amount;
            state = MachineState.HAS_MONEY;
            System.out.println("Inserted $" + amount + ". Current balance = $" + balance);
        }

        public void selectProduct(ProductType product) {
            System.out.println("Selected product request: " + product);

            if (product == null) {
                System.out.println("Expected: product should be a valid ProductType enum value.");
                System.out.println("Actual: got null instead.");
                System.out.println("Caught: runtime by custom validation");
                return;
            }

            if (stock[product.ordinal()] == 0) {
                state = MachineState.OUT_OF_STOCK;
                System.out.println(product.name() + " is out of stock.");
                return;
            }

            Price price = Price.valueOf(product.name());

            if (balance < price.getAmount()) {
                System.out.println("Not enough money for " + product.name() +
                        ". Price is $" + price.getAmount() + ", balance is $" + balance + ".");
                return;
            }

            state = MachineState.DISPENSING;
            stock[product.ordinal()]--;
            balance -= price.getAmount();
            System.out.println("Dispensing " + product.name() + ". Remaining balance = $" + balance);

            if (balance > 0) {
                state = MachineState.HAS_MONEY;
            } else {
                state = MachineState.IDLE;
            }
        }

        public void selectProductByName(String productName) {
            System.out.println("Selected product request by raw string: " + productName);

            try {
                ProductType product = ProductType.valueOf(productName);
                selectProduct(product);
            } catch (IllegalArgumentException e) {
                System.out.println("Expected: product name should match a ProductType enum constant.");
                System.out.println("Actual: exception -> " + e);
                System.out.println("Caught: runtime");
            }
        }

        public void printStatus() {
            System.out.println("State: " + state + ", Balance: $" + balance + ", Stock: " + stockSummary());
        }

        private String stockSummary() {
            return "{SODA=" + stock[ProductType.SODA.ordinal()] +
                    ", CHIPS=" + stock[ProductType.CHIPS.ordinal()] +
                    ", CANDY=" + stock[ProductType.CANDY.ordinal()] + "}";
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Program 5: Applied Enumeration ===");
        System.out.println();
        System.out.println("This program uses enums to drive vending machine logic.");
        System.out.println("The enums are not just labels. They control prices, stock checks, and state transitions.");
        System.out.println();

        VendingMachine machine = new VendingMachine();
        machine.printStatus();
        System.out.println();

        // -------------------------------
        // Part 1: Normal scenarios
        // -------------------------------
        System.out.println("Normal scenario 1: buy chips");
        machine.insertMoney(1);
        machine.selectProduct(ProductType.CHIPS);
        machine.printStatus();
        System.out.println();

        System.out.println("Normal scenario 2: try to buy candy, but it is out of stock");
        machine.insertMoney(1);
        machine.selectProduct(ProductType.CANDY);
        machine.printStatus();
        System.out.println();

        System.out.println("Normal scenario 3: buy soda");
        machine.insertMoney(2);
        machine.selectProduct(ProductType.SODA);
        machine.printStatus();
        System.out.println();

        // -------------------------------
        // Part 2: Beyond Normal Usage
        // -------------------------------
        System.out.println("Beyond Normal Usage: pass an invalid string instead of a valid enum name");
        System.out.println("Expected: the machine should reject it and keep running.");
        machine.selectProductByName("JUICE");
        machine.printStatus();
        System.out.println();

        System.out.println("Beyond Normal Usage: pass null instead of an enum");
        System.out.println("Expected: the machine should reject it and keep running.");
        machine.selectProduct(null);
        machine.printStatus();
        System.out.println();

        System.out.println("Beyond Normal Usage: insert invalid money");
        System.out.println("Expected: the machine should reject negative input and keep running.");
        machine.insertMoney(-5);
        machine.printStatus();
        System.out.println();

        // -------------------------------
        // Final takeaway
        // -------------------------------
        System.out.println("Conclusion:");
        System.out.println("Using enums makes the state machine and product logic clear and controlled.");
        System.out.println("Without enums, this would rely much more on loose strings or integers and would be easier to break.");
    }
}
