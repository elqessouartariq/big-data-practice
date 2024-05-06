package com.example.liteecommercecasandra.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.util.UUID;

@Table("product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @PrimaryKey
    private UUID product_id;
    private String name;
    private Double price;
    private int quantiteStock;
}
