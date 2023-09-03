package com.example.currencyconverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

public class CurrencyConversionServiceAPI extends BaseController{
    private static final String RAPIDAPI_KEY = "1bf827bd76msha477f0334f0651ap175055jsnd38e77280d8a";
    private static final String RAPIDAPI_HOST = "currency-converter-by-api-ninjas.p.rapidapi.com";

    protected static void handleJsonResponse(String jsonResponse) {

        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            conversionResult = jsonObject.getDouble("new_amount");

        } catch (JSONException e) {
            System.err.println("La respuesta no es un JSON válido.");

        }
    }

        protected static void sendingCurrencyRequest(String origin, String destination, String amount){
            try {
                String response = sendGetRequest(origin, destination, amount);
                handleJsonResponse(response);

            } catch (IOException e) {
                e.printStackTrace();

            }


        }

    protected static String sendGetRequest(String have, String want, String amount) throws IOException {
        StringBuilder response = new StringBuilder();
        HttpURLConnection connection = null;

        try {
            String apiUrl = "https://api.api-ninjas.com/v1/convertcurrency?want=" + want + "&have=" + have + "&amount=" + amount;
            URL url = new URL(apiUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-RapidAPI-Key", RAPIDAPI_KEY);
            connection.setRequestProperty("X-RapidAPI-Host", RAPIDAPI_HOST);
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);

                }
                reader.close();

            } else {
                throw new RuntimeException("Error en la solicitud. Código de respuesta: " + responseCode);

            }
        } finally {
            if (connection != null) {
                connection.disconnect();

            }
        }
        return response.toString();

    }


}

