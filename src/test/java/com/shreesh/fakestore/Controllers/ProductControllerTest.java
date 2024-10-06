package com.shreesh.fakestore.Controllers;

import com.shreesh.fakestore.Exception.InvalidProductIdException;
import com.shreesh.fakestore.dtos.ProductWrapper;
import com.shreesh.fakestore.models.Product;
import com.shreesh.fakestore.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductControllerTest {
    private ProductController productController;

    @MockBean
    @Qualifier("selfProductService")
    private ProductService productService;

    @Autowired

    public ProductControllerTest(ProductController productController)
    {
        this.productController = productController;
    }



    @Test
    void getAllProducts() throws InvalidProductIdException {

        Product p1 = new Product();
        Product p2 = new Product();
        Product p3 = new Product();
        Product p4 = new Product();
        List<Product> mockedList = new ArrayList<>();
        mockedList.add(p1);
        mockedList.add(p2);
        mockedList.add(p3);
        mockedList.add(p4);


        Mockito.when(productService.getAllProduct()).thenReturn(mockedList);

        List<Product> generated = productController.getAllProducts();

        Assertions.assertEquals(4,generated.size());
        Assertions.assertNotNull(generated);


    }

    @Test
    void getProduct() throws InvalidProductIdException {
        Product p = new Product();
        p.setTitle("Shreesh");

        Mockito.when(productService.getSingleProduct(1L)).thenReturn(p);

        ResponseEntity<ProductWrapper> prod = productController.getProduct(1L);

        Assertions.assertEquals("Shreesh",prod.getBody().getProduct().getTitle());


    }

    @Test
    void addProduct() {
    }
}