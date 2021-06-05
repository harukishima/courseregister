package dao;

import entity.HockiEntity;
import entity.HocphanEntity;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;

import java.util.List;

public class HocphanDAO {
    public static List<HocphanEntity> getListLoadAllByHocKy(HockiEntity hockiEntity) {
        List<HocphanEntity> list = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("from HocphanEntity where tenhk = :hk and namhoc = :nam order by idhocphan asc");
            query.setParameter("hk", hockiEntity.getTenhk());
            query.setParameter("nam", hockiEntity.getNamhoc());
            list = query.list();
            for (HocphanEntity hp : list) {
                Hibernate.initialize(hp.getGiaovienByMagvlt());
                Hibernate.initialize(hp.getMonhocByMamh());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static HocphanEntity getByIdLoadAdd(int id) {
        HocphanEntity entity = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            entity = session.get(HocphanEntity.class, id);
            Hibernate.initialize(entity.getMonhocByMamh());
            Hibernate.initialize(entity.getGiaovienByMagvlt());
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return entity;
    }
}
