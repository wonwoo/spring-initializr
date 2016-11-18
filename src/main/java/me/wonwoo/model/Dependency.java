package me.wonwoo.model;

/**
 * Created by wonwoo on 2016. 11. 15..
 */

public class Dependency {
  private String id;
  private String name;
  private String group;
  private String description;
  private int weight;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Dependency that = (Dependency) o;

    if (weight != that.weight) return false;
    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (group != null ? !group.equals(that.group) : that.group != null) return false;
    return description != null ? description.equals(that.description) : that.description == null;

  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (group != null ? group.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + weight;
    return result;
  }

  @Override
  public String toString() {
    return "Dependency{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", group='" + group + '\'' +
            ", description='" + description + '\'' +
            ", weight=" + weight +
            '}';
  }
}
