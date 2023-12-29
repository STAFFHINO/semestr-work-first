package ru.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Product {
    private long id;
    private String name;
    private long idOfSeller;
    private String description;
    private String price;
    private String category;
    private Date dateOfAdd;
}
