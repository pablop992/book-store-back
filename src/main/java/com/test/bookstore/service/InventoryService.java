package com.test.bookstore.service;

import com.test.bookstore.dto.request.InventoryRequest;
import com.test.bookstore.dto.response.InventoryResponse;

public interface InventoryService {

  InventoryResponse getInventory(InventoryRequest request);

}
