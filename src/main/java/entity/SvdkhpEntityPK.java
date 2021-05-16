package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class SvdkhpEntityPK implements Serializable {
    private int masv;
    private int idhocphan;

    @Column(name = "masv")
    @Id
    public int getMasv() {
        return masv;
    }

    public void setMasv(int masv) {
        this.masv = masv;
    }

    @Column(name = "idhocphan")
    @Id
    public int getIdhocphan() {
        return idhocphan;
    }

    public void setIdhocphan(int idhocphan) {
        this.idhocphan = idhocphan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SvdkhpEntityPK that = (SvdkhpEntityPK) o;

        if (masv != that.masv) return false;
        if (idhocphan != that.idhocphan) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = masv;
        result = 31 * result + idhocphan;
        return result;
    }
}
