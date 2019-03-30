package com.example.demo.Controller;

import com.example.demo.Dao.AlbumDao;
import com.example.demo.Dao.GrupoDao;
import com.example.demo.Entity.AlbumEntity;
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
public class AlbumController {

    @Autowired
    AlbumDao albumDao;
    @Autowired
    GrupoDao grupoDao;


    @GetMapping("/add_album")
    public String showAddAlbumForm(Model model){
        AlbumEntity albumEntity = new AlbumEntity();
        model.addAttribute("albumEntity",albumEntity);
        model.addAttribute("grupos", grupoDao.getAll());
        return "add-album";
    }

    @GetMapping("/list_album")
    public String showListAlbumForm(Model model){
        List<AlbumEntity> lista = albumDao.getAll();
        lista.forEach(a->System.out.println(a.getIdAlbum()));
        model.addAttribute("albumes", albumDao.getAll());
        return "list-album";
    }

    @GetMapping("/showAlbum/{id}")
    public AlbumEntity showAlbumById(@PathVariable int id, Model model) {
        AlbumEntity albumEntity = albumDao.getByID(id);
        model.addAttribute("album",albumEntity);
        return albumEntity;
    }

    @PostMapping("/addAlbum")
    public String addAlbum(@Valid AlbumEntity albumEntity, BindingResult result, Model model) {
        if(result.hasErrors()){
            System.err.println("Bad data");
            return "list-album";
        }
        albumEntity.setGrupoEntity(grupoDao.getByID(albumEntity.getIdGrupo()));
        albumDao.save(albumEntity);
        model.addAttribute("albumes", albumDao.getAll());
        return "list-album";
    }

    // additional CRUD methods
}