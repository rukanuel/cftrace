package com.rukanuel.cftrace;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

class HTTP {

    // Logger for logging errors and informational messages
    private static final Logger logger = Logger.getLogger(HTTP.class.getName());

    /**
     * Performs a GET request to the specified URL and returns the response as a string.
     * The method handles errors gracefully and logs detailed error messages.
     *
     * @param urlString The URL to send the GET request to.
     * @return The response from the GET request as a string. If an error occurs, an error message is returned.
     */
    static String GET(String urlString) {
        try {
            // Validate URL format
            URI uri = new URI(urlString);
            URL url = uri.toURL();

            // Open HTTP connection and set method to GET
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response from the input stream
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            return response.toString();
        } catch (Exception e) {
            // Log detailed error information
            logger.log(Level.SEVERE, "Error occurred while making GET request to: " + urlString, e);
            return "Error: " + e.getMessage();
        }
    }
}
