package org.example;

import entity.LopEntity;
import org.hibernate.Session;
import util.HibernateUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            LopEntity lop = new LopEntity();
            session.save(lop);
            session.getTransaction().commit();
        }
    }
}
