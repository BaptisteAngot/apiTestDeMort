package com.baptiste.gateway.repository;

import com.baptiste.gateway.model.Produit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface ProduitRepository extends MongoRepository<Produit,String> {
}
