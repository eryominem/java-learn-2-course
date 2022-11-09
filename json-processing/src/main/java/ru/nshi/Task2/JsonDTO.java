package ru.nshi.Task2;

public class JsonDTO {
    private long time;
    private int[] values;

    public JsonDTO() {

    }

    public JsonDTO(long time, int[] values) {
        this.time = time;
        this.values = values;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }
}
