package com.example.demo.Controller;

import com.example.demo.Dao.ArtistaDao;
import com.example.demo.Dao.GrupoDao;
import com.example.demo.Entity.ArtistaEntity;
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
public class ArtistaController {

    @Autowired
    ArtistaDao artistaDao;
    @Autowired
    GrupoDao grupoDao;

    @GetMapping("/add_artista")
    public String showAddArtistaForm(Model model){
        model.addAttribute("artistaEntity",new ArtistaEntity());
        model.addAttribute("grupos", grupoDao.getAll());
        return "add-artista";
    }

    @GetMapping("/list_artista")
    public String showListCancionForm(Model model) {
        List<ArtistaEntity> lista = artistaDao.getAll();
        lista.forEach(a -> System.out.println(a.getIdArtista()));
        model.addAttribute("artistas", artistaDao.getAll());
        return "list-artistas";
    }

    @GetMapping("/showArtista/{id}")
    public ArtistaEntity showArtistaById(@PathVariable int id, Model model) {
        ArtistaEntity artistaEntity = artistaDao.getByID(id);
        model.addAttribute("artista",artistaEntity);
        return artistaEntity;
    }

    @PostMapping("/addArtista")
    public String addArtista(@Valid ArtistaEntity artistaEntity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-artista";
        }

        artistaDao.save(artistaEntity);
        model.addAttribute("artistas", artistaDao.getAll());
        return "list-artistas";
    }

    // additional CRUD methods
}