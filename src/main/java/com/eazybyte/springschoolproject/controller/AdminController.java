package com.eazybyte.springschoolproject.controller;

import com.eazybyte.springschoolproject.model.Courses;
import com.eazybyte.springschoolproject.model.EazyClass;
import com.eazybyte.springschoolproject.model.Person;
import com.eazybyte.springschoolproject.repository.CoursesRepository;
import com.eazybyte.springschoolproject.repository.EazyClassRepository;
import com.eazybyte.springschoolproject.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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
    private final CoursesRepository coursesRepository;

    public AdminController(EazyClassRepository eazyClassRepository, PersonRepository personRepository, CoursesRepository coursesRepository) {
        this.eazyClassRepository = eazyClassRepository;
        this.personRepository = personRepository;
        this.coursesRepository = coursesRepository;
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

  @GetMapping("/displayCourses")
  public ModelAndView displayCourse(Model model){
     //  List<Courses> courses  = coursesRepository.findByOrderByName();
      List<Courses> courses  = coursesRepository.findAll(Sort.by("name").ascending());
       ModelAndView modelAndView = new ModelAndView("courses_secure");
       modelAndView.addObject("courses", courses);
       modelAndView.addObject("course",new Courses());
       return modelAndView;
  }
@PostMapping ("/addNewCourse")
  public ModelAndView addNewCourse(Model model, @ModelAttribute("course") Courses courses){
    ModelAndView modelAndView  = new ModelAndView();
    coursesRepository.save(courses);
    modelAndView.setViewName("redirect:/admin/displayCourses");
  return modelAndView;
  }
@GetMapping("/viewStudents")
  public ModelAndView viewStudents (Model model, @RequestParam int id,
                                    HttpSession session, @RequestParam(required = false) String error){
        String errorMessage = null;
          ModelAndView modelAndView = new ModelAndView("course_students");
          Optional<Courses> courses = coursesRepository.findById(id);
          modelAndView.addObject("courses", courses.get());
          modelAndView.addObject("person",new Person());
          session.setAttribute("courses",courses.get());
          if(error!=null){
              errorMessage = "Invalid Email entered";
              modelAndView.addObject("errorMessage", errorMessage);
          }
          return modelAndView;
  }
@PostMapping("/addStudentToCourse")
  public ModelAndView modelAndView (Model model, @ModelAttribute("person") Person person,
                                    HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Courses courses =(Courses) session.getAttribute("courses");
        Person personEntity = personRepository.readByEmail(person.getEmail());

        if(personEntity ==null || !(personEntity.getPersonId()>0)){
            modelAndView.setViewName("redirect:/admin/viewStudents?id="+ courses.getCourseId()+"&error=true");
            return modelAndView;
        }
        personEntity.getCourses().add(courses);
        courses.getPersons().add(personEntity);
        personRepository.save(personEntity);
        session.setAttribute("courses",courses);
        modelAndView.setViewName("redirect:/admin/viewStudents?id="+ courses.getCourseId());
        return modelAndView;
  }
    @GetMapping("/deleteStudentFromCourse")
    public ModelAndView deleteStudentFromCourses (Model model , @RequestParam int personId, HttpSession session){
        Courses courses = (Courses) session.getAttribute("courses");
        Optional<Person> person = personRepository.findById(personId);
        courses.getPersons().remove(person);
        person.get().getCourses().remove(courses);
        personRepository.save(person.get());
        session.setAttribute("courses",courses);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/viewStudents?id="+courses.getCourseId());
        return modelAndView;
    }

}
