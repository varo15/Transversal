package com.example.demo.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Cancion", schema = "transversal")
public class CancionEntity {
    private int idCancion;
    private String titulo;
    private double duracion;

    @Id
    @Column(name = "id_cancion", nullable = false)
    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
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
    @Column(name = "duracion", nullable = false, precision = 0)
    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CancionEntity that = (CancionEntity) o;

        if (idCancion != that.idCancion) return false;
        if (Double.compare(that.duracion, duracion) != 0) return false;
        if (titulo != null ? !titulo.equals(that.titulo) : that.titulo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idCancion;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        temp = Double.doubleToLongBits(duracion);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
