package dao;

import entity.GiaovuEntity;
import util.hashUtils;

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
}
