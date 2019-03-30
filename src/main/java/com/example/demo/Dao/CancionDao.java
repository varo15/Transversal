package com.example.demo.Dao;

import com.example.demo.Entity.CancionEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.function.Consumer;

@Repository
public class CancionDao implements Dao<CancionEntity> {
    private EntityManager em;

    public CancionDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public CancionEntity getByID(int id) {
        return (em.find(CancionEntity.class, id));
    }

    @Override
    public List<CancionEntity> getAll() {
        Query query = em.createQuery("SELECT e FROM CancionEntity e");
        return query.getResultList();
    }

    @Override
    public void save(CancionEntity cancionEntity) {
        try {
            em.getTransaction().begin();
            em.persist(cancionEntity);
            em.getTransaction().commit();
        } catch (javax.persistence.RollbackException e) {
            System.out.println("Cancion repetido");
        }
    }

    @Override
    public void update(CancionEntity cancionEntity) {
        executeInsideTransaction(entityManager -> entityManager.merge(cancionEntity));
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
