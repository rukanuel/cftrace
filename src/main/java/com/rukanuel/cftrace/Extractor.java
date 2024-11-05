package com.rukanuel.cftrace;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code Extractor} class provides utility methods for extracting specific values from strings,
 * such as parsing JSON strings for specific keys or extracting key-value pairs from formatted strings.
 */
public class Extractor {

    /**
     * Extracts the value associated with a specified key from a JSON string.
     * The method assumes that the JSON string follows a simple key-value format and that
     * the key and value are enclosed in double quotes.
     *
     * @param json The JSON string from which to extract the value.
     * @param key The key whose associated value is to be extracted.
     * @return The value associated with the specified key, or {@code null} if the key is not found.
     */
    public static String JSON(String json, String key) {
        String searchKey = "\"" + key + "\":\"";
        int startIndex = json.indexOf(searchKey);
        if (startIndex == -1) {
            return null; // Key not found
        }
        startIndex += searchKey.length();
        int endIndex = json.indexOf("\"", startIndex);
        return json.substring(startIndex, endIndex);
    }

    /**
     * Extracts key-value pairs from a string formatted with key-value syntax (e.g., "key=value").
     * This method uses regular expressions to find and extract all key-value pairs in the input string.
     * <p>
     * <b>Note:</b> This method is not currently functioning correctly and is marked as non-functional.
     * </p>
     *
     * @param input The input string containing key-value pairs.
     * @return A map of keys and their corresponding values extracted from the input string.
     */
    static Map<String, String> Keys(String input) {
        Map<String, String> keyValueMap = new HashMap<>();

        // Define a pattern to match key-value pairs (e.g., key=value)
        Pattern pattern = Pattern.compile("([^=\\s]+)=([^=\\s]+)");
        Matcher matcher = pattern.matcher(input);

        // Extract each key-value pair
        while (matcher.find()) {
            String key = matcher.group(1).trim();
            String value = matcher.group(2).trim();
            keyValueMap.put(key, value);
        }
        return keyValueMap;
    }
}
