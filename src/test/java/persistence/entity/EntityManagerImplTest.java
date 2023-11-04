package persistence.entity;

import jdbc.DataSetting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.sql.ddl.Person;
import test.PersonGenerator;

class EntityManagerImplTest {

    EntityManager entityManager = new EntityManagerImpl();
    Person person = PersonGenerator.getDefualtPerson();

    @BeforeEach
    void setUp() {



    }

    @Test
    void findTest() {
        DataSetting dataSetting = new DataSetting();
        dataSetting.createTable();
        dataSetting.insert(person);

        Person resultPerson = entityManager.find(Person.class, 1L);
    }
}