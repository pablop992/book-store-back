package com.test.bookstore.repository;

import com.test.bookstore.entity.InventoryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends PagingAndSortingRepository<InventoryItem, String> {


}
