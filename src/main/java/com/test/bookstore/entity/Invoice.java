package com.test.bookstore.entity;

import com.test.bookstore.entity.misc.InvoiceItem;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "invoice")
@Data
public class Invoice {

  @Id
  private String id;
  List<InvoiceItem> items;

}
