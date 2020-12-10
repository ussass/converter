package ru.trofimov.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.trofimov.models.ConversionHistory;
import ru.trofimov.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class HistoryDaoImpl implements HistoryDao {
    @Override
    public void save(ConversionHistory history) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(history);
            tx1.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception: " + e.toString());
        }
    }

    @Override
    public List<ConversionHistory> findAll() {
        return (List<ConversionHistory>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM ConversionHistory ORDER BY id desc").list();
    }
}
