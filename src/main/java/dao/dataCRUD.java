package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtils;

import java.util.List;

public class dataCRUD {
    public static <T> List<T> getList(Class<T> tClass) {
        List<T> list = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("from " + tClass.getName());
            list = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return list;
    }

    public static <T> List<T> customGetList(String hql) {
        List<T> list = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Query query = session.createQuery(hql);
            list = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return list;
    }

    public static <T> T getWithId(Class<T> tClass, int id) {
        T entity = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            entity = session.get(tClass, id);
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return entity;
    }

    public static <T> boolean insertEntity(T entity) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (HibernateException ex) {
            assert transaction != null;
            transaction.rollback();
            System.err.println(ex);
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static <T> boolean updateEntity(T entity, int id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        if (getWithId(entity.getClass(), id) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (HibernateException ex) {
            assert transaction != null;
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }

    public static <T> boolean deleteEntity(Class<T> tClass, int id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        T entity = getWithId(tClass, id);
        if (entity == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (HibernateException ex) {
            assert transaction != null;
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
}
