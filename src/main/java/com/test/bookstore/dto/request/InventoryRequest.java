package com.test.bookstore.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InventoryRequest {

  @NotNull
  @Min(1)
  private Integer page;
  @NotNull
  @Min(1)
  private Integer pageSize;

}
