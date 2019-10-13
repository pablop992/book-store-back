package com.test.bookstore.repository;

import com.test.bookstore.entity.InventoryItem;
import com.test.bookstore.entity.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, String> {

}
