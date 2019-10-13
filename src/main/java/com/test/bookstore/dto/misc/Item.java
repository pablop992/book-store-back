package com.test.bookstore.dto.misc;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {

  private String id;
  private String name;
  private String description;
  private Double price;
  private Long units;
}
