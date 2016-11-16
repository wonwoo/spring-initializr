package me.wonwoo.web;

import me.wonwoo.mapper.InitializrMetadataVersion;
import me.wonwoo.model.BasicProjectRequest;
import me.wonwoo.model.Dependencies;
import me.wonwoo.service.StarterService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wonwoo on 2016. 11. 15..
 */
@RestController
public class MainController {

  private static final MediaType HAL_JSON_CONTENT_TYPE = MediaType.parseMediaType("application/hal+json");


  private final StarterService service;

  public MainController(StarterService service) {
    this.service = service;
  }

  @GetMapping("/")
  public Dependencies home(@RequestParam(required = false) String version) {
    return service.dependencies(version);
  }

  @GetMapping("/metadata/config")
  public String metadataConfig(){
    return service.metadataConfig();
  }

  @GetMapping("/starter.zip")
  public ResponseEntity<byte[]> springZip(BasicProjectRequest basicRequest) {
    return service.springZip(basicRequest);
  }

  @GetMapping("/starter.tgz")
  public ResponseEntity<byte[]> springTgz(BasicProjectRequest basicRequest) {
    return service.springTgz(basicRequest);
  }

  @GetMapping("/pom")
  public ResponseEntity<byte[]> pom(BasicProjectRequest basicRequest) {
    return service.pom(basicRequest);
  }

  @GetMapping("/build")
  public ResponseEntity<byte[]> build(BasicProjectRequest basicRequest) {
    return service.build(basicRequest);
  }

  @GetMapping(value = "/", produces = "application/hal+json")
  public ResponseEntity<String> serviceCapabilitiesHal() {
    return service.serviceCapabilitiesFor(HAL_JSON_CONTENT_TYPE);
  }

  @GetMapping(value = "/", produces = {"application/vnd.initializr.v2.1+json", "application/json"})
  public ResponseEntity<String> serviceCapabilitiesV21() {
    return service.serviceCapabilitiesFor(InitializrMetadataVersion.V2_1.getMediaType());
  }

  @GetMapping(value = "/", produces = "application/vnd.initializr.v2+json")
  public ResponseEntity<String> serviceCapabilitiesV2() {
    return service.serviceCapabilitiesFor(InitializrMetadataVersion.V2.getMediaType());
  }
}
