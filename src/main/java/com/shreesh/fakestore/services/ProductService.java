package com.shreesh.fakestore.services;

import com.shreesh.fakestore.models.Product;

public interface ProductService {

    public Product getSingleProduct(Long id);

    public Product addNewProduct(Product product);
}
