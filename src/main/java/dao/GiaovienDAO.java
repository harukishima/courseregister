package dao;

import entity.GiaovienEntity;
import entity.GiaovuEntity;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtils;

import java.util.List;

public class GiaovienDAO {
    public static GiaovienEntity getByIdLoadAll(int id) {
        GiaovienEntity giaovienEntity = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            giaovienEntity = session.get(GiaovienEntity.class, id);
            Hibernate.initialize(giaovienEntity.getHocphansByMagv());
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return giaovienEntity;
    }

    public static List<GiaovienEntity> getListLoadAll() {
        List<GiaovienEntity> list = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("from GiaovienEntity");
            list = query.list();
            for(GiaovienEntity i : list) {
                Hibernate.initialize(i.getHocphansByMagv());
            }
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return list;
    }
}
