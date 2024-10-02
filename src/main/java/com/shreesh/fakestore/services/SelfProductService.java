package com.shreesh.fakestore.services;

import com.shreesh.fakestore.Exception.InvalidProductIdException;
import com.shreesh.fakestore.models.Category;
import com.shreesh.fakestore.models.Product;
import com.shreesh.fakestore.repositories.ProductRepository;
import com.shreesh.fakestore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@Qualifier("selfProductService")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository)
    {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long id) throws InvalidProductIdException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty())
        {
            throw new InvalidProductIdException("Product with the id "+id+" is not present");
        }

        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProduct() throws InvalidProductIdException {
        List<Product> listOptionalProduct = productRepository.findAll();

        return listOptionalProduct;
    }

    @Override
    public Product addProduct(Product product)  {
        //Checking if the category already exist in DB
        Optional<Category> categoryToCheck = categoryRepository.findByName(product.getCategory().getName());

        if(categoryToCheck.isEmpty())
        {
//            Category category = new Category();
//            category.setName(product.getCategory().getName());
//            Category savedCategory = categoryRepository.save(category);
//            product.setCategory(savedCategory);

            //This code can be reduced by using cascade int ht eproduct model itself
            //If the category alredy present in DB it wont create new Category
        }
        else {
            product.setCategory(categoryToCheck.get());
        }

        Product savedProduct = productRepository.save(product); // Save the product and its associated category
        return savedProduct;
    }


    @Override
    public Product updateProduct(Long id, Product product) throws InvalidProductIdException {

        Optional<Product> isExist =  productRepository.findById(id);
        Product toSave = new Product();

        if(isExist.isEmpty())
        {
            throw new InvalidProductIdException("No product exist with this ID");
        }
        else {

            Product existingProduct = isExist.get();

            toSave.setId(id);
            if(product.getTitle() != null)
            {
                toSave.setTitle(product.getTitle());
            }
            else
            {
                toSave.setTitle(existingProduct.getTitle());
            }

            if(product.getPrice() != 0)
            {
                toSave.setPrice(product.getPrice());
            }
            else
            {
                toSave.setPrice(existingProduct.getPrice());
            }

            if(product.getDescription() != null)
            {
                toSave.setDescription(product.getDescription());
            }
            else
            {
                toSave.setDescription(existingProduct.getDescription());
            }

            if(product.getCategory() != null)
            {
                toSave.setCategory(product.getCategory());
            }
            else
            {
                toSave.setCategory(existingProduct.getCategory());
            }

            if(product.getImageUrl() != null)
            {
                toSave.setImageUrl(product.getImageUrl());
            }
            else
            {
                toSave.setImageUrl(existingProduct.getImageUrl());
            }


        }

        Optional<Category> toCheck = categoryRepository.findByName(product.getCategory().getName());
        if(toCheck.isEmpty())
        {

        }
        else
        {
            toSave.setCategory(toCheck.get());
        }
        Product savedProduct = productRepository.save(toSave);

        return savedProduct;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
