package com.holm.ecommerce.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

@Data
@AllArgsConstructor
@Document(collection = "inventory")
public class InventoryItem {
    @Id
    private ObjectId objectId;

    @NotBlank(message = "Item must have a name.")
    private String name;

    private String priceString;

    // Not reading this validation due to receiving []. Beyond scope to fix bug. UI being updated in the future.
    @NotNull(message = "Item must have a price. For free items. Enter 0.")
    private Double price;

    public InventoryItem() {
        generateId();
    }

    public void setName(String name) {
        this.name = StringUtils.capitalize(name.toLowerCase());
    }

    private void generateId() {
        if (this.objectId == null) {
            this.objectId = new ObjectId();
        }
    }

    public void setPrice(Double price) {
        this.price = price;
        String priceString = Double.toString(price);
        if (!priceString.contains(".")) {
            this.priceString = "$" + priceString + ".00";
        }
        else {
            String[] priceArr = priceString.split("\\.");
            int numOfDecimals = priceArr[1].length();
            switch (numOfDecimals) {
                case 1:
                    this.priceString = "$" + priceString + "0";
                    break;
                case 2:
                    this.priceString = "$" + priceString;
                    break;
            }
        }
    }

}
