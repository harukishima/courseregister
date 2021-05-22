package util;

import com.sun.xml.fastinfoset.algorithm.HexadecimalEncodingAlgorithm;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.Random;


//Tham khao
public class hashUtils {
    public static String generateRandomAlphanumeric(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String encodeHMAC(String data, String key) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Boolean checkPassword(String password, String hashedPassword) {
        String[] pass = hashedPassword.split("\\$");
        if (pass.length != 2)
            return false;
        return Objects.equals(encodeHMAC(password, pass[0]), pass[1]);
    }

    public static String hashPassword(String password) {
        String key = generateRandomAlphanumeric(16);
        String hashed = encodeHMAC(password, key);
        return key + "$" + hashed;
    }
}
