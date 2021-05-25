package dao;

import entity.MonhocEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;

import java.util.List;

public class MonhocDAO {
    public static List<MonhocEntity> findMH(String search) {
        List<MonhocEntity> list = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("from MonhocEntity where lower(tenmh) like :name order by mamh asc");
            query.setParameter("name", "%"+search.toLowerCase()+"%");
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
        return list;
    }
}
