package com.example.demo.Dao;

import com.example.demo.Entity.AlbumEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.function.Consumer;

@Repository
public class AlbumDao implements Dao<AlbumEntity> {
    private EntityManagerFactory emf;

    public AlbumDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public AlbumEntity getByID(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        AlbumEntity result = em.find(AlbumEntity.class, id);
        em.getTransaction().commit();
        return result;
    }

    @Override
    public List<AlbumEntity> getAll() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT e FROM AlbumEntity e");
        em.getTransaction().commit();
        return query.getResultList();
    }

    @Override
    public void save(AlbumEntity albumEntity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(albumEntity);
            em.getTransaction().commit();
        } catch (javax.persistence.RollbackException e) {
            System.out.println("Album repetido");
        }

    }

    @Override
    public void update(AlbumEntity albumEntity) {

        executeInsideTransaction(entityManager -> entityManager.merge(albumEntity));
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
