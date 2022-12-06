package ru.nshi.Task2;

public class JsonModel {
    private String algorithm;
    private int[] values;

    public JsonModel(String algorithm, int[] values) {
        this.algorithm = algorithm;
        this.values = values;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public int[] getValues() {
        return values;
    }
}
