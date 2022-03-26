package dao;

import entities.Breed;
import hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BreedDao implements Dao<Breed>{
    @Override
    public Breed findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Breed.class, id);
    }

    @Override
    public List<Breed> getAll() {
        return HibernateSessionFactoryUtil
                .getSessionFactory().openSession().createQuery("From Breed ").list();
    }

    @Override
    public void save(Breed breed) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(breed);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Breed breed) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(breed);
        session.close();
    }

    @Override
    public void delete(Breed breed) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(breed);
        transaction.commit();
        session.close();
    }
}
