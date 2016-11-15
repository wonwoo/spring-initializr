package me.wonwoo.web;

import me.wonwoo.model.Dependencies;
import me.wonwoo.service.StarterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wonwoo on 2016. 11. 15..
 */
@RestController
public class MainController {

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
}
