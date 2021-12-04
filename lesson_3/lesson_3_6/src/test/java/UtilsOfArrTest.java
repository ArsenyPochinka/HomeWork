import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class UtilsOfArrTest {
    @ParameterizedTest
    @MethodSource("samplingFromAnArrayOperationArgumentsProvider")
    void shouldPerformSamplingFromAnArrayOperation_whenSomeCorrectIntegerArrayPassed(Integer[] inputArr, Integer[] outputArr) {
        Assertions.assertArrayEquals(outputArr, UtilsOfArr.samplingFromAnArray(inputArr));
    }

    private static Stream<Arguments> samplingFromAnArrayOperationArgumentsProvider() {
        Integer[] input1 = {5, 2, 3, 4, 1, 2, 3};
        Integer[] output1 = {1, 2, 3};
        Integer[] input2 = {4, 4, 4, 4, 4, 4, 1};
        Integer[] output2 = {1};
        Integer[] input3 = {4, 2, 1, 3};
        Integer[] output3 = {2, 1, 3};

        return Stream.of(
                Arguments.of(input1, output1),
                Arguments.of(input2, output2),
                Arguments.of(input3, output3)
        );
    }

    @Test
    void shouldPerformSamplingFromAnArrayOperation_andThrowRuntimeException_whenSomeIncorrectIntegerArrayPassed() {
        Assertions.assertThrows(RuntimeException.class, () -> UtilsOfArr.samplingFromAnArray(1,2,3,1,2,3));
    }

    @ParameterizedTest
    @MethodSource("checkingAnArrayOperationArgumentsProvider")
    void shouldPerformCheckingAnArrayOperation_whenSomeIntegerArrayPassed(boolean result, Integer[] arr) {
        Assertions.assertEquals(result, UtilsOfArr.checkingAnArray(arr));
    }

    private static Stream<Arguments> checkingAnArrayOperationArgumentsProvider() {

        return Stream.of(
                Arguments.of(true, new Integer[]{1, 4, 4, 1, 4, 1, 4}),
                Arguments.of(false, new Integer[]{1, 4, 2, 4}),
                Arguments.of(false, new Integer[]{1, 1, 1, 1}),
                Arguments.of(false, new Integer[]{4, 4, 4})
        );
    }
}
