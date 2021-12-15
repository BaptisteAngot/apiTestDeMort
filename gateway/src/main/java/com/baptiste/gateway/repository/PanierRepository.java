package com.baptiste.gateway.repository;

import com.baptiste.gateway.model.Panier;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PanierRepository extends MongoRepository<Panier,String> {

}
