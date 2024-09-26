package com.shreesh.fakestore.Controllers;

import com.shreesh.fakestore.Exception.InvalidProductIdException;
import com.shreesh.fakestore.dtos.ErrorResponseDTO;
import com.shreesh.fakestore.dtos.FakeStoreProductDto;
import com.shreesh.fakestore.dtos.ProductWrapper;
import com.shreesh.fakestore.models.Product;
import com.shreesh.fakestore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }


    @GetMapping()
    public List<Product> getAllProducts()
    {
        return productService.getAllProduct();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductWrapper> getProduct(@PathVariable("id") Long id) throws InvalidProductIdException {

        ResponseEntity<ProductWrapper> response;

            Product product = productService.getSingleProduct(id);
            ProductWrapper productResponse = new ProductWrapper(product, "Got Single Product");

            response = new ResponseEntity<>(productResponse, HttpStatus.OK);

            return response;

    }


    @PostMapping()
    public Product addProduct(@RequestBody FakeStoreProductDto fakeStoreProductDto)
    {
        return productService.addProduct(fakeStoreProductDto);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody FakeStoreProductDto fakeStoreProductDto)
    {
        return productService.updateProduct(id,fakeStoreProductDto);
    }

//    @PutMapping("/{id}")
//    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product)
//    {
//        return new Product();
//    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id)
    {
        return;
    }




}
