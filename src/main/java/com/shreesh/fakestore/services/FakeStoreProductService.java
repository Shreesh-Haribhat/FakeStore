package com.shreesh.fakestore.services;

import com.shreesh.fakestore.dtos.FakeStoreProductDto;
import com.shreesh.fakestore.models.Category;
import com.shreesh.fakestore.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );

        List<Product> answer = new ArrayList<>();

        for(FakeStoreProductDto dto : fakeStoreProductDtos)
        {
            answer.add(convertFaketooriginal(dto));
        }
        return answer;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        //String url = "https://fakestoreapi.com/products/" + id;

        RequestCallback requestCallback = restTemplate.httpEntityCallback(product,FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto prod = restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);


        
        return convertFaketooriginal(prod);

    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto dto = restTemplate.patchForObject("https://fakestoreapi.com/products/" + id,
                product, FakeStoreProductDto.class);
        return convertFaketooriginal(dto);
    }



}
