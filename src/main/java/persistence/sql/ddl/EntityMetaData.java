package persistence.sql.ddl;

import persistence.sql.mapper.*;

import java.util.List;

public class EntityMetaData {
    final private TableType tableType;
    final private ColumnTypes columnTypes;

    public EntityMetaData(final Class<?> clazz) {
        this.tableType = new TableType(clazz);
        this.columnTypes = new ColumnTypes(clazz);
    }

    public List<ColumnId> getIdColumns() {
        return columnTypes.getIdColumns();
    }


    public List<ColumnType> getFieldColumns() {
        return columnTypes.getFieldColumns();
    }

    public List<ColumnValue> getColumnValues(Object object) {
        return columnTypes.getColumnValues(object);
    }

    public String getTableName() {
        return this.tableType.getName();
    }
}