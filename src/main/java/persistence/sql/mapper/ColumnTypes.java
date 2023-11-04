package persistence.sql.mapper;

import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class ColumnTypes {
    final private Map<String, ColumnType> columnTypeMap;


    public ColumnTypes(final Class<?> clazz) {
        this.columnTypeMap = generateColumnMap(clazz);
    }


    private Map<String, ColumnType> generateColumnMap(final Class<?> clazz) {
        Map<String, ColumnType> columnMap = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();

        Arrays.stream(fields)
                .filter(field -> !field.isAnnotationPresent(Transient.class))
                .forEach(field -> {
                    ColumnType columnType;

                    if (field.isAnnotationPresent(Id.class)) {
                        columnType = new ColumnId(field);
                    } else {
                        columnType = new ColumnField(field);
                    }
                    columnMap.put(columnType.getName(), columnType);
                });

        return columnMap;
    }

    public ColumnType getColumn(String name) {
        return columnTypeMap.get(name);
    }

    public List<ColumnId> getIdColumns() {
        return columnTypeMap.values()
                .stream()
                .filter(ColumnType::isId)
                .map(columnType -> (ColumnId) columnType)
                .collect(Collectors.toList());
    }

    public List<ColumnType> getFieldColumns() {
        return columnTypeMap.values()
                .stream()
                .filter(columnType -> !columnType.isId())
                .collect(Collectors.toList());
    }

    public List<ColumnValue> getColumnValues(Object object) {
        return null;
    }

}
