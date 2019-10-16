package com.test.bookstore.controller;

import com.test.bookstore.dto.request.InventoryRequest;
import com.test.bookstore.dto.response.InventoryResponse;
import com.test.bookstore.service.InventoryService;
import com.test.bookstore.util.ResourceConstants;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ResourceConstants.INVENTORY_RESOURCE)
public class InventoryController {

  private final InventoryService inventoryService;

  public InventoryController(InventoryService inventoryService) {
    this.inventoryService = inventoryService;
  }

  @GetMapping
  @PreAuthorize("#oauth2.hasAnyScope('read')")
  public ResponseEntity<InventoryResponse> getInventory(
      @ModelAttribute("inventoryRequest") @Valid InventoryRequest request) {

    return ResponseEntity.ok(inventoryService.getInventory(request));
  }

  @ModelAttribute("inventoryRequest")
  private InventoryRequest inventoryRequest(
      @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
    return new InventoryRequest(page, pageSize);
  }

}
