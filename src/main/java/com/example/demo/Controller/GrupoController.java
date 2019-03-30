package com.example.demo.Controller;

import com.example.demo.Dao.GrupoDao;
import com.example.demo.Entity.GrupoEntity;
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
public class GrupoController {

    @Autowired
    GrupoDao grupoDao;

    @GetMapping("/add_grupo")
    public String showAddGrupoForm(Model model){
        model.addAttribute("grupo",new GrupoEntity());
        return "add-grupo";
    }

    @GetMapping("/list_grupo")
    public String showListGrupoForm(Model model){
        List<GrupoEntity> lista = grupoDao.getAll();
        lista.forEach(a->System.out.println(a.getIdGrupo()));
        model.addAttribute("grupos", grupoDao.getAll());
        return "list-grupos";
    }

    @GetMapping("/showGrupo/{id}")
    public GrupoEntity showAlbumById(@PathVariable int id, Model model) {
        GrupoEntity grupoEntity = grupoDao.getByID(id);
        model.addAttribute("grupo",grupoEntity);
        return grupoEntity;
    }

    @PostMapping("/addGrupo")
    public String addGrupo(@Valid GrupoEntity grupoEntity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-grupo";
        }

        grupoDao.save(grupoEntity);
        model.addAttribute("grupos", grupoDao.getAll());
        return "list-grupos";
    }

    // additional CRUD methods
}