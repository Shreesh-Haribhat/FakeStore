package com.shreesh.fakestore.Controllers;

import com.shreesh.fakestore.Exception.InvalidProductIdException;
import com.shreesh.fakestore.dtos.ErrorResponseDTO;
import com.shreesh.fakestore.dtos.FakeStoreProductDto;
import com.shreesh.fakestore.dtos.ProductWrapper;
import com.shreesh.fakestore.dtos.UserDto;
import com.shreesh.fakestore.models.Category;
import com.shreesh.fakestore.models.Product;
import com.shreesh.fakestore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    private RestTemplate restTemplate;

    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService,RestTemplate restTemplate)
    {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }


    @GetMapping()
    public List<Product> getAllProducts() throws InvalidProductIdException {

//        UserDto userDto = restTemplate.getForObject("http://localhost:8082/validateToken/" + token,
//                UserDto.class);
//
//
//        if (userDto == null) {
//            throw new RuntimeException("Invalid");
//        }


        return productService.getAllProduct();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductWrapper> getProduct(@PathVariable("id") Long id) throws InvalidProductIdException
    {
        ResponseEntity<ProductWrapper> response;

        Product product = productService.getSingleProduct(id);
        ProductWrapper productResponse = new ProductWrapper(product, "Got Single Product");

        response = new ResponseEntity<>(productResponse, HttpStatus.OK);

        return response;

    }


    @PostMapping()
    public Product addProduct(@RequestBody FakeStoreProductDto fakeStoreProductDto) throws InvalidProductIdException {
        Product product = new Product();
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setId(fakeStoreProductDto.getId());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());

        // Create or find the category
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory()); // Set the category name
        product.setCategory(category); // Associate the category with the product

        Product savedProduct = productService.addProduct(product);
        return savedProduct;
    }


    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody FakeStoreProductDto fakeStoreProductDto) throws InvalidProductIdException {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());

        Category newCat = new Category();
        newCat.setName(fakeStoreProductDto.getCategory());
        product.setCategory(newCat);

        return productService.updateProduct(id,product);
    }

//    @PutMapping("/{id}")
//    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product)
//    {
//        return new Product();
//    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id)
    {
        productService.deleteProduct(id);
    }




}
