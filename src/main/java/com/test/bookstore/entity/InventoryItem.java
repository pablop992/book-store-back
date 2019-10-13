package com.test.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryItem {

  @Id
  private String id;
  private String name;
  private String description;
  private Double price;
  private Long units;

  public static InventoryItem empty() {
    return new InventoryItem();
  }

}
