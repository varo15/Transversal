package com.example.demo.Dao;

import com.example.demo.Entity.GrupoEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.function.Consumer;

@Repository
public class GrupoDao implements Dao<GrupoEntity> {
    private EntityManager em;

    public GrupoDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public GrupoEntity getByID(int id) {
        return (em.find(GrupoEntity.class, id));
    }

    @Override
    public List<GrupoEntity> getAll() {
        Query query = em.createQuery("SELECT e FROM GrupoEntity e");
        return query.getResultList();
    }

    @Override
    public void save(GrupoEntity grupoEntity) {
        try {
            em.getTransaction().begin();
            em.persist(grupoEntity);
            em.getTransaction().commit();
        } catch (javax.persistence.RollbackException e) {
            System.out.println("Grupo repetido");
        }
    }

    @Override
    public void update(GrupoEntity grupoEntity) {
        executeInsideTransaction(entityManager -> entityManager.merge(grupoEntity));
    }

    @Override
    public void delete(int id) {
        executeInsideTransaction(entityManager -> entityManager.remove(getByID(id)));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            action.accept(em);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
