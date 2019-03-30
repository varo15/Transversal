package com.example.demo.Entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Artista", schema = "transversal")
public class ArtistaEntity {
    private int idArtista;
    private String nombre;
    private String apellidos;
    private String apodo;
    private Date fechaNacimiento;
    private int idGrupo;

    @Id
    @Column(name = "id_artista", nullable = false)
    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    @Basic
    @Column(name = "Nombre", nullable = false, length = 20)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "Apellidos", nullable = true, length = 20)
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Basic
    @Column(name = "Apodo", nullable = true, length = 20)
    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    @Basic
    @Column(name = "Fecha_Nacimiento", nullable = false)
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

        ArtistaEntity that = (ArtistaEntity) o;

        if (idArtista != that.idArtista) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (apellidos != null ? !apellidos.equals(that.apellidos) : that.apellidos != null) return false;
        if (apodo != null ? !apodo.equals(that.apodo) : that.apodo != null) return false;
        if (fechaNacimiento != null ? !fechaNacimiento.equals(that.fechaNacimiento) : that.fechaNacimiento != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idArtista;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (apodo != null ? apodo.hashCode() : 0);
        result = 31 * result + (fechaNacimiento != null ? fechaNacimiento.hashCode() : 0);
        return result;
    }
}
