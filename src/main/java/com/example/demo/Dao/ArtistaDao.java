package com.example.demo.Dao;

import com.example.demo.Entity.ArtistaEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.function.Consumer;

@Repository
public class ArtistaDao implements Dao<ArtistaEntity> {
    private EntityManagerFactory emf;

    public ArtistaDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public ArtistaEntity getByID(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        ArtistaEntity result = em.find(ArtistaEntity.class, id);
        em.getTransaction().commit();
        return result;
    }

    @Override
    public List<ArtistaEntity> getAll() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT e FROM ArtistaEntity e");
        em.getTransaction().commit();
        return query.getResultList();
    }

    @Override
    public void save(ArtistaEntity artistaEntity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(artistaEntity);
            em.getTransaction().commit();
        } catch (javax.persistence.RollbackException e) {
            System.out.println("Artista repetido");
        }

    }

    @Override
    public void update(ArtistaEntity artistaEntity) {
        executeInsideTransaction(entityManager -> entityManager.merge(artistaEntity));
    }

    @Override
    public void delete(int id) {
        executeInsideTransaction(entityManager -> entityManager.remove(getByID(id)));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityManager em = emf.createEntityManager();
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
