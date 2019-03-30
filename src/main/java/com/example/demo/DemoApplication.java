package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        /*Session session = HibernateUtil.getSessionFactory().openSession();
        List resultado = session.createQuery("from AlbumEntity ").list();

        for (AlbumEntity album : (List<AlbumEntity>)resultado){
            System.out.println(album.getIdAlbum()+album.getTitulo()+album.getPrecio());
        }*/

    }
}


