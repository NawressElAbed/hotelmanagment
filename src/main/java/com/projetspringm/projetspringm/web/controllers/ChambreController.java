package com.projetspringm.projetspringm.web.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.projetspringm.projetspringm.business.services.ChambreService;
import com.projetspringm.projetspringm.dao.entities.Chambre;
import com.projetspringm.projetspringm.web.models.requests.ChambreForm;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/chambres")
public class ChambreController {

    private final ChambreService chambreService;

    public ChambreController(ChambreService chambreService) {
        this.chambreService = chambreService;
    }

    @GetMapping
    public String listChambres(Model model) {
        model.addAttribute("chambres", chambreService.getAllChambre());
        return "chambre-list";  // Use the correct template name here
    }

  @GetMapping("/filter")
public String filterChambresByCategorie(@RequestParam String categorie, Model model) {
    List<Chambre> chambres = chambreService.filterByCategorie(categorie);
    model.addAttribute("chambres", chambres);
    return "chambre-list";
}


  
    @RequestMapping("/create")
    public String showAddChambreForm(Model model) {
        model.addAttribute("chambreForm", new ChambreForm());
        return "add-chambre";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String addChambre(@Valid @ModelAttribute ChambreForm chambreForm, 
                             BindingResult bindingResult, 
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Invalid Input");
            return "add-chambre";
        }

        this.chambreService.addChambre(new Chambre(null, chambreForm.getCategorie(), chambreForm.getCapacite(),
                                                   chambreForm.getEquipement(), chambreForm.getTarif()));
        return "redirect:/chambres";
    }

    @RequestMapping("/{idchambre}/edit")
    public String showEditChambreForm(@PathVariable Long idchambre, Model model) {
        // Récupérer la chambre par ID
        Chambre chambre = this.chambreService.getChambreByIdchambre(idchambre);
    
        // Créer un objet ChambreForm (sans inclure l'ID)
        ChambreForm chambreForm = new ChambreForm();
        chambreForm.setIdchambre(chambre.getIdchambre()); // Ajouter l'ID dans le formulaire
        chambreForm.setCategorie(chambre.getCategorie());
        chambreForm.setCapacite(chambre.getCapacite());
        chambreForm.setEquipement(chambre.getEquipement());
        chambreForm.setTarif(chambre.getTarif());
    
        model.addAttribute("chambreForm", chambreForm);
        return "edit-chambre";
    }
    
    
    @RequestMapping(value = "/{idchambre}/edit", method = RequestMethod.POST)
    public String updateChambre(@PathVariable Long idchambre, @ModelAttribute("chambreForm") ChambreForm chambreForm) {
        // Mettre à jour la chambre avec l'ID existant
        chambreService.updateChambre(idchambre, new Chambre(idchambre, chambreForm.getCategorie(), chambreForm.getCapacite(), chambreForm.getEquipement(), chambreForm.getTarif()));
        
        // Rediriger vers la liste des chambres après la mise à jour
        return "redirect:/chambres";
    }
    

    @RequestMapping(path = "/{idchambre}/delete", method = RequestMethod.GET)
    public String deleteChambreById(@PathVariable Long idchambre) {
        this.chambreService.deleteChambreByIdchambre(idchambre);
        return "redirect:/chambres";
    }
}


