package com.example.demo.Controller;

import com.example.demo.Dao.GeneroDao;
import com.example.demo.Entity.GeneroEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GeneroController {

    @Autowired
    GeneroDao generoDao;

    @GetMapping("/add_genero")
    public String showAddGeneroForm(Model model){
        model.addAttribute("genero",new GeneroEntity());
        return "add-genero";
    }

    @GetMapping("/list_genero")
    public String showListGeneroForm(Model model){
        List<GeneroEntity> lista = generoDao.getAll();
        lista.forEach(a->System.out.println(a.getIdGenero()));
        model.addAttribute("generos", generoDao.getAll());
        return "list-generos";
    }

    @GetMapping("/showGenero/{id}")
    public GeneroEntity showGeneroById(@PathVariable int id, Model model) {
        GeneroEntity generoEntity = generoDao.getByID(id);
        model.addAttribute("genero",generoEntity);
        return generoEntity;
    }

    @PostMapping("/addGenero")
    public String addGenero(@Valid GeneroEntity generoEntity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-genero";
        }

        generoDao.save(generoEntity);
        model.addAttribute("generos", generoDao.getAll());
        return "list-generos";
    }

    // additional CRUD methods
}