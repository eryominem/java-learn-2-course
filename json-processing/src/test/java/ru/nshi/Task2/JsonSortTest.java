package ru.nshi.Task2;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nshi.Task1.BubbleSort;
import ru.nshi.Task1.InsertionSort;
import ru.nshi.Task1.Sorter;

import java.io.IOException;
import java.util.HashMap;

public class JsonSortTest {
    private JsonParse jsonParse;

    @BeforeEach
    public void setUp() {
        HashMap<String, Sorter> sorters = new HashMap<>();
        sorters.put("bubble", new BubbleSort());
        sorters.put("insertion", new InsertionSort());

        this.jsonParse = new JsonParse(sorters);
    }

    @Test
    public void arrayIsNullTest() throws IOException {
        String jsonPath = "src/main/resources/nullArray.json";
        String result = jsonParse.JsonProcessing(jsonPath);

        Assertions.assertTrue(result.contains("errorMessage"));
        Assertions.assertTrue(result.contains("Array is null"));
    }

    @Test
    public void unsupportedAlgorithm() throws IOException {
        String jsonPath = "src/main/resources/unsupportedAlgorithm.json";
        String result = jsonParse.JsonProcessing(jsonPath);

        Assertions.assertTrue(result.contains("errorMessage"));
        Assertions.assertTrue(result.contains("is not supported"));
    }

    @Test
    public void sortJsonTest() throws Exception {
        String jsonPath = "src/main/resources/example.json";
        String result = jsonParse.JsonProcessing(jsonPath);

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JsonDTO jsonDTO = objectMapper.readValue(result, JsonDTO.class);

        boolean isSorted = true;
        for (int i = 1; i < jsonDTO.getValues().length; i++) {
            if (jsonDTO.getValues()[i-1] > jsonDTO.getValues()[i])
                isSorted = false;
        }

        Assertions.assertTrue(isSorted);
    }

}
