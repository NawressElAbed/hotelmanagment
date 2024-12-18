

package com.projetspringm.projetspringm.dao.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "categorie_groups")
public class CategorieGroupe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcat;

    @Column(name = "nomCategorie", nullable = false, unique = true)
    private String nomCategorie;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "categorieGroupe", cascade = CascadeType.ALL)
    private List<Chambre> chambres;
    public CategorieGroupe(Long idcat, String nomCategorie, String description) {
        this.idcat = idcat;
        this.nomCategorie = nomCategorie;
        this.description = description;
    }
}

