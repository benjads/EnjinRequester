package com.horusdev.enjinrequester.request;

import com.horusdev.enjinrequester.EnjinRequester;
import com.horusdev.enjinrequester.auth.EnjinAuth;
import com.horusdev.enjinrequester.enums.RequestResult;
import com.horusdev.enjinrequester.util.Debugger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * A simplification of making HTTP connections for requests
 *
 * @author Ben S (HorusDev)
 */
@SuppressWarnings({"unchecked", "unused"})
public class PreparedConnection {

    private HttpURLConnection connection;

    private Object result;
    private RequestResult outcome;

    /**
     * Creates a new PreparedConnection and sends the request, but does not process the response
     *
     * @param method The method of Enjin's API
     * @param auth An acceptable authentication (approved by the API method)
     * @param params What's in the parameters of the request
     * @throws IOException
     */
    public PreparedConnection(String method, EnjinAuth auth, JSONObject params) throws IOException, ParseException {
        String id = UUID.randomUUID().toString().substring(0, 10);

        URL url = EnjinRequester.getUrl();

        connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("POST");

        JSONObject parent = new JSONObject();

        parent.put("jsonrpc", "2.0");
        parent.put("id", id);
        parent.put("method", method);

        params.put(auth.getJsonKey(), auth.getString());

        parent.put("params", params);

        Debugger.info("Request is " + parent.toString());

        OutputStreamWriter outputStreamWriter= new OutputStreamWriter(connection.getOutputStream());
        outputStreamWriter.write(parent.toString());
        outputStreamWriter.flush();

        InputStream inputStream = connection.getInputStream();
        String rawJson = parseInput(inputStream);
        JSONParser parser = new JSONParser();
        Object parsedJson = parser.parse(rawJson);

        if (!(parsedJson instanceof JSONObject))
            return;

        JSONObject response = (JSONObject) parsedJson;

        Debugger.info(String.format("Response was %s", response.toString()));

        if (response.containsKey("error")) {
            Debugger.warning("The response contained error in PreparedConnection");

            JSONObject error = (JSONObject) response.get("error");

            outcome = RequestResult.getError(Integer.parseInt(String.valueOf(error.get("code"))));
            switch (outcome) {
                case INVALID_PARAMS:
                    Debugger.warning(String.format("%s failed due to invalid params", method));
                    return;
                case AUTH_FAIL:
                    Debugger.warning(String.format("%s failed due to a bad auth key", method));
                    return;
            }
        }

        if (response.get("id") == null || !response.get("id").equals(id)) {
            Debugger.warning("Either ID is null the request doesn't match in PreparedConnection");
            return;
        }

        result = response.get("result");

        if (result instanceof JSONObject) {
            if (String.valueOf(result).equals("")) {
                Debugger.warning("No result found in method");
                outcome = RequestResult.INVALID_PARAMS;
            }
        }

        outcome = RequestResult.SUCCESS;
    }

    /**
     * Receive the response of the request
     *
     * @return the JSON object containing the results
     */
    public Object getResponse() {
        return result;
    }

    public boolean isSuccessful() {
        return !outcome.isError();
    }

    public RequestResult getResult() {
        return outcome;
    }

    /**
     * Parses an InputStream into a usable String
     *
     * @param in The InputStream
     * @return the parsed result
     * @throws IOException
     */
    private static String parseInput(InputStream in) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead = in.read(buffer);
        StringBuilder sb = new StringBuilder();
        while (bytesRead > 0) {
            sb.append(new String(buffer, 0, bytesRead, "UTF-8"));
            bytesRead = in.read(buffer);
        }
        return sb.toString();
    }

    public HttpURLConnection getConnection() {
        return connection;
    }
}
