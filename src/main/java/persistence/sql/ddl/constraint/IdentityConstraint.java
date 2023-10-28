package persistence.sql.ddl.constraint;

import jakarta.persistence.GenerationType;
import persistence.sql.mapper.ColumnId;
import persistence.sql.mapper.ColumnType;

public class IdentityConstraint implements Constraint {

    private GenerationType generationType;

    @Override
    public boolean check(final ColumnType columnType) {
        if (!columnType.isId()) {
            return false;
        }
        this.generationType = ((ColumnId) columnType).getGenerationType();
        return true;
    }

    @Override
    public String generate() {
        if (this.generationType == GenerationType.IDENTITY) {
            return "auto_increment";
        }
        return "";
    }
}
