package com.test.bookstore.mapper;

import com.test.bookstore.entity.misc.InvoiceItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface InvoiceItemMapper {

  @Mapping(source = "itemId", target = "id")
  InvoiceItem invoiceItemToDTO(com.test.bookstore.dto.misc.InvoiceItem source);

}
