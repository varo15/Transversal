package com.example.demo.Entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Album", schema = "transversal")
public class AlbumEntity {
    private int idAlbum;
    private String titulo;
    private String precio;
    private Date fechaLanzamiento;
    private GrupoEntity grupoEntity;
    private int idGrupo;

    @Id
    @Column(name = "id_album", nullable = false)
    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    @Basic
    @Column(name = "titulo", nullable = false, length = 20)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Basic
    @Column(name = "precio", nullable = false, length = 20)
    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Basic
    @Column(name = "fecha_lanzamiento", nullable = false)
    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    @ManyToOne
    public GrupoEntity getGrupoEntity() {
        return grupoEntity;
    }

    public void setGrupoEntity(GrupoEntity grupoEntity) {
        this.grupoEntity = grupoEntity;
    }

    @Transient
    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlbumEntity that = (AlbumEntity) o;

        if (idAlbum != that.idAlbum) return false;
        if (titulo != null ? !titulo.equals(that.titulo) : that.titulo != null) return false;
        if (precio != null ? !precio.equals(that.precio) : that.precio != null) return false;
        if (fechaLanzamiento != null ? !fechaLanzamiento.equals(that.fechaLanzamiento) : that.fechaLanzamiento != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAlbum;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (precio != null ? precio.hashCode() : 0);
        result = 31 * result + (fechaLanzamiento != null ? fechaLanzamiento.hashCode() : 0);
        return result;
    }
}
