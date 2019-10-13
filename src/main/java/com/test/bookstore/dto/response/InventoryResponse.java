package com.test.bookstore.dto.response;

import com.test.bookstore.dto.misc.Item;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InventoryResponse {

  private List<Item> availableItems;
  private Long totalElements;

}
