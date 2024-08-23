package ru.home.webservice2.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.home.webservice2.dao.CityDAO;
import ru.home.webservice2.models.City;

@Controller
@RequestMapping("/city")
public class CityController {

    private final CityDAO cityDAO;

    @Autowired
    public CityController(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("city", cityDAO.index());
        return "city/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("city", cityDAO.show(id));
        return "city/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("city") City city) {
        return "city/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("city") @Valid City city,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "city/new";

        cityDAO.save(city);
        return "redirect:/city";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("city", cityDAO.show(id));
        return "city/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("city") @Valid City city, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "city/edit";

        cityDAO.update(id, city);
        return "redirect:/city";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        cityDAO.delete(id);
        return "redirect:/city";
    }

//    @PostMapping
//    public ResponseEntity<City> create(@RequestBody CityDTO dto) {
//        return new ResponseEntity<>(cityService.create(dto), HttpStatus.OK);
//    }
//
//    @GetMapping("/cities")
//    public ResponseEntity<List<City>> readAll() {
//        return new ResponseEntity<>(cityService.readAll(), HttpStatus.OK);
//    }
//
//    @PutMapping
//    public ResponseEntity<City> update(@RequestBody City landmark) {
//        return new ResponseEntity<>(cityService.update(landmark), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public HttpStatus delete(@PathVariable Integer id) {
//        cityService.delete(id);
//        return HttpStatus.OK;
//    }
}
