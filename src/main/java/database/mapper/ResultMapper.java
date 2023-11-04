package database.mapper;

import jdbc.RowMapper;
import utils.EntityAnnotationUtils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

public class ResultMapper<T> implements RowMapper<T> {

    private final Class<T> entityClass;

    public ResultMapper(final Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T mapRow(final ResultSet resultSet) {
        try {
            T entity = entityClass.getConstructor().newInstance();
            EntityAnnotationUtils.getNonTransientData(entityClass.getDeclaredFields()).forEach(field -> {
                try {
                    field.setAccessible(true);
                    String columnName = EntityAnnotationUtils.parseColumnName(field);
                    Object object = resultSet.getObject(columnName);
                    field.set(entity, object);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            return entity;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
