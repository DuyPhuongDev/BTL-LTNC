package com.ltnc.be.service.impl;

import com.ltnc.be.service.JsonService;
import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.GsonBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonServiceImpl implements JsonService {
  private static final Gson GSON = new GsonBuilder().create();

  @Override
  public <T> String toJson(T obj) {
    return GSON.toJson(obj);
  }

  @Override
  public <T> T fromJSON(String json, Class<T> clazz) {
    return GSON.fromJson(json, clazz);
  }
}
