package persistence.entity;

import persistence.sql.ddl.EntityMetaData;
import persistence.sql.dml.SelectQueryBuilder;

public class EntityManagerImpl implements EntityManager {

    @Override
    public <T> T find(Class<T> clazz, Long id) {
        SelectQueryBuilder selectQueryBuilder = new SelectQueryBuilder();
//        selectQueryBuilder.findById(clazz, id);
        return null;
    }

    @Override
    public Object persist(Object entity) {
        return null;
    }

    @Override
    public void remove(Object entity) {

    }
}
