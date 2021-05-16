package entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "hocphan", schema = "public", catalog = "coursedb")
public class HocphanEntity {
    private int idhocphan;
    private Integer mamh;
    private Integer magvlt;
    private String phonghoc;
    private Short ngaytrongtuan;
    private Short ca;
    private Date ngaybatdau;
    private String tenhk;
    private Integer namhoc;
    private MonhocEntity monhocByMamh;
    private GiaovienEntity giaovienByMagvlt;
    private HockiEntity hocki;
    private Collection<SvdkhpEntity> svdkhpsByIdhocphan;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhocphan")
    public int getIdhocphan() {
        return idhocphan;
    }

    public void setIdhocphan(int idhocphan) {
        this.idhocphan = idhocphan;
    }

    @Basic
    @Column(name = "mamh")
    public Integer getMamh() {
        return mamh;
    }

    public void setMamh(Integer mamh) {
        this.mamh = mamh;
    }

    @Basic
    @Column(name = "magvlt")
    public Integer getMagvlt() {
        return magvlt;
    }

    public void setMagvlt(Integer magvlt) {
        this.magvlt = magvlt;
    }

    @Basic
    @Column(name = "phonghoc")
    public String getPhonghoc() {
        return phonghoc;
    }

    public void setPhonghoc(String phonghoc) {
        this.phonghoc = phonghoc;
    }

    @Basic
    @Column(name = "ngaytrongtuan")
    public Short getNgaytrongtuan() {
        return ngaytrongtuan;
    }

    public void setNgaytrongtuan(Short ngaytrongtuan) {
        this.ngaytrongtuan = ngaytrongtuan;
    }

    @Basic
    @Column(name = "ca")
    public Short getCa() {
        return ca;
    }

    public void setCa(Short ca) {
        this.ca = ca;
    }

    @Basic
    @Column(name = "ngaybatdau")
    public Date getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(Date ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    @Basic
    @Column(name = "tenhk")
    public String getTenhk() {
        return tenhk;
    }

    public void setTenhk(String tenhk) {
        this.tenhk = tenhk;
    }

    @Basic
    @Column(name = "namhoc")
    public Integer getNamhoc() {
        return namhoc;
    }

    public void setNamhoc(Integer namhoc) {
        this.namhoc = namhoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HocphanEntity that = (HocphanEntity) o;

        if (idhocphan != that.idhocphan) return false;
        if (mamh != null ? !mamh.equals(that.mamh) : that.mamh != null) return false;
        if (magvlt != null ? !magvlt.equals(that.magvlt) : that.magvlt != null) return false;
        if (phonghoc != null ? !phonghoc.equals(that.phonghoc) : that.phonghoc != null) return false;
        if (ngaytrongtuan != null ? !ngaytrongtuan.equals(that.ngaytrongtuan) : that.ngaytrongtuan != null)
            return false;
        if (ca != null ? !ca.equals(that.ca) : that.ca != null) return false;
        if (ngaybatdau != null ? !ngaybatdau.equals(that.ngaybatdau) : that.ngaybatdau != null) return false;
        if (tenhk != null ? !tenhk.equals(that.tenhk) : that.tenhk != null) return false;
        if (namhoc != null ? !namhoc.equals(that.namhoc) : that.namhoc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idhocphan;
        result = 31 * result + (mamh != null ? mamh.hashCode() : 0);
        result = 31 * result + (magvlt != null ? magvlt.hashCode() : 0);
        result = 31 * result + (phonghoc != null ? phonghoc.hashCode() : 0);
        result = 31 * result + (ngaytrongtuan != null ? ngaytrongtuan.hashCode() : 0);
        result = 31 * result + (ca != null ? ca.hashCode() : 0);
        result = 31 * result + (ngaybatdau != null ? ngaybatdau.hashCode() : 0);
        result = 31 * result + (tenhk != null ? tenhk.hashCode() : 0);
        result = 31 * result + (namhoc != null ? namhoc.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "mamh", referencedColumnName = "mamh", insertable = false, updatable= false)
    public MonhocEntity getMonhocByMamh() {
        return monhocByMamh;
    }

    public void setMonhocByMamh(MonhocEntity monhocByMamh) {
        this.monhocByMamh = monhocByMamh;
    }

    @ManyToOne
    @JoinColumn(name = "magvlt", referencedColumnName = "magv", insertable = false, updatable= false)
    public GiaovienEntity getGiaovienByMagvlt() {
        return giaovienByMagvlt;
    }

    public void setGiaovienByMagvlt(GiaovienEntity giaovienByMagvlt) {
        this.giaovienByMagvlt = giaovienByMagvlt;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "tenhk", referencedColumnName = "tenhk", insertable = false, updatable= false), @JoinColumn(name = "namhoc", referencedColumnName = "namhoc", insertable = false, updatable= false)})
    public HockiEntity getHocki() {
        return hocki;
    }

    public void setHocki(HockiEntity hocki) {
        this.hocki = hocki;
    }

    @OneToMany(mappedBy = "hocphanByIdhocphan")
    public Collection<SvdkhpEntity> getSvdkhpsByIdhocphan() {
        return svdkhpsByIdhocphan;
    }

    public void setSvdkhpsByIdhocphan(Collection<SvdkhpEntity> svdkhpsByIdhocphan) {
        this.svdkhpsByIdhocphan = svdkhpsByIdhocphan;
    }
}
