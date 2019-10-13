package com.test.bookstore.dto.misc;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class InvoiceItem {

  @NotEmpty
  private String itemId;
  @NotNull
  private Long units;

}
