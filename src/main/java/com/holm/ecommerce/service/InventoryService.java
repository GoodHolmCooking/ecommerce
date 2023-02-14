package com.holm.ecommerce.service;

import com.holm.ecommerce.pojo.InventoryItem;
import com.holm.ecommerce.repository.InventoryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<InventoryItem> getInventory() {
        return inventoryRepository.findAll();
    }

    public InventoryItem getItem(ObjectId objectId) {
        if (objectId == null) return new InventoryItem();
        return inventoryRepository.findById(objectId).orElse(new InventoryItem());
    }

    public void upsertItem(InventoryItem inventoryItem) {

        mongoTemplate.update(InventoryItem.class)
                .matching(Criteria.where("_id").is(inventoryItem.getObjectId()))
                .apply(new Update().set("name", inventoryItem.getName()))
                .upsert();

        mongoTemplate.update(InventoryItem.class)
                .matching(Criteria.where("_id").is(inventoryItem.getObjectId()))
                .apply(new Update().set("price", inventoryItem.getPrice()))
                .upsert();

        mongoTemplate.update(InventoryItem.class)
                .matching(Criteria.where("_id").is(inventoryItem.getObjectId()))
                .apply(new Update().set("priceString", inventoryItem.getPriceString()))
                .upsert();
    }

    public void deleteItem(ObjectId objectId) {
        inventoryRepository.deleteById(objectId);
    }


}
