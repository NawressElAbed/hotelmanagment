package com.projetspringm.projetspringm.business.services;

import java.util.List;

import com.projetspringm.projetspringm.dao.entities.Chambre;

public interface ChambreService {
    
    List<Chambre> getAllChambre();
    List<Chambre> filterByCategorie(String categorie);
    
    Chambre getChambreByIdchambre(Long idchambre);
    
    Chambre addChambre(Chambre chambre);
    
    Chambre updateChambre(Long idchambre, Chambre chambre);
    
    void deleteChambreByIdchambre(Long idchambre);
}
