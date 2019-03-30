package com.example.demo.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Genero", schema = "transversal")
public class GeneroEntity {
    private int idGenero;
    private String nombre;

    @Id
    @Column(name = "id_genero", nullable = false)
    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    @Basic
    @Column(name = "Nombre", nullable = false, length = 20)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeneroEntity that = (GeneroEntity) o;

        if (idGenero != that.idGenero) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idGenero;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}
