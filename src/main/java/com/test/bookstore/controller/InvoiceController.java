package com.test.bookstore.controller;

import com.test.bookstore.dto.request.InvoiceRequest;
import com.test.bookstore.dto.response.InventoryResponse;
import com.test.bookstore.service.InventoryService;
import com.test.bookstore.service.InvoiceService;
import com.test.bookstore.util.ResourceConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ResourceConstants.INVOICE_RESOURCE)
public class InvoiceController {

  private final InvoiceService invoiceService;

  public InvoiceController(InvoiceService invoiceService) {
    this.invoiceService = invoiceService;
  }

  @PostMapping
  public ResponseEntity saveInvoice(@RequestBody InvoiceRequest invoiceRequest) {
    invoiceService.saveInvoice(invoiceRequest);
    return ResponseEntity.accepted().build();
  }

}
