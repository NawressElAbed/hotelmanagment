package com.projetspringm.projetspringm.dao.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "chambres")
public class Chambre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idchambre;

    private String categorie;

    @ManyToOne(optional = true)
    @JoinColumn(name="categorie_groupe_idcat")
    private CategorieGroupe categorieGroupe;

    private int capacite;
    private String equipement;
    private double tarif;

    // Constructeur personnalisé
    public Chambre(Long idchambre, String categorie, int capacite, String equipement, double tarif) {
        this.idchambre = idchambre;
        this.categorie = categorie;
        this.capacite = capacite;
        this.equipement = equipement;
        this.tarif = tarif;
    }
}

