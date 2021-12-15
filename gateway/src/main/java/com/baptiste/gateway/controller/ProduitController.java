package com.baptiste.gateway.controller;

import com.baptiste.gateway.model.Produit;
import com.baptiste.gateway.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/produits")
public class ProduitController {
    @Autowired
    ProduitService produitService;

    @GetMapping("")
    public List<Produit> list() {
        return produitService.listAllProduit();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> get(@PathVariable String id) {
        try {
            Produit produit = produitService.getProduit(id);
            return new ResponseEntity<Produit>(produit, HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<Produit>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public Produit add(@RequestBody Produit produit) {
        return produitService.saveProduit(produit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Produit produit, @PathVariable String id) {
        try {
            Produit existProduit = produitService.getProduit(id);
            existProduit.setId(id);
            produitService.saveProduit(produit);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        produitService.deleteProduit(id);
    }
}
