package util;

public class HocphanUtils {
    public static String getNgayTrongTuan(int date) {
        switch (date) {
            case 1:
                return "Chủ nhật";
            case 2:
                return "Thứ hai";
            case 3:
                return "Thứ ba";
            case 4:
                return "Thứ tư";
            case 5:
                return "Thứ năm";
            case 6:
                return "Thứ sáu";
            case 7:
                return "Thứ bảy";
            default:
                return "";
        }
    }

    public static String getCa(int ca) {
        switch (ca) {
            case 1:
                return "7h30 - 9h30";
            case 2:
                return "9h30 - 11h30";
            case 3:
                return "13h30 - 15h30";
            case 4:
                return "15h30 - 17h30";
            default:
                return "";
        }
    }
}
