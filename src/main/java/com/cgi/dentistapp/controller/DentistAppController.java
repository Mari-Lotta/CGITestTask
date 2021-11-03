package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.entity.DentistEntity;
import com.cgi.dentistapp.service.DentistService;
import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

@Controller
@EnableAutoConfiguration
public class DentistAppController extends WebMvcConfigurerAdapter {

    @Autowired
    private DentistVisitService dentistVisitService;
    @Autowired
    DentistService dentistService;


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String showRegisterForm(DentistVisitDTO dentistVisitDTO, Model model) {
        model.addAttribute("dentists", dentistService.getAll());
        return "form";
    }

    @PostMapping("/")
    public String postRegisterForm(@Valid DentistVisitDTO dentistVisitDTO, BindingResult bindingResult, Model model) throws ValidationException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("dentists", dentistService.getAll());
            return "form";
        }

        dentistVisitService.addVisit(dentistVisitDTO.getDentistName(), dentistVisitDTO.getVisitDate(), dentistVisitDTO.getVisitTime());

        return "redirect:/results";
    }


    @GetMapping("/appointments")
    public String showAppointments(DentistVisitDTO dentistVisitDTO, Model model) {
        model.addAttribute("appointments", dentistVisitService.getAllVisits());

        return "appointments";
    }

    @GetMapping("/appointments/{id}")
    public String showDetailedAppointment(@PathVariable Long id, DentistVisitDTO dentistVisitDTO, Model model) {
        model.addAttribute("appointment", dentistVisitService.getVisitById(id));
        //    dentistVisitDTO = dentistVisitService.getVisitById(id);

        return "detailedview";
    }

    @GetMapping("/appointments/delete/{id}")
    public String deleteAppointment(@PathVariable Long id, DentistVisitDTO dentistVisitDTO, Model model) {
        dentistVisitService.deleteVisitById(id);

        return "redirect:/appointments";
    }

    @GetMapping("/appointments/update/{id}")
    public String editAppointment(@PathVariable Long id, DentistVisitDTO dentistVisitDTO, Model model) {
        dentistVisitDTO = dentistVisitService.getVisitById(id);
        model.addAttribute("dentists", dentistService.getAll());
        model.addAttribute("appointment", dentistVisitService.getVisitById(id));

        return "updateform";
    }

    @PostMapping("/appointments/update/{id}")
    public String editAppointment(@PathVariable Long id, DentistVisitDTO dentistVisitDTO, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            dentistVisitDTO = dentistVisitService.getVisitById(id);
            model.addAttribute("dentists", dentistService.getAll());
            model.addAttribute("appointment", dentistVisitService.getVisitById(id));
            return "updateform";
        }

        dentistVisitService.updateVisit(id, dentistVisitDTO.getDentistName(), dentistVisitDTO.getVisitDate(), dentistVisitDTO.getVisitTime());

        return "redirect:/appointments";
    }
}
