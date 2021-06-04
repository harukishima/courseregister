package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "svdkhp", schema = "public", catalog = "coursedb")
@IdClass(SvdkhpEntityPK.class)
public class SvdkhpEntity {
    private int masv;
    private int idhocphan;
    private Timestamp thoigiandk;
    private SinhvienEntity sinhvienByMasv;
    private HocphanEntity hocphanByIdhocphan;

    @Id
    @Column(name = "masv")
    public int getMasv() {
        return masv;
    }

    public void setMasv(int masv) {
        this.masv = masv;
    }

    @Id
    @Column(name = "idhocphan")
    public int getIdhocphan() {
        return idhocphan;
    }

    public void setIdhocphan(int idhocphan) {
        this.idhocphan = idhocphan;
    }

    @Basic
    @Column(name = "thoigiandk")
    public Timestamp getThoigiandk() {
        return thoigiandk;
    }

    public void setThoigiandk(Timestamp thoigiandk) {
        this.thoigiandk = thoigiandk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SvdkhpEntity that = (SvdkhpEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "masv", referencedColumnName = "masv", nullable = false, insertable = false, updatable= false)
    public SinhvienEntity getSinhvienByMasv() {
        return sinhvienByMasv;
    }

    public void setSinhvienByMasv(SinhvienEntity sinhvienByMasv) {
        this.sinhvienByMasv = sinhvienByMasv;
    }

    @ManyToOne
    @JoinColumn(name = "idhocphan", referencedColumnName = "idhocphan", nullable = false, insertable = false, updatable= false)
    public HocphanEntity getHocphanByIdhocphan() {
        return hocphanByIdhocphan;
    }

    public void setHocphanByIdhocphan(HocphanEntity hocphanByIdhocphan) {
        this.hocphanByIdhocphan = hocphanByIdhocphan;
    }
}
