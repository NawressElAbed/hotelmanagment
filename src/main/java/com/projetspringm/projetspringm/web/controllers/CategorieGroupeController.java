package com.projetspringm.projetspringm.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetspringm.projetspringm.business.services.CategorieGroupeService;
import com.projetspringm.projetspringm.dao.entities.CategorieGroupe;
import com.projetspringm.projetspringm.web.models.CategorieGroupeForm;

import jakarta.validation.Valid;
@Controller
@RequestMapping("/categorieGroupes")
public class CategorieGroupeController {

    @Autowired
    private CategorieGroupeService categorieGroupeService;

    // Liste des groupes
    @GetMapping("")
    public String listCategorieGroupes(Model model) {
        List<CategorieGroupe> categorieGroupes = categorieGroupeService.getAllCategorieGroupes();
        model.addAttribute("categorieGroupes", categorieGroupes);
        return "categorieGroupe/categorieGroupeList";  // Vérifiez que ce fichier existe
    }

    // Formulaire d'ajout
    @GetMapping("/create")
    public String showFormAddCategorieGroupe(Model model) {
        CategorieGroupeForm categorieGroupeForm = new CategorieGroupeForm();
        model.addAttribute("categorieGroupeForm", categorieGroupeForm);
        return "categorieGroupe/categorieGroupeAddForm"; // Vérifiez que ce fichier existe
    }

   // Sauvegarde du groupe
@PostMapping("/create")
public String saveCategorieGroupe(@Valid @ModelAttribute("categorieGroupeForm") CategorieGroupeForm categorieGroupeForm,
                                  BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
        return "categorieGroupe/categorieGroupeAddForm";
    }
    // Assurez-vous que les champs nomCategorie et description sont bien non nuls et non vides
    if (categorieGroupeForm.getNomCategorie() == null || categorieGroupeForm.getNomCategorie().isEmpty()) {
        bindingResult.rejectValue("nomCategorie", "error.nomCategorie", "Le nom de la catégorie ne peut pas être vide");
        return "categorieGroupe/categorieGroupeAddForm";
    }
    if (categorieGroupeForm.getDescription() == null || categorieGroupeForm.getDescription().isEmpty()) {
        bindingResult.rejectValue("description", "error.description", "La description de la catégorie ne peut pas être vide");
        return "categorieGroupe/categorieGroupeAddForm";
    }
    
    // Ajouter une nouvelle catégorie
    CategorieGroupe categorieGroupe = new CategorieGroupe(null, categorieGroupeForm.getNomCategorie(), categorieGroupeForm.getDescription());
    categorieGroupeService.addCategorieGroupe(categorieGroupe);
    
    return "redirect:/categorieGroupes";  // Redirection vers la liste après l'ajout
}

    @PostMapping("/{idcat}/edit")
    public String updateCategorieGroupe(@PathVariable Long idcat,
                                        @Valid @ModelAttribute("categorieGroupeForm") CategorieGroupeForm categorieGroupeForm,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "categorieGroupe/categorieGroupeEditForm";
        }

        // Fetch the category to be updated
        CategorieGroupe categorieGroupe = categorieGroupeService.getCategorieGroupeById(idcat);

        // Update the category details
        categorieGroupe.setNomCategorie(categorieGroupeForm.getNomCategorie());
        categorieGroupe.setDescription(categorieGroupeForm.getDescription());

        // Save the updated category
        categorieGroupeService.updateCategorieGroupe(idcat, categorieGroupe);

        // Redirect to the list of categories after the update
        return "redirect:/categorieGroupes";
    }
    // Suppression du groupe
    @PostMapping("/{idcat}/delete")
    public String deleteCategorieGroupe(@PathVariable Long idcat) {
        categorieGroupeService.deleteCategorieGroupe(idcat);
        return "redirect:/categorieGroupes";  // Redirection après suppression
    }
}
