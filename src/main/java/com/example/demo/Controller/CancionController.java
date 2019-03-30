package com.example.demo.Controller;

import com.example.demo.Dao.CancionDao;
import com.example.demo.Entity.CancionEntity;
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
public class CancionController {

    @Autowired
    CancionDao cancionDao;

    @GetMapping("/add_cancion")
    public String showAddCancionForm(Model model){
        model.addAttribute("cancion",new CancionEntity());
        return "add-cancion";
    }

    @GetMapping("/list_cancion")
    public String showListCancionForm(Model model){
        List<CancionEntity> lista = cancionDao.getAll();
        lista.forEach(a->System.out.println(a.getIdCancion()));
        model.addAttribute("canciones", cancionDao.getAll());
        return "list-canciones";
    }

    @GetMapping("/showCancion/{id}")
    public CancionEntity showAlbumById(@PathVariable int id, Model model) {
        CancionEntity cancionEntity = cancionDao.getByID(id);
        model.addAttribute("cancion",cancionEntity);
        return cancionEntity;
    }

    @PostMapping("/addCancion")
    public String addCancion(@Valid CancionEntity cancionEntity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-cancion";
        }

        cancionDao.save(cancionEntity);
        model.addAttribute("canciones", cancionDao.getAll());
        return "list-album";
    }

    // additional CRUD methods
}