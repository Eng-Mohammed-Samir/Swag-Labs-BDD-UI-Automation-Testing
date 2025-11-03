package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonReader {
    static String jsonData_path = "src/test/resources/test-data/testData.json";

    private static JSONObject getJsonObject(String filePath) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;

        try (FileReader reader = new FileReader(filePath)) {
            Object obj = parser.parse(reader);
            jsonObject = (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    private static JSONObject getJsonNode(String nodeName) {
        JSONObject jsonObject = getJsonObject(jsonData_path);
        if (jsonObject != null && jsonObject.get(nodeName) instanceof JSONObject) {
            return (JSONObject) jsonObject.get(nodeName);
        }
        else {
            return null;
        }
    }

    public static String getAttributeName(String nodeName,String AttributeName) {
        JSONObject testData = getJsonNode(nodeName);
        return testData != null ? (String) testData.get(AttributeName) : null;
    }

}

