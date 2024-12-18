package com.projetspringm.projetspringm.business.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.projetspringm.projetspringm.business.services.CategorieGroupeService;
import com.projetspringm.projetspringm.dao.entities.CategorieGroupe;
import com.projetspringm.projetspringm.dao.repositories.CategorieGroupeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategorieGroupServiceImpl implements CategorieGroupeService {

    @Autowired
    private CategorieGroupeRepository categorieGroupeRepository;

    // Récupérer toutes les catégories
    @Override
    public List<CategorieGroupe> getAllCategorieGroupes() {
        return categorieGroupeRepository.findAll();
    }

    // Récupérer une catégorie par son ID
    @Override
    public CategorieGroupe getCategorieGroupeById(Long idcat) {
        if (idcat == null) {
            throw new EntityNotFoundException("L'ID de la catégorie est nul.");
        }
        return categorieGroupeRepository
            .findById(idcat)
            .orElseThrow(() -> new EntityNotFoundException("La catégorie avec l'ID: " + idcat + " n'a pas été trouvée"));
    }

    // Ajouter une nouvelle catégorie
    @Override
    public CategorieGroupe addCategorieGroupe(CategorieGroupe categorieGroupe) {
        if (categorieGroupe == null) {
            log.error("La catégorie à ajouter est nulle.");
            return null;
        }
        try {
            return categorieGroupeRepository.save(categorieGroupe);
        } catch (DataIntegrityViolationException e) {
            log.error("Erreur de violation d'intégrité: {}", e.getMessage());
            return null;
        }
    }

    // Supprimer une catégorie par son ID
    @Override
    public void deleteCategorieGroupe(Long idcat) {
        if (idcat == null) {
            throw new EntityNotFoundException("L'ID de la catégorie est nul.");
        }
        if (categorieGroupeRepository.existsById(idcat)) {
            categorieGroupeRepository.deleteById(idcat);
        } else {
            throw new EntityNotFoundException("La catégorie avec l'ID: " + idcat + " n'existe pas");
        }
    }

    // Mettre à jour une catégorie existante
    @Override
    public CategorieGroupe updateCategorieGroupe(Long idcat, CategorieGroupe categorieGroupe) {
        if (categorieGroupe == null || idcat == null) {
            throw new IllegalArgumentException("Les paramètres sont invalides.");
        }

        // Récupérer la catégorie existante
        CategorieGroupe existingCategorieGroupe = getCategorieGroupeById(idcat);

        // Mise à jour des informations
        existingCategorieGroupe.setNomCategorie(categorieGroupe.getNomCategorie());
        existingCategorieGroupe.setDescription(categorieGroupe.getDescription());

        // Sauvegarde et retour de la catégorie mise à jour
        return categorieGroupeRepository.save(existingCategorieGroupe);
    }
}
