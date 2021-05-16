package org.example;

import dao.dataCRUD;
import entity.LopEntity;
import org.hibernate.Session;
import util.HibernateUtils;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        LopEntity lopEntity = dataCRUD.getWithId(LopEntity.class, 1000000);
        System.out.println(lopEntity);
    }
}
