package com.holm.ecommerce.controller;

import com.holm.ecommerce.pojo.InventoryItem;
import com.holm.ecommerce.service.InventoryService;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/inventory")
    public String getInventory(Model model) {
        model.addAttribute("inventory", inventoryService.getInventory());
        return "inventory";
    }

    @GetMapping
    public String getForm(Model model, @RequestParam(required = false) ObjectId objectId) {
        model.addAttribute("inventoryItem", inventoryService.getItem(objectId));
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(@Valid InventoryItem inventoryItem, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error);
            }
            return "form";
        }
        inventoryService.upsertItem(inventoryItem);
        return "redirect:/inventory";
    }

    @GetMapping("/delete/{objectId}")
    public String deleteItem(@PathVariable("objectId") ObjectId objectId, Model model) {
        inventoryService.deleteItem(objectId);
        return "redirect:/inventory";
    }

}
