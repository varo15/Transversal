package com.example.demo.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Grupo", schema = "transversal")
public class GrupoEntity {
    private int idGrupo;
    private String nombre;
    private byte isActivo;
    private GeneroEntity generoEntity;

    @Id
    @Column(name = "id_grupo", nullable = false)
    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Basic
    @Column(name = "nombre", nullable = false, length = 20)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "is_activo", nullable = false)
    public byte getIsActivo() {
        return isActivo;
    }

    public void setIsActivo(byte isActivo) {
        this.isActivo = isActivo;
    }

    @ManyToOne
    public GeneroEntity getGeneroEntity() {
        return generoEntity;
    }

    public void setGeneroEntity(GeneroEntity generoEntity) {
        this.generoEntity = generoEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GrupoEntity that = (GrupoEntity) o;

        if (idGrupo != that.idGrupo) return false;
        if (isActivo != that.isActivo) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

}
