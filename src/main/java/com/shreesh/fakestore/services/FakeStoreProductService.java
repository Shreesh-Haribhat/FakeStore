package com.shreesh.fakestore.services;

import com.shreesh.fakestore.dtos.FakeStoreProductDto;
import com.shreesh.fakestore.dtos.ProductRequestDto;
import com.shreesh.fakestore.models.Category;
import com.shreesh.fakestore.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductToProduct(FakeStoreProductDto productDto)
    {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setId(productDto.getId());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImage());
        product.setCategory(new Category());

        product.getCategory().setName(productDto.getCategory());

        return product;
    }

    @Override
    public Product getSingleProduct(Long id) {
        //https://fakestoreapi.com/products/
        FakeStoreProductDto productDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
        );
        return convertFakeStoreProductToProduct(productDto);
    }

    @Override
    public List<Product> getAllProduct(){
        //https://fakestoreapi.com/products
        FakeStoreProductDto[] list = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        List<Product> productList = new ArrayList<>();
        for(int i=0;i<list.length;i++)
        {
            productList.add(convertFakeStoreProductToProduct(list[i]));
        }

        return productList;
    }

    @Override
    public Product addProduct(FakeStoreProductDto fakeStoreProductDto) {

        FakeStoreProductDto dto = restTemplate.postForObject("https://fakestoreapi.com/products",fakeStoreProductDto,FakeStoreProductDto.class);

        return convertFakeStoreProductToProduct(dto);
    }

    @Override
    public Product updateProduct(Long id, FakeStoreProductDto fakeStoreProductDto) {


        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto,FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
         FakeStoreProductDto dto = restTemplate.execute(
                "https://fakestoreapi.com/products/"+id,
                HttpMethod.PUT, requestCallback, responseExtractor);

         return convertFakeStoreProductToProduct(dto);

    }
}
