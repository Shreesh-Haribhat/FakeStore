package com.shreesh.fakestore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product extends BaseModel{

    private String title;
    private double price;
    private String imageUrl;
    private String description;

    @ManyToOne
    private Category category;

}
