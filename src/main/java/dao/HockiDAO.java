package dao;

import entity.HockiEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtils;


public class HockiDAO {
    public static Boolean setCurrentHK(String tenhk, int nam) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        int success = 0;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update HockiEntity set hkhientai = false where hkhientai = true");
            query.executeUpdate();
            query = session.createQuery("update HockiEntity set hkhientai = true where tenhk = :ten and namhoc = :nam");
            query.setParameter("ten", tenhk);
            query.setParameter("nam", nam);
            success = query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            assert transaction != null;
            transaction.rollback();
        } finally {
            session.close();
        }
        return success == 1;
    }

    public static HockiEntity getCurrentHK() {
        HockiEntity hockiEntity = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("from HockiEntity where hkhientai = true");
            hockiEntity = (HockiEntity) query.getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return hockiEntity;
    }

    public static HockiEntity getHK(String tenhk, int namhoc) {
        HockiEntity hockiEntity = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("from HockiEntity where tenhk = :ten and namhoc = :nam");
            query.setParameter("ten", tenhk);
            query.setParameter("nam", namhoc);
            hockiEntity = (HockiEntity) query.getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return hockiEntity;
    }

}
