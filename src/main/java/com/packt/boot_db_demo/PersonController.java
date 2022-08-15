package com.packt.boot_db_demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/persons")
public class PersonController {
    
    @Autowired PersonMapper personMapper;

    @GetMapping
    public String list(ModelMap model){
        List<Person> persons = personMapper.getPersons();
        model.put("persons", persons);

        return "list";
    }

    @GetMapping("/{id}")
    public String detail(ModelMap model, @PathVariable Integer id){
        System.out.println("Detail id: " + id);
        Person person = personMapper.getPersonById(id);
        model.put("person", person);

        return "detail";
    }

    @GetMapping("/form")
    public String form(ModelMap model){
        Person p = new Person();
        model.put("person", p);
        return "form";
    }

    @GetMapping("/form/{id}")
    public String editForm(ModelMap model, @PathVariable  Integer id){
        Person p = personMapper.getPersonById(id);
        model.put("person", p);
        return "form";
    }

    @PostMapping("/form")
    public String submitForm(Person person){
        System.out.println("Submiting form person id: " + person.getId());
        if ( person.getId() != null ){
            personMapper.save(person);
        }else{
            personMapper.insert(person);
        }

        return "redirect:/persons/";
    }

    @GetMapping("/{id}/delete")
    public String deletePerson(@PathVariable Integer id){ 
        personMapper.delete(id);

        return "redirect:/persons/";
    }

}