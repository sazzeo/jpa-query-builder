package persistence.sql.dml;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.sql.ddl.EntityMetaData;
import persistence.sql.ddl.Person;
import test.PersonGenerator;

import static org.junit.jupiter.api.Assertions.*;

class DeleteQueryBuilderTest {

    DeleteQueryBuilder deleteQueryBuilder = new DeleteQueryBuilder();
    Person person = PersonGenerator.getDefualtPerson();
    EntityMetaData entityMetaData;

    final Long id = 1L;

    @BeforeEach
    void setUp() {
        person.setId(id);
        entityMetaData = new EntityMetaData(Person.class);
    }

    @Test
    void deleteTest() {
        String deleteQuery = deleteQueryBuilder.create(entityMetaData);
        Assertions.assertThat(deleteQuery).isEqualTo("delete from users where id = " + id);
    }
}