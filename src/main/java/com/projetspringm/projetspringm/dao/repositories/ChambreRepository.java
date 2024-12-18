package com.projetspringm.projetspringm.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetspringm.projetspringm.dao.entities.Chambre;


public interface ChambreRepository extends JpaRepository<Chambre, Long> {

    List<Chambre> findByCategorie(String categorie);


}
