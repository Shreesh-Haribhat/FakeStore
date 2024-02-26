package com.shreesh.fakestore.services;

import com.shreesh.fakestore.dtos.FakeStoreProductDto;
import com.shreesh.fakestore.models.Category;
import com.shreesh.fakestore.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    private Product convertFaketooriginal(FakeStoreProductDto fakeStoreProductDto)
    {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(new Category());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());

        product.getCategory().setName("Shreesh");

        return product;
    }

    @Override
    public Product getSingleProduct(Long id) {
        FakeStoreProductDto productDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
        );
        return convertFaketooriginal(productDto);
    }

    @Override
    public Product addNewProduct(Product product) {
        Product p = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                product,
                Product.class
        );
        return product;
    }
}
