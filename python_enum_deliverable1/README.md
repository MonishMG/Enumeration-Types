# Deliverable 1 - Python Enum Programs

This folder contains the Python portion of Deliverable 1 for the Programming Languages group project on enumeration types.

## Folder Contents

- `01_enumeration_basics.py`
- `02_exhaustive_control_flow.py`
- `03_extended_enum_behavior.py`
- `04_iteration_and_introspection.py`
- `05_applied_enumeration.py`

Each file is a separate program, as required. There is no main driver file.

## Python Version

These programs were written for Python **3.13**.

## How to Run

Run each file separately from the terminal:

```bash
python 01_enumeration_basics.py
python 02_exhaustive_control_flow.py
python 03_extended_enum_behavior.py
python 04_iteration_and_introspection.py
python 05_applied_enumeration.py
```

Depending on your setup, you may need to use:

```bash
python3 01_enumeration_basics.py
```

## What These Programs Demonstrate

### 1. Enumeration Basics
Shows normal enum declaration and variable assignment using `Enum` and `IntEnum`. It also tests what happens when an invalid integer value is used.

### 2. Exhaustive Control Flow
Shows both `if/elif` and `match-case` with enum values. It also demonstrates what happens when a case is intentionally left out and when a new enum variant is added without updating the control flow.

### 3. Extended Enum Behavior
Shows methods and attached member data on enums. It also shows a workaround for carrying additional data and demonstrates what happens when code tries to use `None` like an enum value.

### 4. Iteration and Introspection
Shows how Python supports iteration over enum members and how `__members__` exposes names, including aliases. It also explores duplicate integer values in `IntEnum`.

### 5. Applied Enumeration
Implements a vending machine example where enums drive the machine state and product logic. This makes the enum meaningful to the program design instead of using it only as a label.

## Beyond Normal Usage

Each program includes at least one edge-case test. For each one, the output clearly shows:

1. **Expected** behavior
2. **Actual** behavior
3. Whether Python caught the issue at runtime or not at all

The programs are written so that failing cases do **not** crash the whole program. Exceptions are caught and printed when needed.

## Notes for Demo / Presentation

- Python does **not** have compile-time checking for enum misuse.
- Invalid enum construction is usually caught at **runtime**.
- Python does **not** enforce exhaustive handling of enum cases in `if/elif` or `match-case`.
- Duplicate values in `Enum` / `IntEnum` create **aliases** by default.
- Python enums can hold methods and shared member attributes, but they are not as expressive as algebraic data types with per-instance payloads.


