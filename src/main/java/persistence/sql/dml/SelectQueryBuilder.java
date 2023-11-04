package persistence.sql.dml;

import persistence.sql.ddl.EntityMetaData;
import persistence.sql.mapper.ColumnId;

import java.util.stream.Collectors;

public class SelectQueryBuilder {
    private static final String SELECT_QUERY_FORMAT = "select %s from %s";
    private static final String WHERE_CLAUSE_FORMAT = "%s = %s";


    public String findAll(final EntityMetaData entityMetaData) {
        return String.format(SELECT_QUERY_FORMAT, "*", entityMetaData.getTableName());
    }

    public String findById(final EntityMetaData entityMetaData, final Object id) {
        return findAll(entityMetaData) + whereClause(entityMetaData, id);
    }

    private String whereClause(EntityMetaData entityMetaData, final Object id) {
        String idWhereClause = entityMetaData.getIdColumns().stream()
                .map(columnId -> String.format(WHERE_CLAUSE_FORMAT, columnId.getName(), id))
                .collect(Collectors.joining(" AND "));
        return " where " + idWhereClause;
    }

}
