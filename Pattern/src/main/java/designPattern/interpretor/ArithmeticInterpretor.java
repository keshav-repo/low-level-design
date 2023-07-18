package designPattern.interpretor;

interface Expression {
    int interpret();
}

// Terminal Expression classes
class NumberExpression implements Expression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    public int interpret() {
        return number;
    }
}

// Non-Terminal Expression classes
class AdditionExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public AdditionExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public int interpret() {
        return leftExpression.interpret() + rightExpression.interpret();
    }
}

class SubtractionExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public SubtractionExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public int interpret() {
        return leftExpression.interpret() - rightExpression.interpret();
    }
}

class MultiplicationExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public MultiplicationExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public int interpret() {
        return leftExpression.interpret() * rightExpression.interpret();
    }
}

class DivisionExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public DivisionExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public int interpret() {
        int divisor = rightExpression.interpret();
        if (divisor != 0) {
            return leftExpression.interpret() / divisor;
        } else {
            throw new ArithmeticException("Division by zero");
        }
    }
}

// Client code
public class ArithmeticInterpretor {
    public static void main(String[] args) {
        // Create the expression: (5 + 3) * (10 - 2) / 4
        Expression expression = new DivisionExpression(
                new MultiplicationExpression(
                        new AdditionExpression(
                                new NumberExpression(5),
                                new NumberExpression(3)
                        ),
                        new SubtractionExpression(
                                new NumberExpression(10),
                                new NumberExpression(2)
                        )
                ),
                new NumberExpression(4)
        );

        // Evaluate the expression
        int result = expression.interpret();
        System.out.println("Result: " + result);
    }
}



