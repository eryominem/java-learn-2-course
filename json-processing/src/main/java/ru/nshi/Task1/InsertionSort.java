package ru.nshi.Task1;

public class InsertionSort implements Sorter {
    @Override
    public int[] sort(int[] toSort) {
        if (toSort == null)
            throw new NullPointerException("Array is null");

        if (toSort.length == 0)
            return new int[0];

        int[] array = toSort.clone();

        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while(j >= 0 && current < array[j]) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = current;
        }
        return array;
    }
}