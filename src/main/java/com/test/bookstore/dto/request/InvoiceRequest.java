package com.test.bookstore.dto.request;

import com.test.bookstore.dto.misc.InvoiceItem;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvoiceRequest {

  private List<InvoiceItem> itemsToBuy;


}
