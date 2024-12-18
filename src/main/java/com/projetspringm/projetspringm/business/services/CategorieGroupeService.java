package com.projetspringm.projetspringm.business.services;
import java.util.List;

import com.projetspringm.projetspringm.dao.entities.CategorieGroupe;
public interface CategorieGroupeService {
    CategorieGroupe addCategorieGroupe(CategorieGroupe categorieGroupe);
    CategorieGroupe getCategorieGroupeById(Long idcat);
    List<CategorieGroupe> getAllCategorieGroupes();
    void deleteCategorieGroupe(Long idcat);
    CategorieGroupe updateCategorieGroupe(Long idcat, CategorieGroupe categorieGroupe);
    
}