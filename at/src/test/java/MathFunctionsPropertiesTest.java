import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import org.example.MathLogger;
import org.example.MathFunctions;

import java.util.Arrays;

class MathFunctionsPropertiesTest {

    static class NoopMathLogger implements MathLogger {
        @Override
        public void log(String operation, int[] inputs) {
        }
    }

    private final MathFunctions math = new MathFunctions(new NoopMathLogger());

    // Gerador de arrays não nulos, não vazios
    @Provide
    Arbitrary<int[]> nonEmptyIntArrays() {
        return Arbitraries.integers()
                .between(-1_000_000, 1_000_000)
                .array(int[].class)
                .ofMinSize(1)
                .ofMaxSize(1_000);
    }

    // Gerador de números primos
    @Provide
    Arbitrary<Integer> primesUpTo10000() {
        return Arbitraries.integers()
                .between(2, 10_000)
                .filter(this::isPrimeReference);
    }

    private boolean isPrimeReference(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }


    @Property
    boolean multiplyByTwo_semprePar(@ForAll int n) {
        int result = math.multiplyByTwo(n);
        return result % 2 == 0;
    }


    @Property
    boolean generateMultiplicationTable_todosMultiplosOriginal(
            @ForAll @IntRange(min = -1_000_000, max = 1_000_000) int number,
            @ForAll @IntRange(min = 1, max = 1000) int limit
    ) {
        int[] table = math.generateMultiplicationTable(number, limit);

        for (int value : table) {
            if (number == 0) {
                if (value != 0) {
                    return false;
                }
            } else {
                if (value % number != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Property
    boolean isPrime_hasNoDivisorsOtherThanOneAndItself(
            @ForAll @IntRange(min = 2, max = 100_000) int candidate
    ) {
        boolean isPrimo = math.isPrime(candidate);
        if (!isPrimo) {
            return true;
        }

        for (int divisor = 2; divisor <= Math.sqrt(candidate); divisor++) {
            if (candidate % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    @Property
    boolean isPrime_ok_paraNumPrimos(
            @ForAll("primesUpTo10000") int prime
    ) {
        return math.isPrime(prime);
    }

    @Property
    boolean calculateAverage_entreMaiorEMenor(
            @ForAll("nonEmptyIntArrays") int[] numbers
    ) {
        double average = math.calculateAverage(numbers);

        int min = Arrays.stream(numbers).min().orElseThrow();
        int max = Arrays.stream(numbers).max().orElseThrow();

        return average >= min && average <= max;
    }


}
