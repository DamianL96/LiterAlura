package com.LiterAtura.Challenge.services;

public interface ITransoformar {
  <T> T transformar(String json, Class<T> clase);
}
