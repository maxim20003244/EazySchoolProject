package com.eazybyte.springschoolproject.controller;

import com.eazybyte.springschoolproject.model.EazyClass;
import com.eazybyte.springschoolproject.model.Person;
import com.eazybyte.springschoolproject.repository.EazyClassRepository;
import com.eazybyte.springschoolproject.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    private final EazyClassRepository eazyClassRepository;
    private  final PersonRepository personRepository;

    public AdminController(EazyClassRepository eazyClassRepository, PersonRepository personRepository) {
        this.eazyClassRepository = eazyClassRepository;
        this.personRepository = personRepository;
    }

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model){
        ModelAndView modelAndView = new ModelAndView("classes");
        List<EazyClass> eazyClassList = eazyClassRepository.findAll();
        modelAndView.addObject("eazyClasses",eazyClassList);
        modelAndView.addObject("eazyClass", new EazyClass());
        return modelAndView;

    }
    @RequestMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("ezyClasses") EazyClass eazyClass){
        eazyClassRepository.save(eazyClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }@RequestMapping("/deleteClass")
    public ModelAndView deleteClasses(Model model, @RequestParam int id){
        Optional<EazyClass> eazyClass = eazyClassRepository.findById(id);
        for (Person person : eazyClass.get().getPersons()){
            person.setEazyClass(null);
            personRepository.save(person);
        }
        eazyClassRepository.deleteById(id);
        ModelAndView modelAndView  = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }
}
