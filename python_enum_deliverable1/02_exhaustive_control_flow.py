from enum import Enum


# This enum represents a fixed set of order states.
class OrderStatus(Enum):
    NEW = 1
    PAID = 2
    SHIPPED = 3
    DELIVERED = 4


# This second enum simulates what happens if we later add a new variant
# but forget to update our control flow code.
class OrderStatusWithReturned(Enum):
    NEW = 1
    PAID = 2
    SHIPPED = 3
    DELIVERED = 4
    RETURNED = 5


# Full if/elif coverage for every enum member.
def handle_with_if(status):
    if status == OrderStatus.NEW:
        return "Order created"
    elif status == OrderStatus.PAID:
        return "Payment received"
    elif status == OrderStatus.SHIPPED:
        return "Order sent out"
    elif status == OrderStatus.DELIVERED:
        return "Order finished"
    else:
        return f"Unhandled status in if/elif: {status}"


# This version deliberately leaves out DELIVERED.
# The goal is to show that Python does not enforce exhaustiveness.
def handle_with_if_missing_case(status):
    if status == OrderStatus.NEW:
        return "Order created"
    elif status == OrderStatus.PAID:
        return "Payment received"
    elif status == OrderStatus.SHIPPED:
        return "Order sent out"
    else:
        return f"Unhandled status in if/elif: {status}"


# Full match-case coverage for every enum member.
# match-case looks more like pattern matching in other languages.
def handle_with_match(status):
    match status:
        case OrderStatus.NEW:
            return "Order created"
        case OrderStatus.PAID:
            return "Payment received"
        case OrderStatus.SHIPPED:
            return "Order sent out"
        case OrderStatus.DELIVERED:
            return "Order finished"
        case _:
            return f"Unhandled status in match-case: {status}"


# This version also deliberately leaves out DELIVERED.
def handle_with_match_missing_case(status):
    match status:
        case OrderStatus.NEW:
            return "Order created"
        case OrderStatus.PAID:
            return "Payment received"
        case OrderStatus.SHIPPED:
            return "Order sent out"
        case _:
            return f"Unhandled status in match-case: {status}"


# These two functions simulate adding a new enum member later
# without updating the old control flow logic.
def handle_new_variant_without_update_if(status):
    if status == OrderStatusWithReturned.NEW:
        return "Order created"
    elif status == OrderStatusWithReturned.PAID:
        return "Payment received"
    elif status == OrderStatusWithReturned.SHIPPED:
        return "Order sent out"
    elif status == OrderStatusWithReturned.DELIVERED:
        return "Order finished"
    else:
        return f"Unhandled NEW enum variant in if/elif: {status}"


def handle_new_variant_without_update_match(status):
    match status:
        case OrderStatusWithReturned.NEW:
            return "Order created"
        case OrderStatusWithReturned.PAID:
            return "Payment received"
        case OrderStatusWithReturned.SHIPPED:
            return "Order sent out"
        case OrderStatusWithReturned.DELIVERED:
            return "Order finished"
        case _:
            return f"Unhandled NEW enum variant in match-case: {status}"


print("=== Program 2: Exhaustive Control Flow ===")
print()

# -------------------------------
# Part 1: Full coverage
# -------------------------------
print("Full coverage with if/elif:")
for status in OrderStatus:
    print(f"{status.name} -> {handle_with_if(status)}")
print()

print("Full coverage with match-case:")
for status in OrderStatus:
    print(f"{status.name} -> {handle_with_match(status)}")
print()

# -------------------------------
# Part 2: Missing one case on purpose
# -------------------------------
print("Beyond Normal Usage: deliberately leave out one case")
print("Expected: Python should not give a compile-time warning or error.")
print("Expected: the code should keep running and fall to the else/default branch.")
print()

print("Missing DELIVERED in if/elif:")
print(f"Actual: {handle_with_if_missing_case(OrderStatus.DELIVERED)}")
print("Caught: not at compile time, not automatically at runtime")
print()

print("Missing DELIVERED in match-case:")
print(f"Actual: {handle_with_match_missing_case(OrderStatus.DELIVERED)}")
print("Caught: not at compile time, not automatically at runtime")
print()

# -------------------------------
# Part 3: Add a new enum variant later
# -------------------------------
print("Adding a new enum variant without updating control flow:")
print("Expected: Python should continue running silently unless we wrote our own fallback message.")

new_status = OrderStatusWithReturned.RETURNED
print(f"if/elif result -> {handle_new_variant_without_update_if(new_status)}")
print(f"match-case result -> {handle_new_variant_without_update_match(new_status)}")
print("Caught: not at compile time; program continues running")
print()

# -------------------------------
# Final comparison
# -------------------------------
print("Difference between if/elif and match-case in Python:")
print("Both can express enum branches clearly, but neither enforces exhaustiveness.")
print("match-case looks more like a traditional language-level pattern matching feature,")
print("while if/elif is just regular boolean branching.")
