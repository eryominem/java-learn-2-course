package ru.nshi.Task2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SortingRequest {
    @JsonProperty(value = "values")
    private int[] values;

    public SortingRequest() {

    }

    public SortingRequest(int[] values) {
        this.values = values;
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }
}
