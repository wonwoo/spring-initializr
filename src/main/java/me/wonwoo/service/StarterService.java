package me.wonwoo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.wonwoo.config.SpringClient;
import me.wonwoo.config.SpringProperties;
import me.wonwoo.mapper.InitializrMetadataVersion;
import me.wonwoo.model.BasicProjectRequest;
import me.wonwoo.model.Dependencies;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
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
  private final ObjectMapper objectMapper;

  public StarterService(SpringClient springClient, SpringProperties springProperties, ObjectMapper objectMapper) {
    this.springClient = springClient;
    this.springProperties = springProperties;
    this.objectMapper = objectMapper;
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
  //TODO 좀더 간결하게..
  public ResponseEntity<byte[]> springZip(BasicProjectRequest basicRequest) {
    return springClient.invoke(
      createRequestEntity(
        createURIBuilder("/starter.zip")
          .queryParam("applicationName", basicRequest.getApplicationName())
          .queryParam("artifactId", basicRequest.getArtifactId())
          .queryParam("baseDir", basicRequest.getBaseDir())
          .queryParam("bootVersion", basicRequest.getBootVersion())
          .queryParam("dependencies", basicRequest.getDependencies())
          .queryParam("description", basicRequest.getDescription())
          .queryParam("groupId(", basicRequest.getGroupId())
          .queryParam("javaVersion", basicRequest.getJavaVersion())
          .queryParam("language", basicRequest.getLanguage())
          .queryParam("name", basicRequest.getName())
          .queryParam("packageName", basicRequest.getPackageName())
          .queryParam("style", basicRequest.getStyle())
          .queryParam("type", basicRequest.getType())
          .queryParam("version", basicRequest.getVersion())
          .build()
          .toUri()), byte[].class);

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

  public ResponseEntity<byte[]> springTgz(BasicProjectRequest basicRequest) {
    return springClient.invoke(
    createRequestEntity(
      createURIBuilder("/starter.tgz")
        .queryParam("applicationName", basicRequest.getApplicationName())
        .queryParam("artifactId", basicRequest.getArtifactId())
        .queryParam("baseDir", basicRequest.getBaseDir())
        .queryParam("bootVersion", basicRequest.getBootVersion())
        .queryParam("dependencies", basicRequest.getDependencies())
        .queryParam("description", basicRequest.getDescription())
        .queryParam("groupId(", basicRequest.getGroupId())
        .queryParam("javaVersion", basicRequest.getJavaVersion())
        .queryParam("language", basicRequest.getLanguage())
        .queryParam("name", basicRequest.getName())
        .queryParam("packageName", basicRequest.getPackageName())
        .queryParam("style", basicRequest.getStyle())
        .queryParam("type", basicRequest.getType())
        .queryParam("version", basicRequest.getVersion())
        .build()
        .toUri(),MediaType.parseMediaType("application/x-compress")), byte[].class);
  }

  public ResponseEntity<byte[]> pom(BasicProjectRequest basicRequest) {
    return springClient.invoke(
      createRequestEntity(
        createURIBuilder("/pom")
          .queryParam("applicationName", basicRequest.getApplicationName())
          .queryParam("artifactId", basicRequest.getArtifactId())
          .queryParam("baseDir", basicRequest.getBaseDir())
          .queryParam("bootVersion", basicRequest.getBootVersion())
          .queryParam("dependencies", basicRequest.getDependencies())
          .queryParam("description", basicRequest.getDescription())
          .queryParam("groupId(", basicRequest.getGroupId())
          .queryParam("javaVersion", basicRequest.getJavaVersion())
          .queryParam("language", basicRequest.getLanguage())
          .queryParam("name", basicRequest.getName())
          .queryParam("packageName", basicRequest.getPackageName())
          .queryParam("style", basicRequest.getStyle())
          .queryParam("type", basicRequest.getType())
          .queryParam("version", basicRequest.getVersion())
          .build()
          .toUri()), byte[].class);
  }

  public ResponseEntity<byte[]> build(BasicProjectRequest basicRequest) {
    return springClient.invoke(
      createRequestEntity(
        createURIBuilder("/build")
          .queryParam("applicationName", basicRequest.getApplicationName())
          .queryParam("artifactId", basicRequest.getArtifactId())
          .queryParam("baseDir", basicRequest.getBaseDir())
          .queryParam("bootVersion", basicRequest.getBootVersion())
          .queryParam("dependencies", basicRequest.getDependencies())
          .queryParam("description", basicRequest.getDescription())
          .queryParam("groupId(", basicRequest.getGroupId())
          .queryParam("javaVersion", basicRequest.getJavaVersion())
          .queryParam("language", basicRequest.getLanguage())
          .queryParam("name", basicRequest.getName())
          .queryParam("packageName", basicRequest.getPackageName())
          .queryParam("style", basicRequest.getStyle())
          .queryParam("type", basicRequest.getType())
          .queryParam("version", basicRequest.getVersion())
          .build()
          .toUri()), byte[].class);
  }

  public ResponseEntity<String> serviceCapabilitiesFor(MediaType contentType) {
    return springClient.invoke(
        createRequestEntity(
          createURIBuilder("/")
            .build()
            .toUri(),contentType), String.class);
  }

  public ResponseEntity<byte[]> spring() {
    return springClient.invoke(
        createRequestEntity(
            createURIBuilder("/spring")
              .build()
              .toUri()), byte[].class);
  }
  public ResponseEntity<byte[]> springTgz() {
    return springClient.invoke(
      createRequestEntity(
        createURIBuilder("/spring.tar.gz")
          .build()
          .toUri()), byte[].class);
  }
}
