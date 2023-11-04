package persistence.sql.dml;

import persistence.sql.ddl.EntityMetaData;

import java.util.stream.Collectors;

public class DeleteQueryBuilder {

    private static final String DELETE_QUERY_FORMAT = "delete from %s";
    private static final String WHERE_CLAUSE_FORMAT = "%s = %s";


    public String create(EntityMetaData entityMetaData) {
        return deleteAll(entityMetaData) + whereClause(entityMetaData);
    }

    public String deleteAll(EntityMetaData entityMetaData) {
        return String.format(DELETE_QUERY_FORMAT , entityMetaData.getTableName());
    }

    private String whereClause(EntityMetaData entityMetaData) {
        String idWhereClause = entityMetaData.getIdColumns().stream()
                .map(columnId -> String.format(WHERE_CLAUSE_FORMAT, columnId.getName(), columnId.getValue()))
                .collect(Collectors.joining(" AND "));
        return " where " + idWhereClause;
    }

}
