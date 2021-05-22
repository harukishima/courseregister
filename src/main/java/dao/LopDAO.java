package dao;

import entity.GiaovienEntity;
import entity.LopEntity;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;

import java.util.List;

public class LopDAO {
    public static LopEntity getByIdLoadAll(int id) {
        LopEntity lopEntity = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            lopEntity = session.get(LopEntity.class, id);
            Hibernate.initialize(lopEntity.getSinhviensByMalop());
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return lopEntity;
    }

    public static List<LopEntity> getListLoadAll() {
        List<LopEntity> list = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("from LopEntity");
            list = query.list();
            for(LopEntity i : list) {
                Hibernate.initialize(i.getSinhviensByMalop());
            }
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return list;
    }
}
