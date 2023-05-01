package com.eazybyte.springschoolproject.controller;

import com.eazybyte.springschoolproject.model.EazyClass;
import com.eazybyte.springschoolproject.model.Person;
import com.eazybyte.springschoolproject.repository.EazyClassRepository;
import com.eazybyte.springschoolproject.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    @RequestMapping("/displayStudents")
    public ModelAndView displayStudents(Model model , @RequestParam int classId, HttpSession session,
                                        @RequestParam(value = "error" , required = false)String error){
        ModelAndView modelAndView = new ModelAndView("students");
        Optional<EazyClass> eazyClass = eazyClassRepository.findById(classId);
        modelAndView.addObject("eazyClass", eazyClass.get());
        modelAndView.addObject("person", new Person());
        session.setAttribute("eazyClass", eazyClass.get());
        if(error!=null){
            String errorMessage = "Invalid email entered!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }
    @PostMapping("/addStudent")
    public ModelAndView addStudent(Model model, @ModelAttribute("person") Person person, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        EazyClass eazyClass = (EazyClass) session.getAttribute("eazyClass");
        Person personEntity = personRepository.readByEmail(person.getEmail());
        if(personEntity==null || !(personEntity.getPersonId()>0)){
            modelAndView.setViewName("redirect:/admin/displayStudents?classId="+eazyClass.getClassId()
                    +"&error=true");
            return modelAndView;
        }
        personEntity.setEazyClass(eazyClass);
        personRepository.save(personEntity);
        eazyClass.getPersons().add(personEntity);
        eazyClassRepository.save(eazyClass);
        modelAndView.setViewName("redirect:/admin/displayStudents?classId="+eazyClass.getClassId());
        return modelAndView;
    }
@GetMapping("/deleteStudent")
  public ModelAndView deleteStudent(Model model, @RequestParam int personId,HttpSession session){
      EazyClass eazyClass = (EazyClass) session.getAttribute("eazyClass");
      Optional<Person> person = personRepository.findById(personId);
    person.get().setEazyClass(null);
    eazyClass.getPersons().remove(person.get());
    eazyClassRepository.save(eazyClass);
    session.setAttribute("eazyClass",eazyClass);
    ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId="+eazyClass.getClassId());
    return modelAndView;
  }


}
