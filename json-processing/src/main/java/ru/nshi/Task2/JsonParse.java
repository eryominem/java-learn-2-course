package ru.nshi.Task2;

import ru.nshi.Task1.BubbleSort;
import ru.nshi.Task1.Sorter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;
import java.io.File;
import java.io.IOException;

public class JsonParse {
    private static final String JSON_PATH = "src/main/resources/example.json";
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

        /*ArrayNode values = (ArrayNode) objectMapper.readTree(file).get("values");

        ArrayList<Integer> numbers = new ArrayList<>();
        for (JsonNode node : values) {
            numbers.add(node.asInt());
        }*/
        /*HashMap<String, List<Integer>> storage = new HashMap<>();
        storage.put(objectMapper.readTree(file).get("algorithm").asText(), numbers);*/
    }

    public String sortAndSerialize(JsonModel jsonModel) throws JsonProcessingException {
        String algorithm = jsonModel.getAlgorithm();
        //System.out.println(sorters.containsKey("bubble"));

        if (jsonModel.getValues() == null) {
            return objectMapper.writeValueAsString("\"errorMessage\": Array is null");
        }

        int[] values;
        long timeBeforeSort = System.currentTimeMillis();

        try {
            values = sorters.get(algorithm).sort(jsonModel.getValues());
        } catch (Exception e) {
            return objectMapper.writeValueAsString
                    (String.format("\"errorMessage\": Algorithm %s is not supported.", algorithm));
        }

        long timeAfterSort = System.currentTimeMillis();
        long sortingTime = timeAfterSort - timeBeforeSort;

        String response = objectMapper.writeValueAsString(new JsonDTO(sortingTime, values));
        return response;

        /*long timeBeforeSort = System.currentTimeMillis();
        switch (algorithm) {
            case "bubble":
                BubbleSort bubbleSort = new BubbleSort();
                jsonModel.setValues(bubbleSort.sort(jsonModel.getValues()));
                break;

            case "insertion":
                InsertionSort insertionSort = new InsertionSort();
                jsonModel.setValues(insertionSort.sort(jsonModel.getValues()));
                break;
        }*/
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        String jsonPath = "json-processing\\src\\main\\resources\\nullArray.json";
        HashMap<String, Sorter> sorters = new HashMap<>();
        sorters.put("bubble", new BubbleSort());

        JsonParse jsonParse = new JsonParse(sorters);

        JsonModel jsonModel = jsonParse.deserialization(jsonPath);
        System.out.println(jsonModel);

        String result = jsonParse.JsonProcessing(jsonPath);
        System.out.println(result);
    }
}
