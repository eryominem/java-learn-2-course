package ru.nshi.Task2;

import java.util.Arrays;

public class JsonModel {
    private String algorithm;
    private int[] values;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "JsonModel{" +
                "algorithm='" + algorithm + '\'' +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}
