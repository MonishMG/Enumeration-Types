from enum import Enum


# This enum shows that Python enum members can store more than just a single name.
# Each member is given a tuple value: (label, number_of_sides)
class Shape(Enum):
    CIRCLE = ("circle", 3)
    SQUARE = ("square", 4)
    TRIANGLE = ("triangle", 3)

    # __init__ runs once for each enum member when the enum class is created.
    # We use it to attach extra attributes to each member.
    def __init__(self, label, sides):
        self.label = label
        self.sides = sides

    # Enums in Python can also have methods, just like classes.
    def describe(self):
        return f"{self.label} has {self.sides} side(s) in this simplified example"


# This enum shows another example of behavior attached to enum values.
# Here the values are plain integers, but we still add a method.
class ErrorCode(Enum):
    NOT_FOUND = 404
    SERVER_ERROR = 500

    def message(self):
        if self == ErrorCode.NOT_FOUND:
            return "Item was not found"
        return "Internal server error"


# Python enums do not naturally support fresh per-use associated data
# like "CIRCLE with radius 10" as a distinct enum value.
# So this wrapper class is one workaround:
# store the enum itself in one field and the extra payload in another.
class ShapeData:
    def __init__(self, kind, extra_data):
        self.kind = kind
        self.extra_data = extra_data

    def __str__(self):
        return f"ShapeData(kind={self.kind}, extra_data={self.extra_data})"


print("=== Program 3: Extended Enum Behavior ===")
print()

# -------------------------------
# Part 1: What Python enums can do directly
# -------------------------------
print("Direct enum behavior in Python:")
print("Python enums can store values and methods, and we can attach extra attributes in __init__.")

shape = Shape.CIRCLE
print(f"Enum value: {shape}")
print(f"Attached label: {shape.label}")
print(f"Attached sides: {shape.sides}")
print(f"Method call: {shape.describe()}")
print()

error = ErrorCode.NOT_FOUND
print("Another enum with behavior:")
print(f"Enum value: {error}")
print(f"Method call: {error.message()}")
print()

# -------------------------------
# Part 2: Workaround for associated data
# -------------------------------
print("Workaround for carrying case-specific data like a radius or message:")
print("Python enums do not create fresh per-use payloads the way algebraic data types do.")
print("A common workaround is to pair an enum with another class or object that stores the extra data.")

# Here we keep the enum member fixed, but add custom data in a separate object.
shape_with_radius = ShapeData(Shape.CIRCLE, {"radius": 10})
error_with_message = ShapeData(ErrorCode.SERVER_ERROR, {"message": "Disk is full"})

print(shape_with_radius)
print(error_with_message)
print()

# -------------------------------
# Part 3: Beyond Normal Usage
# -------------------------------
# The assignment asks us to test behavior outside normal usage.
# Here we intentionally use None instead of a real enum value.
print("Beyond Normal Usage: access method/data on a null variable")
print("Expected: using None like an enum should fail at runtime with an AttributeError.")

unset_shape = None

try:
    # This fails because None does not have a describe() method.
    print(unset_shape.describe())
    print("Caught: not caught")
except AttributeError as e:
    print(f"Actual: exception -> {e}")
    print("Caught: runtime")
print()

print("Expected: accessing an attached enum attribute through None should also fail at runtime.")
try:
    # This also fails because None does not have a label attribute.
    print(unset_shape.label)
    print("Caught: not caught")
except AttributeError as e:
    print(f"Actual: exception -> {e}")
    print("Caught: runtime")
print()

# -------------------------------
# Final takeaway
# -------------------------------
print("Conclusion:")
print("Python enums support methods and shared member data nicely,")
print("but for true per-instance associated values, a separate wrapper object is usually needed.")
