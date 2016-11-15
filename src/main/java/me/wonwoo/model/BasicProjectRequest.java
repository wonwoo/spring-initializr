package me.wonwoo.model;

import lombok.Data;

import java.util.List;

/**
 * Created by wonwoo on 2016. 11. 15..
 */
@Data
public class BasicProjectRequest {
  private List<String> style;
  private List<String> dependencies;
  private String name;
  private String type;
  private String description;
  private String groupId;
  private String artifactId;
  private String version;
  private String bootVersion;
  private String packaging;
  private String applicationName;
  private String language;
  private String packageName;
  private String javaVersion;
  private String baseDir;
}
