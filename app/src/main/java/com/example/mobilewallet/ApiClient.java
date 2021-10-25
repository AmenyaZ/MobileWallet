package com.example.mobilewallet;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ApiClient {

    private String baseUrl;
    private String CustomerID;
    private String CustomerPin;
    private String urlResource;
    private String httpMethod; // GET, POST, PUT, DELETE
    private String urlPath;
    private String lastResponse;
    private String payload;
    private HashMap<String, String> parameters;
    private Map<String, List<String>> headerFields;

    public ApiClient(String  baseUrl, String username, String password) {
        setBaseUrl(baseUrl);
        this.CustomerID = CustomerID;
        this.CustomerPin = CustomerPin;
        this.urlResource = "";
        this.urlPath = "";
        this.httpMethod = "GET";
        parameters = new HashMap<>();
        lastResponse = "";
        payload = "";
        headerFields = new HashMap<>();
        // This is important. The application may break without this line.
        System.setProperty("jsse.enableSNIExtension", "false");
    }
    public ApiClient setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        if (!baseUrl.substring(baseUrl.length() - 1).equals("/")) {
            this.baseUrl += "/";
        }
        return this;
    }
    public ApiClient setUrlResource(String urlResource) {
        this.urlResource = urlResource;
        return this;
    }
    public final ApiClient setUrlPath(String urlPath) {
        this.urlPath = urlPath;
        return this;
    }
    public ApiClient setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }
    public String getLastResponse() {
        return lastResponse;
    }
    public Map<String, List<String>> getHeaderFields() {
        return headerFields;
    }
    public ApiClient setParameters(HashMap<String, String> parameters) {
        this.parameters = parameters;
        return this;
    }
    public ApiClient setParameter(String key, String value) {
        this.parameters.put(key, value);
        return this;
    }
    public ApiClient clearParameters() {
        this.parameters.clear();
        return this;
    }
    public ApiClient removeParameter(String key) {
        this.parameters.remove(key);
        return this;
    }

    /**
     * Deletes all values used to make Rest API calls.
     * @return this
     */
    public ApiClient clearAll() {
        parameters.clear();
        baseUrl = "";
        this.CustomerID = "";
        this.CustomerPin = "";
        this.urlResource = "";
        this.urlPath = "";
        this.httpMethod = "";
        lastResponse = "";
        payload = "";
        headerFields.clear();
        return this;
    }
    public JSONObject getLastResponseAsJsonObject() {
        try {
            return new JSONObject(String.valueOf(lastResponse));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the last response from the Rest API as a JSON Array.
     * @return JSONArray
     */
    public JSONArray getLastResponseAsJsonArray() {
        try {
            return new JSONArray(String.valueOf(lastResponse));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getPayloadAsString() {
        // Cycle through the parameters.
        StringBuilder stringBuffer = new StringBuilder();
        Iterator it = parameters.entrySet().iterator();
        int count = 0;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (count > 0) {
                stringBuffer.append("&");
            }
            stringBuffer.append(pair.getKey()).append("=").append(pair.getValue());

            it.remove(); // avoids a ConcurrentModificationException
            count++;
        }
        return stringBuffer.toString();
    }

    public String execute() {
        String line;
        StringBuilder outputStringBuilder = new StringBuilder();

        try {
            StringBuilder urlString = new StringBuilder(baseUrl + urlResource);

            if (!urlPath.equals("")) {
                urlString.append("/" + urlPath);
            }

            if (parameters.size() > 0 && httpMethod.equals("GET")) {
                payload = getPayloadAsString();
                urlString.append("?" + payload);
            }

            URL url = new URL(urlString.toString());

          //  String encoding = Base64Encoder.encode(CustomerID + ":" + CustomerPin);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(httpMethod);
         //   connection.setRequestProperty("Authorization", "Basic " + encoding);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "text/plain");

            // Make the network connection and retrieve the output from the server.
            if (httpMethod.equals("POST") || httpMethod.equals("PUT")) {

                payload = getPayloadAsString( );

                connection.setDoInput(true);
                connection.setDoOutput(true);

                try {
                    OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
                    writer.write(payload);

                    headerFields = connection.getHeaderFields();

                    Reader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while ((line = ((BufferedReader) br).readLine()) != null) {
                        outputStringBuilder.append(line);
                    }
                } catch (Exception ex) {}
                connection.disconnect();
            }
            else {
                InputStream content = (InputStream) connection.getInputStream();

                headerFields = connection.getHeaderFields();

                //connection.
                BufferedReader in = new BufferedReader(new InputStreamReader(content));

                while ((line = in.readLine()) != null) {
                    outputStringBuilder.append(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // If the outputStringBuilder is blank, the call failed.
        if (!outputStringBuilder.toString().equals("")) {
            lastResponse = outputStringBuilder.toString();
        }

        return outputStringBuilder.toString();
    }

}
