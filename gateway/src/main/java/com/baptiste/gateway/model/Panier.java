package com.baptiste.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "paniers")
public class Panier {

    @Id
    private String id;
    private Integer idUser;

    private int price;

    private List<Produit> productList = new ArrayList<Produit>();
    private Boolean validate = false;
}
