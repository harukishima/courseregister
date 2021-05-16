package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class HockiEntityPK implements Serializable {
    private String tenhk;
    private int namhoc;

    @Column(name = "tenhk")
    @Id
    public String getTenhk() {
        return tenhk;
    }

    public void setTenhk(String tenhk) {
        this.tenhk = tenhk;
    }

    @Column(name = "namhoc")
    @Id
    public int getNamhoc() {
        return namhoc;
    }

    public void setNamhoc(int namhoc) {
        this.namhoc = namhoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HockiEntityPK that = (HockiEntityPK) o;

        if (namhoc != that.namhoc) return false;
        if (tenhk != null ? !tenhk.equals(that.tenhk) : that.tenhk != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tenhk != null ? tenhk.hashCode() : 0;
        result = 31 * result + namhoc;
        return result;
    }
}
