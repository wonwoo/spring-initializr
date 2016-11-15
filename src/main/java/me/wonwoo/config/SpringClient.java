package me.wonwoo.config;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wonwoo on 2016. 11. 15..
 */
public class SpringClient {

  private final RestTemplate restTemplate;
  private final CounterService counterService;

  public SpringClient(RestTemplate restTemplate, CounterService counterService) {
    this.restTemplate = restTemplate;
    this.counterService = counterService;
  }

  public <T> ResponseEntity<T> invoke(RequestEntity<?> request, Class<T> type) {
    this.counterService.increment("start.spring.io.requests");
    try {
      return this.restTemplate.exchange(request, type);
    } catch (HttpClientErrorException ex) {
      this.counterService.increment("start.spring.io.failures");
      throw ex;
    }
  }

  public <T> ResponseEntity<T> invoke(RequestEntity<?> request, ParameterizedTypeReference<T> type) {
    this.counterService.increment("start.spring.io.requests");
    try {
      return this.restTemplate.exchange(request, type);
    } catch (HttpClientErrorException ex) {
      this.counterService.increment("start.spring.io.failures");
      throw ex;
    }
  }
}