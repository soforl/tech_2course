package dao;

import entities.Friends;
import hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FriendsDao implements Dao<Friends>{
    @Override
    public Friends findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Friends.class, id);
    }

    @Override
    public List<Friends> getAll() {
        return HibernateSessionFactoryUtil
                .getSessionFactory().openSession().createQuery("From Friends ").list();

    }

    @Override
    public void save(Friends friends) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(friends);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Friends friends) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(friends);
        session.close();
    }

    @Override
    public void delete(Friends friends) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(friends);
        transaction.commit();
        session.close();
    }
}
