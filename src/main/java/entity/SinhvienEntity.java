package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "sinhvien", schema = "public", catalog = "coursedb")
public class SinhvienEntity {
    private int masv;
    private String fullname;
    private String pass;
    private String gioitinh;
    private Short namnhaphoc;
    private Integer malop;
    private LopEntity lopByMalop;
    private Collection<SvdkhpEntity> svdkhpsByMasv;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "masv")
    public int getMasv() {
        return masv;
    }

    public void setMasv(int masv) {
        this.masv = masv;
    }

    @Basic
    @Column(name = "fullname")
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Basic
    @Column(name = "pass")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Basic
    @Column(name = "gioitinh")
    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    @Basic
    @Column(name = "namnhaphoc")
    public Short getNamnhaphoc() {
        return namnhaphoc;
    }

    public void setNamnhaphoc(Short namnhaphoc) {
        this.namnhaphoc = namnhaphoc;
    }

    @Basic
    @Column(name = "malop")
    public Integer getMalop() {
        return malop;
    }

    public void setMalop(Integer malop) {
        this.malop = malop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SinhvienEntity that = (SinhvienEntity) o;

        if (masv != that.masv) return false;
        if (fullname != null ? !fullname.equals(that.fullname) : that.fullname != null) return false;
        if (pass != null ? !pass.equals(that.pass) : that.pass != null) return false;
        if (gioitinh != null ? !gioitinh.equals(that.gioitinh) : that.gioitinh != null) return false;
        if (namnhaphoc != null ? !namnhaphoc.equals(that.namnhaphoc) : that.namnhaphoc != null) return false;
        if (malop != null ? !malop.equals(that.malop) : that.malop != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = masv;
        result = 31 * result + (fullname != null ? fullname.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        result = 31 * result + (gioitinh != null ? gioitinh.hashCode() : 0);
        result = 31 * result + (namnhaphoc != null ? namnhaphoc.hashCode() : 0);
        result = 31 * result + (malop != null ? malop.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "malop", referencedColumnName = "malop", insertable = false, updatable= false)
    public LopEntity getLopByMalop() {
        return lopByMalop;
    }

    public void setLopByMalop(LopEntity lopByMalop) {
        this.lopByMalop = lopByMalop;
    }

    @OneToMany(mappedBy = "sinhvienByMasv")
    public Collection<SvdkhpEntity> getSvdkhpsByMasv() {
        return svdkhpsByMasv;
    }

    public void setSvdkhpsByMasv(Collection<SvdkhpEntity> svdkhpsByMasv) {
        this.svdkhpsByMasv = svdkhpsByMasv;
    }
}
