package me.wonwoo.model;

import lombok.Data;

/**
 * Created by wonwoo on 2016. 11. 15..
 */

@Data
public class Dependency {
  private String id;
  private String name;
  private String group;
  private String description;
  private int weight;
}
