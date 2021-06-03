package dao;

import entity.GiaovienEntity;
import entity.LopEntity;
import entity.LopEntityExtended;
import entity.SinhvienEntity;
import org.hibernate.*;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import util.HibernateUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
                int nam = 0, nu = 0;
                //Cach 1
//                query = session.createQuery("select count(*) from SinhvienEntity where malop = :ml and gioitinh = :gt");
//                query.setParameter("ml", e.getMalop());
//                query.setParameter("gt", "NAM");
//                nam = query.getFirstResult();
//                query.setParameter("gt", "NU");
//                nu = query.getFirstResult();
                //Cach 2
//                Criteria criteria = session.createCriteria(SinhvienEntity.class);
//                criteria.setProjection(Projections.rowCount());
//                criteria.add(Restrictions.eq("gioitinh", "NAM"));
//                criteria.add(Restrictions.eq("malop", e.getMalop()));
//                criteria = session.createCriteria(SinhvienEntity.class);
//                criteria.setProjection(Projections.rowCount());
//                criteria.add(Restrictions.eq("gioitinh", "NU"));
//                criteria.add(Restrictions.eq("malop", e.getMalop()));
//                nam = Math.toIntExact((long) criteria.list().get(0));
                //Cach 3
                query = session.createQuery("select count(*), gioitinh from SinhvienEntity group by gioitinh");
                List<?> c = query.list();
                for(int i=0; i<list.size(); i++) {
                    Object[] row = (Object[]) c.get(i);
                    System.out.println(row[0]+", "+ row[1]);
                    if (row[1].equals("NAM")) {
                        nam = Math.toIntExact((long) row[0]);
                    } else {
                        nu = Math.toIntExact((long) row[0]);
                    }
                }
                list.add(new LopEntityExtended(e, nam, nu));
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static Boolean deleteLop(int id) {
        LopEntity lopEntity = dataCRUD.getWithId(LopEntity.class, id);
        if (lopEntity == null) {
            return false;
        }
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update SinhvienEntity set malop = null where malop = :ml");
            query.setParameter("ml", id);
            query.executeUpdate();
            session.delete(lopEntity);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            assert transaction != null;
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static Boolean checkClassEmpty(int id) {
        LopEntity lopEntity = dataCRUD.getWithId(LopEntity.class, id);
        List<SinhvienEntity> list = null;
        if (lopEntity == null) {
            return false;
        }
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("from SinhvienEntity where malop = :ml");
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list != null && list.isEmpty();
    }
}
