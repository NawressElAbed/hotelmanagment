package com.projetspringm.projetspringm.web.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Chambre {
    private Long idchambre;
    private String categorie;
    private int capacite;
    private String equipement;
    private double tarif;
    
}
