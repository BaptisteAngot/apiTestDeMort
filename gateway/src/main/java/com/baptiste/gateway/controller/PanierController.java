package com.baptiste.gateway.controller;

import com.baptiste.gateway.model.Panier;
import com.baptiste.gateway.model.Produit;
import com.baptiste.gateway.service.PanierService;
import com.baptiste.gateway.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/panier")
public class PanierController {
    @Autowired
    PanierService panierService;

    @Autowired
    ProduitService produitService;

    @GetMapping("/{id}")
    public ResponseEntity<Panier> get(@PathVariable String id) {
        try {
            Optional<Panier> panier = panierService.getPanier(id);
            if (panier.isPresent()){
                return new ResponseEntity<Panier>(panier.get(), HttpStatus.OK);
            }else {
                return new ResponseEntity<Panier>(HttpStatus.NOT_FOUND);
            }
        }catch (NoSuchElementException e) {
            return new ResponseEntity<Panier>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add/{idPanier}/{idUser}")
    public ResponseEntity<Panier> addToPanier(@PathVariable String idPanier, @PathVariable Integer idUser, @RequestBody Produit produit) throws Exception {
        Optional<Panier> panier = panierService.getPanier(idPanier);
        Produit produitdb = produitService.getProduit(produit.getId());
        if (panier.isPresent() && produitdb != null ) {
            Panier panierdb = panier.get();
            panierdb.getProductList().add(produitdb);
            AtomicInteger somme = new AtomicInteger();
            panierdb.getProductList().forEach(produit1 -> {
                somme.addAndGet(produit1.getPrice());
            });

            panierdb.setPrice(somme.get());
            Panier panierDb2 = panierService.savePanier(panierdb);
            produitdb.setStock(produitdb.getStock()-1);
            produitService.saveProduit(produitdb);
            return new ResponseEntity<Panier>(panierDb2, HttpStatus.OK);
        }else {
            if (produitdb == null ) {
                throw new Exception("This product don't exist");
            }else {
                Panier panierToCreate = new Panier();
                panierToCreate.setIdUser(idUser);
                panierToCreate.getProductList().add(produitdb);
                panierToCreate.setPrice(produit.getPrice());
                Panier panierDb = panierService.savePanier(panierToCreate);
                produitdb.setStock(produitdb.getStock()-1);
                produitService.saveProduit(produitdb);
                return new ResponseEntity<Panier>(panierDb, HttpStatus.OK);
            }
        }
    }

    @PostMapping("/validate/{idPanier}")
    public boolean validate(@PathVariable String idPanier) throws Exception {
        Optional<Panier> panierDb = panierService.getPanier(idPanier);
        if (panierDb.isPresent()) {
            Panier panier = panierDb.get();
            panier.setValidate(true);
            panierService.savePanier(panier);
            return true;
        }else {
            throw new Exception("This panier don't exist");
        }
    }

}
