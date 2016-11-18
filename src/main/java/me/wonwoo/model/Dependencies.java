package me.wonwoo.model;

import java.util.List;

/**
 * Created by wonwoo on 2016. 11. 15..
 */
public class Dependencies {
  private List<Dependency> dependencies;


  public List<Dependency> getDependencies() {
    return dependencies;
  }

  public void setDependencies(List<Dependency> dependencies) {
    this.dependencies = dependencies;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Dependencies that = (Dependencies) o;

    return dependencies != null ? dependencies.equals(that.dependencies) : that.dependencies == null;

  }

  @Override
  public int hashCode() {
    return dependencies != null ? dependencies.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "Dependencies{" +
            "dependencies=" + dependencies +
            '}';
  }
}
