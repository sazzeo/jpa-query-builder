package persistence.sql.dml;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import persistence.sql.ddl.EntityMetaData;
import persistence.sql.ddl.Person;
import test.PersonGenerator;

import static org.assertj.core.api.Assertions.assertThat;

class SelectQueryBuilderTest {

    SelectQueryBuilder selectQueryBuilder = new SelectQueryBuilder();
    Person person = PersonGenerator.getDefualtPerson();

    EntityMetaData entityMetaData ;
    @BeforeEach
    void setUp() {
        entityMetaData = new EntityMetaData(Person.class);
    }

    @Test
    @DisplayName("findAll 쿼리 테스트")
    void findAllTest() {
        String findAll = selectQueryBuilder.findAll(entityMetaData);
        assertThat(findAll).isEqualTo("select * from users");
    }

    @Test
    @DisplayName("findById 쿼리 테스트")
    void findByIdTest() {
        EntityMetaData entityMetaData = new EntityMetaData(Person.class);
        String findById = selectQueryBuilder.findById(entityMetaData , 2L);
        System.out.println(findById);
        assertThat(findById).isEqualTo("select * from users where id = 2");
    }
}