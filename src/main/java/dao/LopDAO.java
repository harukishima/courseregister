package dao;

import entity.GiaovienEntity;
import entity.LopEntity;
import entity.LopEntityExtended;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;

import java.util.ArrayList;
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

    public static List<LopEntityExtended> calListWithSum(List<LopEntity> lopEntityList) {
        List<LopEntityExtended> list = new ArrayList<>();
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Query query = null;
            for (LopEntity e : lopEntityList) {
                int nam, nu;
                query = session.createQuery("select count(*) from SinhvienEntity where malop = :ml and gioitinh = :gt");
                query.setParameter("ml", e.getMalop());
                query.setParameter("gt", "NAM");
                nam = query.getFirstResult();
                query.setParameter("gt", "NU");
                nu = query.getFirstResult();
                list.add(new LopEntityExtended(e, nam, nu));
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
}
