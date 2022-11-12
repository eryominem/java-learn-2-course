package ru.nshi.Task2;

import ru.nshi.Task1.Sorter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;
import java.io.File;
import java.io.IOException;

public class JsonParse {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private HashMap<String, Sorter> sorters;

    public JsonParse() {

    }

    public JsonParse(HashMap<String, Sorter> sorters) {
        this.sorters = sorters;
    }

    public String JsonProcessing(String jsonPath) throws IOException {
        JsonModel jsonModel = deserialization(jsonPath);
        return sortAndSerialize(jsonModel);
    }

    public JsonModel deserialization(String jsonPath) throws IOException {
        File file = new File(jsonPath);
        JsonModel jsonModel = objectMapper.readValue(file, JsonModel.class);
        return jsonModel;
    }

    public String sortAndSerialize(JsonModel jsonModel) throws JsonProcessingException {
        String algorithm = jsonModel.getAlgorithm();

        if (jsonModel.getValues() == null) {
            return objectMapper.writeValueAsString(
                    new JsonError("Array is null")
            );
        }

        int[] values;
        long timeBeforeSort = System.currentTimeMillis();

        try {
            values = sorters.get(algorithm).sort(jsonModel.getValues());
        } catch (Exception e) {
            return objectMapper.writeValueAsString(
                    new JsonError(String.format(String.format("Algorithm %s is not supported", algorithm)))
            );
        }

        long timeAfterSort = System.currentTimeMillis();
        long sortingTime = timeAfterSort - timeBeforeSort;

        String response = objectMapper.writeValueAsString(new JsonDTO(sortingTime, values));
        return response;
    }

}

