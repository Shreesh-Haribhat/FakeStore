package com.shreesh.fakestore.dtos;

import com.shreesh.fakestore.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductWrapper {
    private Product product;
    private String message;

    public ProductWrapper(Product product, String message)
    {
        this.product = product;
        this.message = message;
    }
}
