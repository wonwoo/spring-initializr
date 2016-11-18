package me.wonwoo.model;

import java.util.List;

/**
 * Created by wonwoo on 2016. 11. 15..
 */
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

  public List<String> getStyle() {
    return style;
  }

  public void setStyle(List<String> style) {
    this.style = style;
  }

  public List<String> getDependencies() {
    return dependencies;
  }

  public void setDependencies(List<String> dependencies) {
    this.dependencies = dependencies;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public String getArtifactId() {
    return artifactId;
  }

  public void setArtifactId(String artifactId) {
    this.artifactId = artifactId;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getBootVersion() {
    return bootVersion;
  }

  public void setBootVersion(String bootVersion) {
    this.bootVersion = bootVersion;
  }

  public String getPackaging() {
    return packaging;
  }

  public void setPackaging(String packaging) {
    this.packaging = packaging;
  }

  public String getApplicationName() {
    return applicationName;
  }

  public void setApplicationName(String applicationName) {
    this.applicationName = applicationName;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public String getJavaVersion() {
    return javaVersion;
  }

  public void setJavaVersion(String javaVersion) {
    this.javaVersion = javaVersion;
  }

  public String getBaseDir() {
    return baseDir;
  }

  public void setBaseDir(String baseDir) {
    this.baseDir = baseDir;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BasicProjectRequest that = (BasicProjectRequest) o;

    if (style != null ? !style.equals(that.style) : that.style != null) return false;
    if (dependencies != null ? !dependencies.equals(that.dependencies) : that.dependencies != null) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (type != null ? !type.equals(that.type) : that.type != null) return false;
    if (description != null ? !description.equals(that.description) : that.description != null) return false;
    if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
    if (artifactId != null ? !artifactId.equals(that.artifactId) : that.artifactId != null) return false;
    if (version != null ? !version.equals(that.version) : that.version != null) return false;
    if (bootVersion != null ? !bootVersion.equals(that.bootVersion) : that.bootVersion != null) return false;
    if (packaging != null ? !packaging.equals(that.packaging) : that.packaging != null) return false;
    if (applicationName != null ? !applicationName.equals(that.applicationName) : that.applicationName != null)
      return false;
    if (language != null ? !language.equals(that.language) : that.language != null) return false;
    if (packageName != null ? !packageName.equals(that.packageName) : that.packageName != null) return false;
    if (javaVersion != null ? !javaVersion.equals(that.javaVersion) : that.javaVersion != null) return false;
    return baseDir != null ? baseDir.equals(that.baseDir) : that.baseDir == null;

  }

  @Override
  public int hashCode() {
    int result = style != null ? style.hashCode() : 0;
    result = 31 * result + (dependencies != null ? dependencies.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (type != null ? type.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
    result = 31 * result + (artifactId != null ? artifactId.hashCode() : 0);
    result = 31 * result + (version != null ? version.hashCode() : 0);
    result = 31 * result + (bootVersion != null ? bootVersion.hashCode() : 0);
    result = 31 * result + (packaging != null ? packaging.hashCode() : 0);
    result = 31 * result + (applicationName != null ? applicationName.hashCode() : 0);
    result = 31 * result + (language != null ? language.hashCode() : 0);
    result = 31 * result + (packageName != null ? packageName.hashCode() : 0);
    result = 31 * result + (javaVersion != null ? javaVersion.hashCode() : 0);
    result = 31 * result + (baseDir != null ? baseDir.hashCode() : 0);
    return result;
  }


}
