# Java Deliverable 1 — Enumeration Types

This folder contains the Java side of Deliverable 1 for the enum project. It mirrors the Python structure so both languages can be compared more easily in the slides and demo.

## Files
- `P01EnumerationBasics.java`
- `P02ExhaustiveControlFlow.java`
- `P03ExtendedEnumBehavior.java`
- `P04IterationAndIntrospection.java`
- `P05AppliedEnumeration.java`

## What each program demonstrates
1. **Enumeration Basics**
   - Basic enum declaration
   - Variable assignment and printing
   - Invalid value lookup and Java type-safety notes

2. **Exhaustive Control Flow**
   - Classic `switch`
   - Enhanced `switch`
   - Missing-case behavior
   - Effect of adding a new enum variant

3. **Extended Enum Behavior**
   - Enum fields
   - Constructors
   - Methods
   - Workaround for per-use associated data
   - Null misuse at runtime

4. **Iteration and Introspection**
   - Iterating with `values()`
   - Basic introspection/reflection
   - Duplicate custom field values versus distinct enum constants

5. **Applied Enumeration**
   - Vending machine example
   - Enums driving logic, not just labels
   - Invalid input and null handling

## How to compile
Compile each file separately, for example:

```bash
javac P01EnumerationBasics.java
```

or compile all at once:

```bash
javac *.java
```

## How to run

```bash
java P01EnumerationBasics
java P02ExhaustiveControlFlow
java P03ExtendedEnumBehavior
java P04IterationAndIntrospection
java P05AppliedEnumeration
```

## Notes for the demo
- Each program prints expected behavior, actual behavior, and whether the issue is caught at compile time or runtime.
- Some invalid enum uses in Java are prevented by the compiler, so those are explained in comments and printed notes instead of being written as broken code.
- The programs are designed to be screenshot-friendly for Deliverable 2.

## Academic integrity note
If you use this code in the project repository, make sure every team member understands each line and can explain it during the video demo.
