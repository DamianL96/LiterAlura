package com.LiterAtura.Challenge.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIConnection {

  public String connect(String url){
    HttpClient cliente = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

    HttpResponse<String> response = null;

    try {
      response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
      return response.body();

    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());

    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
