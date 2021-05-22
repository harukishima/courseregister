package org.example;

import entity.GiaovienEntity;
import entity.SinhvienEntity;
import util.HibernateUtils;
import view.GiaoVuLogIn;
import view.StudentLogIn;

/**
 * Hello world!
 *
 */
public class App 
{
    public static StudentLogIn studentLogIn = null;
    public static GiaoVuLogIn giaoVuLogIn = null;
    public static GiaovienEntity currentGV = null;
    public static SinhvienEntity currentSV = null;
    public static void main( String[] args )
    {
        HibernateUtils.getSessionFactory().openSession();
        App.studentLogIn = new StudentLogIn();
    }
}
