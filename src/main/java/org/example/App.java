package org.example;

import dao.GiaovuDAO;
import entity.GiaovienEntity;
import entity.GiaovuEntity;
import entity.SinhvienEntity;
import util.HibernateUtils;
import view.GVDashboard;
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
    public static GiaovuEntity currentGV = null;
    public static SinhvienEntity currentSV = null;
    public static GVDashboard gvDashboard = null;
    public static void main( String[] args )
    {
        HibernateUtils.getSessionFactory().openSession();
        App.studentLogIn = new StudentLogIn();
        //GiaovuDAO.register("admin", "admin");
    }
}
