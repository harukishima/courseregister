package dao;

import entity.SvdkhpEntity;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;

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
}
