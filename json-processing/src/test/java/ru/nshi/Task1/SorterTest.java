package ru.nshi.Task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

public class SorterTest {

    /**
     * Для тестирования сортировки массива = null и пустого массива берём любую из реализаций интерфейса Sorter,
     * т.к. они обрабатывают такие массивы одинаково.
     */
    @Test
    public void nullArrayTest() {
        Sorter sorter = new BubbleSort();
        int[] values = null;

        NullPointerException e = Assertions.assertThrows(NullPointerException.class, () -> {
            sorter.sort(values);
        });

        Assertions.assertTrue(e.getMessage().contains("Array is null"));
    }

    @Test
    public void emptyArrayTest() {
        Sorter sorter = new InsertionSort();
        int[] values = {};
        values = sorter.sort(values);

        int actualLength = 0;
        Assertions.assertTrue(actualLength == values.length);
    }


    @ParameterizedTest
    @MethodSource("realizations")
    void sorterTest(Sorter sorter) {
        int[] toSort = {28, 6, 5, 21, 13, 3, 12, 23, 14, 37, 59, 50, 41};
        int[] afterSort = sorter.sort(toSort);

        boolean isSort = true;
        for (int i = 0; i < afterSort.length - 1; i++) {
            if (afterSort[i] > afterSort[i + 1])
                isSort = false;
        }

        Assertions.assertNotSame(toSort, afterSort);
        Assertions.assertTrue(isSort);

    }

    static Stream<Arguments> realizations() {
        return Arrays.asList(Arguments.of(new BubbleSort()),
                Arguments.of(new InsertionSort())).stream();
    }
}
