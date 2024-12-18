package com.projetspringm.projetspringm.business.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetspringm.projetspringm.business.services.ChambreService;
import com.projetspringm.projetspringm.dao.entities.Chambre;
import com.projetspringm.projetspringm.dao.repositories.ChambreRepository;

@Service
public class ChambreServiceImpl implements ChambreService {

    @Autowired
    private ChambreRepository chambreRepository;

    @Override
    public List<Chambre> getAllChambre() {
        return chambreRepository.findAll(); // Récupérer toutes les chambres
    }

    @Override
    public List<Chambre> filterByCategorie(String categorie) {
        return chambreRepository.findByCategorie(categorie);
    }
    

    @Override
    public Chambre getChambreByIdchambre(Long idchambre) {
        return chambreRepository.findById(idchambre).orElseThrow(() -> new RuntimeException("Chambre non trouvée"));
    }

    @Override
    public Chambre addChambre(Chambre chambre) {
        return chambreRepository.save(chambre); // Ajouter une nouvelle chambre
    }

    @Override
    public Chambre updateChambre(Long idchambre, Chambre chambre) {
        if (!chambreRepository.existsById(idchambre)) {
            throw new RuntimeException("Chambre non trouvée pour mise à jour");
        }
        chambre.setIdchambre(idchambre); // S'assurer que l'ID de la chambre est bien mis à jour
        return chambreRepository.save(chambre); // Sauvegarder la chambre mise à jour
    }

    @Override
    public void deleteChambreByIdchambre(Long idchambre) {
        if (!chambreRepository.existsById(idchambre)) {
            throw new RuntimeException("Chambre non trouvée pour suppression");
        }
        chambreRepository.deleteById(idchambre); // Supprimer la chambre par son ID
    }

    



 
}
