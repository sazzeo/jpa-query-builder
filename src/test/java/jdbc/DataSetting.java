package jdbc;

import database.DatabaseServer;
import database.H2;
import persistence.sql.ddl.EntityMetaData;
import persistence.sql.ddl.Person;
import persistence.sql.ddl.query.DdlQueryBuilder;
import persistence.sql.dml.InsertQueryBuilder;

public class DataSetting {

    private final JdbcTemplate jdbcTemplate;
    private final DatabaseServer server;


    public DataSetting() {
        try {
            this.server = new H2();
            this.server.start();
            this.jdbcTemplate = new JdbcTemplate(server.getConnection());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable() {
        //테이블 생성
        DdlQueryBuilder ddlQueryBuilder = DdlQueryBuilder.getInstance();
        EntityMetaData entityMetaData = new EntityMetaData(Person.class);
        jdbcTemplate.execute(ddlQueryBuilder.createTable(entityMetaData));
    }

    public void insert(Person person) {
        InsertQueryBuilder insertQueryBuilder = new InsertQueryBuilder();
        EntityMetaData entityMetaData = new EntityMetaData(Person.class);
        jdbcTemplate.execute(insertQueryBuilder.create(entityMetaData));
    }

}
