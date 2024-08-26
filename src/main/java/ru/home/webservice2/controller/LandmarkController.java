package ru.home.webservice2.controller;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.home.webservice2.dao.LandmarkDAO;
import ru.home.webservice2.models.Landmark;

import javax.validation.Valid;
import java.util.Map;

/**
 * URL операции Landmark
 *
 * @see Landmark
 */

@Slf4j
@Controller
@RequestMapping("/landmark")
public class LandmarkController {
    private final LandmarkDAO landmarkDAO;

    @Autowired
    public LandmarkController(LandmarkDAO landmarkDAO) {
        this.landmarkDAO = landmarkDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("landmark", landmarkDAO.index());
        return "landmark/index";
    }

    @GetMapping( "/sort")
    public String sortFiltr(@RequestParam Map<String,String> allParams, ModelMap model){
        model.addAttribute("landmark", landmarkDAO.sortFiltr(allParams));
        return "landmark/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("landmark", landmarkDAO.show(id));
        return "landmark/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("landmark") Landmark landmark) {
        return "landmark/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("landmark") @Valid Landmark landmark,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "landmark/new";

        landmarkDAO.save(landmark);
        return "redirect:/landmark";
    }

    @GetMapping("/city/{city}")
    public String filtrCity(Model model, @PathVariable("city") String city) {
        model.addAttribute("landmark", landmarkDAO.filtrCity(city));
        return "landmark/showcity";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("landmark", landmarkDAO.show(id));
        return "landmark/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("landmark") @Valid Landmark landmark, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "landmark/edit";

        landmarkDAO.update(id, landmark);
        return "redirect:/landmark";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        landmarkDAO.delete(id);
        return "redirect:/landmark";
    }
}
