package dao;


import entity.GiaovuEntity;
import entity.SinhvienEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;

import java.util.List;

public class SinhvienDAO {
    public static List<SinhvienEntity> findSV(String search, int malop) {
        List<SinhvienEntity> list = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Query query;
            if (malop == -1) {
                query = session.createQuery("from SinhvienEntity where lower(fullname) like :searchString order by masv asc");
            } else {
                query = session.createQuery("from SinhvienEntity where lower(fullname) like :searchString and malop = :lop order by masv asc");
                query.setParameter("lop", malop);
            }
            query.setParameter("searchString", "%"+search.toLowerCase()+"%");
            list = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return list;
    }

}
