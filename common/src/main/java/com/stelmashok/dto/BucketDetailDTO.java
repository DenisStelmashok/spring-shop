package com.stelmashok.dto;


import com.stelmashok.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketDetailDTO {
    private String title;
    private Long productId ;
    private BigDecimal price;
    private BigDecimal amount;
    private Double sum;

    public BucketDetailDTO (Product preoduct){
        this.title = preoduct.getTitle();
        this.productId = preoduct.getId();
        this.price = preoduct.getPrice();
        this.amount = new BigDecimal(1.0);
        this.sum = Double.valueOf(product.getPrice().toString());
    }
}
