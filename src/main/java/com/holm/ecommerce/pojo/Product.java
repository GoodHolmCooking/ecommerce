package com.holm.ecommerce.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Item must have a name.")
    private String name;

    @Column(name = "price_string")
    private String priceString;

    @NotNull(message = "Item must have a price. For free items. Enter 0.")
    @Column(name = "price")
    private Double price;

    public void setName(String name) {
        this.name = StringUtils.capitalize(name.toLowerCase());
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
