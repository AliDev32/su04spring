package ali.su.cft2j02.datasaver;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "testtable")
public class TestTable {
    @Id
    private long id;
    private String cVarchar;
    private long cNumeric;

    @Override
    public String toString() {
        return "TestTable{" +
                "id=" + id +
                ", cVarchar='" + cVarchar + '\'' +
                ", cNumeric=" + cNumeric +
                '}';
    }
}
