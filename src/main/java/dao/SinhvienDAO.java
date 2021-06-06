package dao;


import entity.SinhvienEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;
import util.hashUtils;

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
            query.setParameter("searchString", "%" + search.toLowerCase() + "%");
            list = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return list;
    }

    public static SinhvienEntity LogIn(String username, String password) {
        try {
            SinhvienEntity sinhvienEntity = dataCRUD.getWithId(SinhvienEntity.class, Integer.parseInt(username));
            if (sinhvienEntity != null && hashUtils.checkPassword(password, sinhvienEntity.getPass())) {
                return sinhvienEntity;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

}
