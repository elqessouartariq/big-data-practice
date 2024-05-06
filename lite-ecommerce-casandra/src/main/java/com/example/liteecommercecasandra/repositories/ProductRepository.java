package com.example.liteecommercecasandra.repositories;

import com.example.liteecommercecasandra.entities.Product;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface ProductRepository extends CassandraRepository<Product, UUID> {
}
