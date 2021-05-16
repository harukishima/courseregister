package entity;

import javax.persistence.*;

@Entity
@Table(name = "giaovu", schema = "public", catalog = "coursedb")
public class GiaovuEntity {
    private int magv;
    private String fullname;
    private String pass;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "magv")
    public int getMagv() {
        return magv;
    }

    public void setMagv(int magv) {
        this.magv = magv;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GiaovuEntity that = (GiaovuEntity) o;

        if (magv != that.magv) return false;
        if (fullname != null ? !fullname.equals(that.fullname) : that.fullname != null) return false;
        if (pass != null ? !pass.equals(that.pass) : that.pass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = magv;
        result = 31 * result + (fullname != null ? fullname.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        return result;
    }
}
