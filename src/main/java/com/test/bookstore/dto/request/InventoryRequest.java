package com.test.bookstore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InventoryRequest {

  private Integer page;
  private Integer pageSize;

}
