package util;

import org.apache.commons.lang3.ArrayUtils;

public class HocphanUtils {
    public final static String[] weekDay = {"Chủ nhật", "Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy"};
    public final static String[] caHoc = {"7h30 - 9h30","9h30 - 11h30","13h30 - 15h30","15h30 - 17h30"};

    public static String getNgayTrongTuan(int date) {
        return weekDay[date];
    }

    public static String getCa(int ca) {
        return caHoc[ca];
    }

    public static int getIndexOfDate(String date) {
        return ArrayUtils.indexOf(weekDay, date);
    }

    public static int getIndexOfCaHoc(String ca) {
        return ArrayUtils.indexOf(caHoc, ca);
    }
}
