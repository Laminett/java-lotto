package step1;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BinaryOperator;

public enum Operation {
    ADD("+", (Integer operand1, Integer operand2) -> operand1 + operand2),
    SUBTRACT("-", (Integer operand1, Integer operand2) -> operand1 - operand2),
    MULTIPLY("*", (Integer operand1, Integer operand2) -> operand1 * operand2),
    DIVIDE("/", (Integer operand1, Integer operand2) -> operand1 / operand2);

    private final String symbol;

    private final BinaryOperator<Integer> work;

    Operation(String symbol, BinaryOperator<Integer> work) {
        this.symbol = symbol;
        this.work = work;
    }

    public static Operation castOperation(String operationString) {
        return Arrays.stream(Operation.values())
                .filter(operation -> Objects.equals(operation.toString(), operationString))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(operationString + "는 연산자로 사용할 수 없습니다."));
    }

    public Integer work(Integer operand1, Integer operand2) {
        return this.work.apply(operand1, operand2);
    }

    @Override
    public String toString() {
        return this.symbol;
    }
}