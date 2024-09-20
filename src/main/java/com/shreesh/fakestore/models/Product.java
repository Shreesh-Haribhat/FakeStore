package com.shreesh.fakestore.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
    private Long id;
    private String title;
    private double price;
    private Category category;
    private String imageUrl;
    private String description;

}
