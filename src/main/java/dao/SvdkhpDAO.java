package dao;

import entity.SvdkhpEntity;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;
import util.NlpUtils;

import java.util.List;

public class SvdkhpDAO {
    public static List<SvdkhpEntity> getListSVOfHP(int idHP) {
        List<SvdkhpEntity> list = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("from SvdkhpEntity where idhocphan = :idhp");
            query.setParameter("idhp", idHP);
            list = query.list();
            for (SvdkhpEntity entity : list) {
                Hibernate.initialize(entity.getHocphanByIdhocphan().getGiaovienByMagvlt());
                Hibernate.initialize(entity.getHocphanByIdhocphan().getMonhocByMamh());
                Hibernate.initialize(entity.getSinhvienByMasv());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<SvdkhpEntity> getByIdSV(int id) {
        List<SvdkhpEntity> list = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("from SvdkhpEntity where masv = :idsv");
            query.setParameter("idsv", id);
            list = query.list();
            for (SvdkhpEntity entity : list) {
                Hibernate.initialize(entity.getHocphanByIdhocphan().getGiaovienByMagvlt());
                Hibernate.initialize(entity.getHocphanByIdhocphan().getMonhocByMamh());
                Hibernate.initialize(entity.getSinhvienByMasv());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static SvdkhpEntity getByIdSVandHP(int idSV, int idHP) {
        SvdkhpEntity svdkhpEntity = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("from SvdkhpEntity where masv = :idsv and idhocphan = :idhp");
            query.setParameter("idsv", idSV);
            query.setParameter("idhp", idHP);
            List q = query.list();
            svdkhpEntity = (q.isEmpty()?null: (SvdkhpEntity) q.get(0));
            if (svdkhpEntity != null) {
                Hibernate.initialize(svdkhpEntity.getHocphanByIdhocphan().getGiaovienByMagvlt());
                Hibernate.initialize(svdkhpEntity.getHocphanByIdhocphan().getMonhocByMamh());
                Hibernate.initialize(svdkhpEntity.getSinhvienByMasv());
            }
        } catch (HibernateException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return svdkhpEntity;
    }

    public static List<SvdkhpEntity> findSV(int idHP, String search) {
        List<SvdkhpEntity> list = null;
        search = NlpUtils.removeAccent(search).toLowerCase();
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("select SvdkhpEntity from SvdkhpEntity dk join SinhvienEntity sv where dk.idhocphan = :idhp and sv.fullname like :searchString");
            query.setParameter("idhp", idHP);
            query.setParameter("searchString", "%"+search+"%");
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
}
