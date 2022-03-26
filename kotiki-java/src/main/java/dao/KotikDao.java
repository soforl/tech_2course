package dao;

import entities.Kotik;
import hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class KotikDao implements Dao<Kotik> {
    @Override
    public Kotik findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Kotik.class, id);
    }

    @Override
    public List<Kotik> getAll() {
        return HibernateSessionFactoryUtil
                .getSessionFactory().openSession().createQuery("From Kotik ").list();
    }

    @Override
    public void save(Kotik kotik) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(kotik);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Kotik kotik) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(kotik);
        session.close();
    }

    @Override
    public void delete(Kotik kotik) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(kotik);
        transaction.commit();
        session.close();
    }
}
