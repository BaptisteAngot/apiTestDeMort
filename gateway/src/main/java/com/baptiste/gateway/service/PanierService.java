package com.baptiste.gateway.service;

import com.baptiste.gateway.model.Panier;
import com.baptiste.gateway.repository.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PanierService {

    @Autowired
    private PanierRepository panierRepository;

    public List<Panier> listAllPanier() {
        return panierRepository.findAll();
    }

    public Panier savePanier(Panier panier) {
        return panierRepository.save(panier);
    }

    public Optional<Panier> getPanier(String id) {

        return panierRepository.findById(id);
    }

    public void deletePanier(String id) {
        panierRepository.deleteById(id);
    }
}
