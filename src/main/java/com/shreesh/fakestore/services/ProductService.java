package com.shreesh.fakestore.services;

import com.shreesh.fakestore.models.Product;

import java.util.List;

public interface ProductService {

    public Product getSingleProduct(Long id);

    public Product addNewProduct(Product product);

    public List<Product> getAllProducts();

    public Product updateProduct(Long id,Product product);

    public Product replaceProduct(Long id,Product product);

}
