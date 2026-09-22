package com.example.Distributed.Application.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonFileUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Reads a list of objects from a JSON file.
     * 
     * @param filePath the path to the JSON file
     * @param clazz the class type to deserialize into
     * @param <T> the type of the objects in the list
     * @return the list of objects read from the file
     * @throws IOException if an I/O error occurs
     */
    public static <T> List<T> readFromFile(String filePath, Class<T> clazz) throws IOException {
        // Create the file object
        File file = new File(filePath);

        // If the file doesn't exist, return an empty list
        if (!file.exists()) {
            return List.of();
        }

        // Read from the file and return the deserialized objects
        return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    /**
     * Writes a list of objects to a JSON file.
     * 
     * @param filePath the path to the JSON file
     * @param list the list of objects to write to the file
     * @param <T> the type of the objects in the list
     * @throws IOException if an I/O error occurs
     */
    public static <T> void writeToFile(String filePath, List<T> list) throws IOException {
        // Create the file object
        File file = new File(filePath);

        // Write the list of objects to the file as JSON
        objectMapper.writeValue(file, list);
    }
}

