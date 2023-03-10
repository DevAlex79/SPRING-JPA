package org.example.services;

import org.example.entities.Produit;
import org.example.interfaces.IDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class ProduitService implements IDAO<Produit> {

    private EntityManagerFactory emf;

    private EntityManager em;

    public ProduitService(){
        emf = Persistence.createEntityManagerFactory("Exercices");

        em = emf.createEntityManager();
    }

    @Override
    public void begin() {
        em.getTransaction().begin();
        System.out.println("Démarrrage de la persistence");
    }

    @Override
    public boolean create(Produit o) {
        em.persist(o);
        return true;
    }

    @Override
    public boolean update(Produit o) {
        em.persist(o);
        return true;
    }

    @Override
    public boolean delete(Produit o) {
        em.remove(o);
        return true;
    }

    @Override
    public Produit findById(int id) {
        return em.find(Produit.class,id);
    }

    @Override
    public void envoi() {
        em.getTransaction().commit();
    }

    @Override
    public void close() {
        em.close();
        emf.close();
    }

    @Override
    public List<Produit> findAll() {
        Query query = em.createQuery("select p from Produit p");
        List<Produit> list = query.getResultList();
        return list;
    }

    public List<Produit> filterByPrice(double min) {
            Query query = em.createQuery("select p from Produit p where p.prix >= :min");
            query.setParameter("min", min);
            List<Produit> list = query.getResultList();
            return list;
    }

    public List<Produit> filterByDate(Date min, Date max) throws Exception {
        if(min.before(max)) {
            Query query = em.createQuery("select p from Produit p where dateAchat >= :min and dateAchat <= :max");
            query.setParameter("min", min);
                    query.setParameter("max", max);
            return query.getResultList();

        }
        throw new Exception("error date");
    }

    public double valeurStockParMarque(String marque) {
        Query query = em.createQuery("select sum(prix) from Produit where marque =:marque");
        query.setParameter("marque", marque);
        Double result = (Double) query.getSingleResult();
        return result;
    }
}