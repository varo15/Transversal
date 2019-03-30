package com.example.demo.Dao;


import com.example.demo.Entity.PerteneceEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.function.Consumer;

@Repository
public class PerteneceDao implements Dao<PerteneceEntity> {
    private EntityManagerFactory emf;

    public PerteneceDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public PerteneceEntity getByID(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        PerteneceEntity result = em.find(PerteneceEntity.class, id);
        em.getTransaction().commit();
        return result;
    }

    @Override
    public List<PerteneceEntity> getAll() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT e FROM PerteneceEntity e");
        em.getTransaction().commit();
        return query.getResultList();
    }

    @Override
    public void save(PerteneceEntity perteneceEntity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(perteneceEntity);
            em.getTransaction().commit();
        } catch (javax.persistence.RollbackException e) {
            System.out.println("Pertenece repetido");
        }

    }

    @Override
    public void update(PerteneceEntity perteneceEntity) {

        executeInsideTransaction(entityManager -> entityManager.merge(perteneceEntity));
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
