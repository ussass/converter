package ru.trofimov.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.trofimov.models.ExchangeRates;
import ru.trofimov.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class ValuteDaoImpl implements ValuteDao {

    @Override
    public void save(ExchangeRates rates) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(rates);
            tx1.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception: " + e.toString());
        }
    }

    @Override
    public List<ExchangeRates> findAll() {
        return (List<ExchangeRates>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM ExchangeRates").list();
    }

    @Override
    public ExchangeRates getRatesByDate(int date) {
        return (ExchangeRates) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM ExchangeRates WHERE rates_date = " + date).uniqueResult();
    }
}
