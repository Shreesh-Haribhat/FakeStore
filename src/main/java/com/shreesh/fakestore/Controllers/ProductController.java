package com.shreesh.fakestore.Controllers;

import com.shreesh.fakestore.models.Category;
import com.shreesh.fakestore.models.Product;
import com.shreesh.fakestore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id)
    {
        return productService.getSingleProduct(id);
    }
    @PostMapping()
    public Product addProduct(@RequestBody Product product)
    {
        Product p = new Product();
        p.setId(232L);
        p.setTitle("Shreesh");
        p.setCategory(new Category());
        p.setImageUrl("ggfg");
        return p;
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product)
    {
        return new Product();
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product)
    {
        return new Product();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id)
    {
        return;
    }




}
