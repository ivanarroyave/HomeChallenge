package com.paloit.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Json {
    private static final Logger LOGGER = LoggerFactory.getLogger(Json.class);

    private Json() {
    }

    public static <T> String generateJson(T object) {
        return gsonContent(object);
    }

    public static <T> String generateJson(T object, boolean printLog) {
        String json = gsonContent(object);

        if (printLog)
            LOGGER.info("\r\n{}", json);

        return json;
    }

    private static <T> String gsonContent(T object) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(object);
    }

    public static <T> T[] deserializeObjectList(String jsonPath, Class<T[]> clazz) throws IOException {
        return new Gson().fromJson(Files.readString(Path.of(jsonPath)), clazz);
    }

    public static <T> T deserializeObject(String jsonPath, Class<T> clazz) throws IOException {
        return new Gson().fromJson(Files.readString(Path.of(jsonPath)), clazz);
    }
}
