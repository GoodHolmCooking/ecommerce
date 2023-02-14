package com.holm.ecommerce.repository;

import com.holm.ecommerce.pojo.InventoryItem;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends MongoRepository<InventoryItem, ObjectId> { }
