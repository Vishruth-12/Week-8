# Week 8 — Category C Practice Problems

1. Payment System Fee Calculation
2. Library Item Due Date Calculator
3. Delivery Fee Calculator
4. Examination Question Grader
5. Public Transport Fare Calculator

These solutions use inheritance and method overriding so different types implement a common operation and are processed uniformly.

## Important note — Problem 3 sample inconsistency

The PDF's written pricing formulas yield STANDARD = 15.00, EXPRESS = 24.00, and INTERNATIONAL = 145.00 for its sample input, while the printed expected output lists EXPRESS = 29.00, INTERNATIONAL = 155.00, and Total = 199.00. The `DeliveryFeeCalculator.java` solution follows the explicit business rules/formulas stated in the PDF.