package com.example.demo.Dao;

import com.example.demo.Entity.GeneroEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.function.Consumer;

@Repository
public class GeneroDao implements Dao<GeneroEntity> {
    private EntityManager em;

    public GeneroDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public GeneroEntity getByID(int id) {
        return (em.find(GeneroEntity.class, id));
    }

    @Override
    public List<GeneroEntity> getAll() {
        Query query = em.createQuery("SELECT e FROM GeneroEntity e");
        return query.getResultList();
    }

    @Override
    public void save(GeneroEntity generoEntity) {
        try {
            em.getTransaction().begin();
            em.persist(generoEntity);
            em.getTransaction().commit();
        } catch (javax.persistence.RollbackException e) {
            System.out.println("Genero repetido");
        }
    }

    @Override
    public void update(GeneroEntity generoEntity) {
        executeInsideTransaction(entityManager -> entityManager.merge(generoEntity));
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
