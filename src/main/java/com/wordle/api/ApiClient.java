package com.wordle.api;

import com.wordle.interfaces.ApiService;
import com.wordle.services.WordService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class ApiClient implements ApiService {

    private final HttpClient httpClient =  HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
    private final WordService wordService = new WordService();


    @Override
    public List<String> callApi() {

        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://trouve-mot.fr/api/size/5"))
                    .header("Accept","application/json")
                    .timeout(Duration.ofSeconds(30))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return wordService.processResponse(response.body());


        }catch(RuntimeException | IOException | InterruptedException e){
            throw new RuntimeException("Erreur lors de l'appel API", e);
        }

    }



}
