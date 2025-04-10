package com.example.CRB.service;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HttpHandler {

    private final OkHttpClient client;

    public HttpHandler() {
        this.client = new OkHttpClient();
    }

    public String postApi(String requestBody) {
        String url = "https://secure7.transunionafrica.com:443/crbws/rw";

        MediaType mediaType = MediaType.parse("text/xml; charset=utf-8");
        RequestBody body = RequestBody.create(requestBody, mediaType);

        String username = "RWFq7NE3vz";
        String password = "WxB4sZQXDyUaxL";
        String credential = Credentials.basic(username, password);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Authorization", credential)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                return "Request failed with status code: " + response.code();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error making request: " + e.getMessage();
        }
    }
}
