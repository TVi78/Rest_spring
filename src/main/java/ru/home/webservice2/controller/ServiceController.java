package ru.home.webservice2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.home.webservice2.dao.CityDAO;
import ru.home.webservice2.dao.ServiceDAO;
import ru.home.webservice2.models.City;
import ru.home.webservice2.models.Service;

import javax.validation.Valid;

/**
 * URL операции Service
 *
 * @see Service
 */
@Slf4j
@Controller
@RequestMapping("/service")
public class ServiceController {

    private final ServiceDAO serviceDAO;

    @Autowired
    public ServiceController(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("services", serviceDAO.index());
        return "service/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("service", serviceDAO.show(id));
        return "service/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("service") Service service) {
        return "service/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("service") @Valid Service service,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "service/new";

        serviceDAO.save(service);
        return "redirect:/service";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("service", serviceDAO.show(id));
        return "service/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("service") @Valid Service service, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "service/edit";

        serviceDAO.update(id, service);
        return "redirect:/service";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        serviceDAO.delete(id);
        return "redirect:/service";
    }
}
