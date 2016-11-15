package me.wonwoo.service;

import me.wonwoo.config.SpringClient;
import me.wonwoo.config.SpringProperties;
import me.wonwoo.model.Dependencies;
import me.wonwoo.model.Dependency;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Created by wonwoo on 2016. 11. 15..
 */
@Component
public class StarterService {

  private final SpringClient springClient;
  private final SpringProperties springProperties;

  public StarterService(SpringClient springClient, SpringProperties springProperties) {
    this.springClient = springClient;
    this.springProperties = springProperties;
  }

  public Dependencies dependencies(String version) {
    return springClient.invoke(
      createRequestEntity(
        createURIBuilder("/ui/dependencies")
          .queryParam("version", version)
          .build()
          .toUri()),
      Dependencies.class).getBody();
  }


  public String metadataConfig() {
    return springClient.invoke(
      createRequestEntity(
        createURIBuilder("/metadata/config")
          .build()
          .toUri()),
      String.class).getBody();

  }

  private UriComponentsBuilder createURIBuilder(String path) {
    return UriComponentsBuilder.fromHttpUrl(springProperties.getUrl()).path(path);
  }

  private RequestEntity<?> createRequestEntity(URI uri) {
    return this.createRequestEntity(uri, MediaType.APPLICATION_JSON);
  }

  private RequestEntity<?> createRequestEntity(URI uri, MediaType mediaType) {
    return RequestEntity.get(uri)
      .accept(mediaType).build();
  }

}
