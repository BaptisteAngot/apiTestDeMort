package com.baptiste.gateway.service;

import com.baptiste.gateway.model.Produit;
import com.baptiste.gateway.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> listAllProduit() {
        return produitRepository.findAll();
    }

    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit getProduit(String id) {
        return produitRepository.findById(id).get();
    }

    public void deleteProduit(String id) {
        produitRepository.deleteById(id);
    }
}
