package com.LiterAtura.Challenge.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransformJsonToClass implements ITransoformar{
  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public <T> T transformar(String json, Class<T> clase){
    try{
      return objectMapper.readValue(json, clase);

    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
