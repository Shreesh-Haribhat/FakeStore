package com.shreesh.fakestore.services;

import com.shreesh.fakestore.Exception.InvalidProductIdException;
import com.shreesh.fakestore.dtos.FakeStoreProductDto;
import com.shreesh.fakestore.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id) throws InvalidProductIdException;

    List<Product> getAllProduct();

    Product addProduct(FakeStoreProductDto fakeStoreProductDto);

    Product updateProduct(Long id, FakeStoreProductDto fakeStoreProductDto);
}
