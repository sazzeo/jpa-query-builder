package database.mapper;

import database.DatabaseServer;
import database.H2;
import jdbc.JdbcTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import persistence.sql.ddl.EntityMetaData;
import persistence.sql.ddl.Person;
import persistence.sql.ddl.query.DdlQueryBuilder;
import persistence.sql.dml.InsertQueryBuilder;
import persistence.sql.dml.SelectQueryBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static test.PersonGenerator.getDefualtPerson;

class ResultMapperTest {

    Person person = getDefualtPerson();

    @Test
    @DisplayName("resultMapper 테스트")
    void resultMapperTest() throws Exception {
        final DatabaseServer server = new H2();
        server.start();
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(server.getConnection());

        //데이터 생성
        Person person = getDefualtPerson();

        //테이블 생성
        DdlQueryBuilder ddlQueryBuilder = DdlQueryBuilder.getInstance();
        EntityMetaData entityMetaData = new EntityMetaData(person.getClass());
        jdbcTemplate.execute(ddlQueryBuilder.createTable(entityMetaData));

        //데이터 insert
        InsertQueryBuilder insertQueryBuilder = new InsertQueryBuilder();
        jdbcTemplate.execute(insertQueryBuilder.create(entityMetaData));
        jdbcTemplate.execute(insertQueryBuilder.create(entityMetaData));
        jdbcTemplate.execute(insertQueryBuilder.create(entityMetaData));

        SelectQueryBuilder selectQueryBuilder = new SelectQueryBuilder();
        String findAll = selectQueryBuilder.findAll(entityMetaData);

        List<Person> persons = jdbcTemplate.query(findAll, new ResultMapper<>(Person.class));

        assertThat(persons.size()).isEqualTo(3);
    }

}