package me.wonwoo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by wonwoo on 2016. 11. 15..
 */
@ConfigurationProperties(prefix = "spring.starter")
public class SpringProperties {

  private String url = "http://start.spring.io";

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
