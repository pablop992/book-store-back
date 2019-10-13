package com.test.bookstore.service.impl;

import com.test.bookstore.dto.misc.InvoiceItem;
import com.test.bookstore.dto.request.InvoiceRequest;
import com.test.bookstore.entity.InventoryItem;
import com.test.bookstore.entity.Invoice;
import com.test.bookstore.mapper.InvoiceItemMapper;
import com.test.bookstore.repository.InventoryRepository;
import com.test.bookstore.repository.InvoiceRepository;
import com.test.bookstore.service.InvoiceService;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

  private final InvoiceRepository invoiceRepository;
  private final InventoryRepository inventoryRepository;
  private final InvoiceItemMapper invoiceItemMapper;

  public InvoiceServiceImpl(InvoiceRepository invoiceRepository,
      InventoryRepository inventoryRepository,
      InvoiceItemMapper invoiceItemMapper) {
    this.invoiceRepository = invoiceRepository;
    this.inventoryRepository = inventoryRepository;
    this.invoiceItemMapper = invoiceItemMapper;
  }

  @Override
  public void saveInvoice(InvoiceRequest request) {
    Invoice invoice = new Invoice();
    invoice.setItems(request.getItemsToBuy().stream()
        .map(item -> invoiceItemMapper.invoiceItemToDTO(item))
        .collect(Collectors.toList()));

    invoiceRepository.save(invoice);

    updateItemsUnits(request.getItemsToBuy());
  }

  @Async
  protected void updateItemsUnits(List<InvoiceItem> items) {

    Map<String, Long> invoiceItemsMap = items.stream().collect(
        Collectors.toMap(InvoiceItem::getItemId, InvoiceItem::getUnits));

    List<InventoryItem> dbItems = items.parallelStream()
        .map(item -> inventoryRepository.findById(item.getItemId()).orElse(InventoryItem.empty()))
        .collect(Collectors.toList());

    Iterator<InventoryItem> iterator = dbItems.iterator();

    while(iterator.hasNext()) {

      InventoryItem item = iterator.next();

      if(StringUtils.isEmpty(item.getId())) {
        iterator.remove();
        continue;
      }

      Long qtyToSubtract = invoiceItemsMap.get(item.getId());
      item.setUnits(item.getUnits() - qtyToSubtract >= 0 ? item.getUnits() - qtyToSubtract : 0);
    }

    inventoryRepository.saveAll(dbItems);

  }
}
