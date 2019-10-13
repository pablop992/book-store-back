package com.test.bookstore.service;

import com.test.bookstore.dto.request.InvoiceRequest;

public interface InvoiceService {

  void saveInvoice(InvoiceRequest request);

}
