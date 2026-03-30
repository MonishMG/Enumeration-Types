from enum import Enum, IntEnum


# A regular Enum is used for symbolic names.
# These members are not meant to behave like plain integers in normal use.
class TrafficLight(Enum):
    RED = 1
    YELLOW = 2
    GREEN = 3


# IntEnum is similar to Enum, but its members also behave like integers.
# This can be useful, but it also makes the type a little less strict.
class Priority(IntEnum):
    LOW = 1
    MEDIUM = 2
    HIGH = 3


print("=== Program 1: Enumeration Basics ===")
print()

# -------------------------------
# Part 1: Basic enum declaration and usage
# -------------------------------
print("Normal Enum declaration and usage:")

light = TrafficLight.RED
print(f"Assigned variable: {light}")
print(f"Name: {light.name}")
print(f"Value: {light.value}")
print()

print("Normal IntEnum declaration and usage:")

priority = Priority.HIGH
print(f"Assigned variable: {priority}")
print(f"Name: {priority.name}")
print(f"Value: {priority.value}")
print()

# -------------------------------
# Part 2: Beyond Normal Usage
# -------------------------------
# The assignment asks us to try an integer that is outside the enum range.
# In Python, this is checked at runtime when we try to construct the enum.
print("Beyond Normal Usage: using an integer outside the defined enum range")
print("Expected: TrafficLight(99) should fail at runtime because 99 is not a valid member.")

try:
    bad_light = TrafficLight(99)
    print(f"Actual: created value {bad_light}")
    print("Caught: not caught")
except ValueError as e:
    print(f"Actual: exception -> {e}")
    print("Caught: runtime")
print()

print("Expected: Priority(99) should also fail at runtime for the same reason.")

try:
    bad_priority = Priority(99)
    print(f"Actual: created value {bad_priority}")
    print("Caught: not caught")
except ValueError as e:
    print(f"Actual: exception -> {e}")
    print("Caught: runtime")
print()

# -------------------------------
# Part 3: Extra IntEnum observation
# -------------------------------
# IntEnum members can compare equal to integers.
# This is worth mentioning in the demo because it shows a design trade-off:
# convenience versus strict type separation.
print("Extra observation for IntEnum:")
print("Expected: IntEnum members should compare equal to matching integers.")
print(f"Actual: Priority.HIGH == 3 -> {Priority.HIGH == 3}")
print("Caught: not an error; Python allows this")
print()

# -------------------------------
# Final takeaway
# -------------------------------
print("Conclusion:")
print("Python does not catch invalid enum values at compile time.")
print("It catches them at runtime only when you try to construct the enum from that value.")
