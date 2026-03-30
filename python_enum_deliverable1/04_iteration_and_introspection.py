from enum import Enum, IntEnum


# A simple enum for demonstrating normal iteration.
class Day(Enum):
    MONDAY = 1
    TUESDAY = 2
    WEDNESDAY = 3
    THURSDAY = 4
    FRIDAY = 5


# This IntEnum intentionally gives the same integer value to two names.
# Python allows this and treats the later one as an alias.
class StatusCode(IntEnum):
    OK = 1
    SUCCESS = 1
    WARNING = 2
    ERROR = 3


print("=== Program 4: Iteration and Introspection ===")
print()

# -------------------------------
# Part 1: Built-in iteration
# -------------------------------
print("Built-in iteration over enum values:")
print("Expected: Python should let us iterate directly over the enum class.")

for day in Day:
    print(f"{day.name} -> {day.value}")

print("Actual: it works with very little code")
print("Caught: not an error")
print()

# -------------------------------
# Part 2: Introspection
# -------------------------------
# __members__ is a built-in mapping of declared names to enum members.
# It includes aliases too, which is important for this assignment.
print("Introspection with __members__:")
print("Expected: __members__ should include every declared name.")

for name, member in StatusCode.__members__.items():
    print(f"{name} -> {member} (value={member.value})")
print()

# -------------------------------
# Part 3: Beyond Normal Usage
# -------------------------------
print("Beyond Normal Usage: assign the same integer to two names")
print("Expected: Python allows this in Enum/IntEnum and treats the later name as an alias.")
print("Expected: normal iteration should skip aliases, but __members__ should still show both names.")
print()

print("Normal iteration over StatusCode:")
for code in StatusCode:
    print(f"{code.name} -> {code.value}")
print()

# Here we directly test whether the two names refer to the same object.
print("Checking alias behavior directly:")
print(f"StatusCode.OK is StatusCode.SUCCESS -> {StatusCode.OK is StatusCode.SUCCESS}")
print(f"StatusCode.OK == StatusCode.SUCCESS -> {StatusCode.OK == StatusCode.SUCCESS}")
print("Caught: not an error; Python allows aliases by default")
print()

print("What appears during iteration?")
print("Actual: only the first name for value 1 appears in normal iteration.")
print("Actual: both names appear in __members__ because __members__ preserves declared names, including aliases.")
print()

# -------------------------------
# Final takeaway
# -------------------------------
print("Conclusion:")
print("Python has strong built-in support for enum iteration and introspection.")
print("But duplicate values can be surprising because aliases are hidden in normal iteration.")
