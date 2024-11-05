package com.rukanuel.cftrace;

import java.io.IOException;
import java.util.Map;

/**
 * The {@code CfTrace} class provides methods to interact with various Cloudflare services
 * and retrieve trace data from different providers.
 * This class supports tracing using multiple providers, as well as retrieving Geo-related data.
 */
public class CfTrace {

    /**
     * Retrieves trace information from a specified provider.
     * The trace data is retrieved via an HTTP GET request to the provider's endpoint.
     * The data is parsed into a key-value map for further processing.
     *
     * @param provider The name of the provider to fetch trace data from. Accepted values include:
     *                 "101", "cloudflare", "cloudflare-eth", "workers", "pages", "tv", "icanhazip".
     *                 If an invalid provider is provided, a default provider (https://one.one.one.one/cdn-cgi/trace) is used.
     * @param key An optional key to extract specific information from the trace data.
     *            If {@code key} is provided, the corresponding value is returned. If the key is not found,
     *            a "Key not found" message is returned.
     * @return A string representation of the trace data or the value for the given key, if provided.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws InterruptedException If the HTTP request is interrupted.
     */
    public static String Trace(String provider, String key) throws IOException, InterruptedException {
        String data = switch (provider) {
            case "101" -> HTTP.GET("https://1.0.0.1/cdn-cgi/trace");
            case "cloudflare" -> HTTP.GET("https://cloudflare-dns.com/cdn-cgi/trace");
            case "cloudflare-eth" -> HTTP.GET("https://cloudflare-eth.com/cdn-cgi/trace");
            case "workers" -> HTTP.GET("https://workers.dev/cdn-cgi/trace");
            case "pages" -> HTTP.GET("https://pages.dev/cdn-cgi/trace");
            case "tv" -> HTTP.GET("https://cloudflare.tv/cdn-cgi/trace");
            case "icanhazip" -> HTTP.GET("https://icanhazip.com/cdn-cgi/trace");
            default -> HTTP.GET("https://one.one.one.one/cdn-cgi/trace");
        };

        Map<String, String> keyValueMap = Extractor.Keys(data);

        return keyValueMap.toString();
    }

    /**
     * Retrieves Geo-related data from Cloudflare's speed meta endpoint.
     * If a specific key is provided, the corresponding value from the JSON response is returned.
     * Otherwise, the full JSON response is returned.
     *
     * @param key The specific key to extract from the JSON response. If empty, the full JSON response is returned.
     * @return The value for the specified key, or the full JSON response if the key is not provided.
     */
    public static String Geo(String key) {
        String jsonResponse = HTTP.GET("https://speed.cloudflare.com/meta");

        if (key != null && !key.isEmpty()) {
            return Extractor.JSON(jsonResponse, key);
        }
        return jsonResponse;
    }

    /**
     * Retrieves the full Geo-related data from Cloudflare's speed meta endpoint.
     * This method does not require a key and returns the full JSON response.
     *
     * @return The full JSON response from the speed meta endpoint.
     */
    public static String Geo() {
        return Geo("");
    }

    /**
     * Retrieves trace data from the default provider with no specific key.
     * This method is a convenience method that calls {@link #Trace(String, String)} with empty parameters.
     *
     * @return The trace data from the default provider as a string.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws InterruptedException If the HTTP request is interrupted.
     */
    public static String Trace() throws IOException, InterruptedException {
        return Trace("","");
    }
}
