package com.test.bookstore.service.impl;

import com.test.bookstore.dto.misc.Item;
import com.test.bookstore.dto.request.InventoryRequest;
import com.test.bookstore.dto.response.InventoryResponse;
import com.test.bookstore.entity.InventoryItem;
import com.test.bookstore.exception.UnexpectedException;
import com.test.bookstore.repository.InventoryRepository;
import com.test.bookstore.service.InventoryService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

  private final InventoryRepository inventoryRepository;

  public InventoryServiceImpl(InventoryRepository inventoryRepository) {
    this.inventoryRepository = inventoryRepository;
  }

  @Override
  public InventoryResponse getInventory(InventoryRequest request) {

    Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());

    Page<InventoryItem> itemsPage = inventoryRepository.findAll(pageable);

    List<Item> itemsList = itemsPage.get().map(
        item -> Item.builder()
            .id(item.getId())
            .name(item.getName())
            .description(item.getDescription())
            .price(item.getPrice())
            .units(item.getUnits()).build())
        .collect(Collectors.toList());

    return new InventoryResponse(itemsList, itemsPage.getTotalElements());
  }
}
