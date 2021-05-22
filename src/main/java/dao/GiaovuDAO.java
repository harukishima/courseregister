package dao;

import entity.GiaovuEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;
import util.hashUtils;

import java.util.List;

public class GiaovuDAO {
    public static GiaovuEntity LogIn(String username, String password) {
        try {
            GiaovuEntity giaovuEntity = dataCRUD.getWithId(GiaovuEntity.class, Integer.parseInt(username));
            if (giaovuEntity != null && hashUtils.checkPassword(password, giaovuEntity.getPass())) {
                return giaovuEntity;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Boolean register(String fullName, String password) {
        GiaovuEntity giaovuEntity = new GiaovuEntity();
        giaovuEntity.setFullname(fullName);
        String hash = hashUtils.hashPassword(password);
        giaovuEntity.setPass(hash);
        return dataCRUD.insertEntity(giaovuEntity);
    }

    public static List<GiaovuEntity> findGV(String search) {
        List<GiaovuEntity> list = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("from GiaovuEntity where lower(fullname) like :searchString");
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
