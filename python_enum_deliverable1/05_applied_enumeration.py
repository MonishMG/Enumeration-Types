from enum import Enum, IntEnum


# These enums define the fixed choices used by the vending machine.
# The goal is to show enums driving real logic, not just acting as labels.

class ProductType(Enum):
    SODA = "soda"
    CHIPS = "chips"
    CANDY = "candy"


class MachineState(Enum):
    IDLE = "idle"
    HAS_MONEY = "has_money"
    DISPENSING = "dispensing"
    OUT_OF_STOCK = "out_of_stock"


# Prices are fixed and tied to product names.
# IntEnum is convenient here because the prices behave like integers.
class Price(IntEnum):
    SODA = 2
    CHIPS = 1
    CANDY = 1


class VendingMachine:
    def __init__(self):
        # The machine starts idle with no money inserted.
        self.state = MachineState.IDLE
        self.balance = 0

        # Stock is also controlled by enums instead of loose strings.
        self.stock = {
            ProductType.SODA: 1,
            ProductType.CHIPS: 2,
            ProductType.CANDY: 0,
        }

    def insert_money(self, amount):
        # Invalid input is rejected, but the program keeps running.
        if amount <= 0:
            print(f"Rejected invalid money amount: {amount}")
            return

        self.balance += amount
        self.state = MachineState.HAS_MONEY
        print(f"Inserted ${amount}. Current balance = ${self.balance}")

    def select_product(self, product):
        print(f"Selected product request: {product}")

        # This custom validation handles bad input safely.
        # It lets us demonstrate "beyond normal usage" without crashing.
        if not isinstance(product, ProductType):
            print("Expected: product should be a ProductType enum value.")
            print(f"Actual: got {type(product).__name__} instead.")
            print("Caught: runtime by custom validation")
            return

        # If the selected product is out of stock, update the machine state.
        if self.stock[product] == 0:
            self.state = MachineState.OUT_OF_STOCK
            print(f"{product.name} is out of stock.")
            return

        # Use the enum name to look up the matching price enum member.
        price = Price[product.name]

        # If there is not enough money, do not dispense anything.
        if self.balance < price:
            print(f"Not enough money for {product.name}. Price is ${price}, balance is ${self.balance}.")
            return

        # Successful purchase path.
        self.state = MachineState.DISPENSING
        self.stock[product] -= 1
        self.balance -= price
        print(f"Dispensing {product.name}. Remaining balance = ${self.balance}")

        # After dispensing, the machine returns to the correct state.
        if self.balance > 0:
            self.state = MachineState.HAS_MONEY
        else:
            self.state = MachineState.IDLE

    def print_status(self):
        print(f"State: {self.state.name}, Balance: ${self.balance}, Stock: {self.stock_summary()}")

    def stock_summary(self):
        # This helper formats the stock neatly for output.
        return {product.name: count for product, count in self.stock.items()}


print("=== Program 5: Applied Enumeration ===")
print()
print("This program uses enums to drive vending machine logic.")
print("The enums are not just labels. They control prices, stock checks, and state transitions.")
print()

machine = VendingMachine()
machine.print_status()
print()

# -------------------------------
# Part 1: Normal scenarios
# -------------------------------
print("Normal scenario 1: buy chips")
machine.insert_money(1)
machine.select_product(ProductType.CHIPS)
machine.print_status()
print()

print("Normal scenario 2: try to buy candy, but it is out of stock")
machine.insert_money(1)
machine.select_product(ProductType.CANDY)
machine.print_status()
print()

print("Normal scenario 3: buy soda")
machine.insert_money(2)
machine.select_product(ProductType.SODA)
machine.print_status()
print()

# -------------------------------
# Part 2: Beyond Normal Usage
# -------------------------------
print("Beyond Normal Usage: pass a string instead of an enum")
print("Expected: the machine should reject it and keep running.")
machine.select_product("SODA")
machine.print_status()
print()

print("Beyond Normal Usage: pass None instead of an enum")
print("Expected: the machine should reject it and keep running.")
machine.select_product(None)
machine.print_status()
print()

print("Beyond Normal Usage: insert invalid money")
print("Expected: the machine should reject negative input and keep running.")
machine.insert_money(-5)
machine.print_status()
print()

# -------------------------------
# Final takeaway
# -------------------------------
print("Conclusion:")
print("Using enums makes the state machine and product logic clear and controlled.")
print("Without enums, this would rely much more on loose strings or integers and would be easier to break.")
