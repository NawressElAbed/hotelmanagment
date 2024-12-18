package com.projetspringm.projetspringm.web.models.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChambreForm {
    private Long idchambre;
    private String categorie;
    private int capacite;
    private String equipement;
    private double tarif;
    private Long categorieGroupe;  // Référence à CategorieGroupe
}



