package com.example.demo.Entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Pertenece", schema = "transversal")
public class PerteneceEntity {
    private int idPertenece;
    private GrupoEntity grupoEntity;
    private ArtistaEntity artistaEntity;
    private Date fechaEntrada;

    @Id
    @Column(name = "id_pertenece", nullable = false)
    public int getIdPertenece() {
        return idPertenece;
    }

    public void setIdPertenece(int idPertenece) {
        this.idPertenece = idPertenece;
    }

    @ManyToOne
    public GrupoEntity getGrupoEntity() {
        return grupoEntity;
    }

    public void setGrupoEntity(GrupoEntity grupoEntity) {
        this.grupoEntity = grupoEntity;
    }

    @ManyToOne
    public ArtistaEntity getArtistaEntity() {
        return artistaEntity;
    }

    public void setArtistaEntity(ArtistaEntity artistaEntity) {
        this.artistaEntity = artistaEntity;
    }

    @Column(name = "fecha_entrada", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
}