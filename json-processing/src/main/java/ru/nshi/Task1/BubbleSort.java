package ru.nshi.Task1;

public class BubbleSort implements Sorter{
    @Override
    public int[] sort(int[] toSort) {
        if (toSort == null)
            throw new NullPointerException("Array is null");

        if (toSort.length == 0)
            return new int[0];

        int[] array = toSort.clone();

        boolean sorted = false;
        int temp;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i+1]) {
                    temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    sorted = false;
                }
            }
        }
        return array;
    }
}
