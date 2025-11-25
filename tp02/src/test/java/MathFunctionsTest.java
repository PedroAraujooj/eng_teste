import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.example.MathFunctions;

public class MathFunctionsTest {
    @Property
    boolean doublingIsGreaterOrEqual(
            @ForAll @IntRange(min = 0, max = 1_000_000) int number
    ) {
        int result = MathFunctions.multiplyByTwo(number);
        return result >= number;
    }
    @Provide
    Arbitrary<Integer> inteirosPares() {
        return Arbitraries.integers()
                .filter(n -> n % 2 == 0);
    }

    @Property
    boolean doublingEvenNumberIsEven(@ForAll("inteirosPares") int x) {
        int result = MathFunctions.multiplyByTwo(x);
        return result % 2 == 0;
    }
}
