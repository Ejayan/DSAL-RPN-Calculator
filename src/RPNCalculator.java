import dsal.stack.*; // Assuming this is the provided  stack package

/**
 * RPN Calculator using  Stack ADT
 * Supports +, -, *, /, ^ operations with error handling and trace mode
 */
public class RPNCalculator {
    private LinkedStack<Double> stack;
    private boolean traceMode;

    public RPNCalculator() {
        this.stack = new LinkedStack<>();
        this.traceMode = false;
    }

    public void setTraceMode(boolean mode) {
        this.traceMode = mode;
    }

    /**
     * Evaluates an RPN expression
     * @param expression Space-separated RPN expression
     * @return Result of evaluation
     * @throws IllegalArgumentException if expression is invalid
     */
    public double evaluate(String expression) {
        // Clear the stack for new evaluation
        while (!stack.isEmpty()) {
            try {
                stack.pop();
            } catch (Exception e) {
                // Stack empty, break out
                break;
            }
        }

        String[] tokens = expression.trim().split("\\s+");

        for (String token : tokens) {
            if (traceMode) {
                System.out.println("Processing token: '" + token + "'");
                System.out.print("Stack before: ");
                printStack();
            }

            try {
                processToken(token);
            } catch (Exception e) {
                throw new IllegalArgumentException("Error processing token '" + token + "': " + e.getMessage());
            }

            if (traceMode) {
                System.out.print("Stack after: ");
                printStack();
                System.out.println("---");
            }
        }

        // Check result
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("No result - expression may be empty");
        }

        double result;
        try {
            result = stack.pop();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error retrieving result");
        }

        // Verify stack is empty after popping result
        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression - too many operands");
        }

        return result;
    }

    private void processToken(String token) {
        // Check if token is a number
        try {
            double number = Double.parseDouble(token);
            stack.push(number);
            return;
        } catch (NumberFormatException e) {
            // Not a number, try as operator
        }

        // Process operators
        if (token.length() == 1 && isOperator(token.charAt(0))) {
            processOperator(token.charAt(0));
        } else {
            throw new IllegalArgumentException("Invalid token: " + token);
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private void processOperator(char operator) {
        // Need at least 2 operands for binary operators
        if (stack.size() < 2) {
            throw new IllegalArgumentException("Insufficient operands for operator '" + operator + "'");
        }

        double b, a;
        try {
            b = stack.pop();
            a = stack.pop();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error popping operands");
        }

        double result;

        switch (operator) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                if (b == 0) {
                    throw new IllegalArgumentException("Division by zero");
                }
                result = a / b;
                break;
            case '^':
                result = Math.pow(a, b);
                break;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }

        stack.push(result);
    }

    private void printStack() {
        // Since we can't directly iterate through Stack,
        // we need to temporarily transfer elements
        Stack<Double> temp = new LinkedStack<>();
        Stack<Double> reversed = new LinkedStack<>();
        // Transfer all elements to temp (reverses order)
        while (!stack.isEmpty()) {
            try {
                Double val = stack.pop();
                temp.push(val);
            } catch (Exception e) {
                break;
            }
        }

        // Transfer back to both stacks to maintain original
        System.out.print("[");
        boolean first = true;
        while (!temp.isEmpty()) {
            try {
                Double val = temp.pop();
                if (!first) {
                    System.out.print(", ");
                }
                System.out.print(val);
                first = false;
                stack.push(val);
            } catch (Exception e) {
                break;
            }
        }
        System.out.println("]");
    }

    /**
     * Main method for interactive testing
     */
    public static void main(String[] args) {
        RPNCalculator calc = new RPNCalculator();
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.println("RPN Calculator");
        System.out.println("Supported operators: + - * / ^");
        System.out.println("Enter 'trace on' to enable trace mode");
        System.out.println("Enter 'trace off' to disable trace mode");
        System.out.println("Enter 'quit' to exit");
        System.out.println();

        while (true) {
            System.out.print("Enter expression: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("quit")) {
                break;
            } else if (input.equalsIgnoreCase("trace on")) {
                calc.setTraceMode(true);
                System.out.println("Trace mode enabled");
                continue;
            } else if (input.equalsIgnoreCase("trace off")) {
                calc.setTraceMode(false);
                System.out.println("Trace mode disabled");
                continue;
            }

            try {
                double result = calc.evaluate(input);
                System.out.println("Result: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }

        scanner.close();
        System.out.println("Goodbye!");
    }
}