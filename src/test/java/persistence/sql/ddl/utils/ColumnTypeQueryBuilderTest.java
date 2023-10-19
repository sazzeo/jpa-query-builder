package persistence.sql.ddl.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.sql.ddl.EntityMetaDataExtractor;
import persistence.sql.ddl.Person;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ColumnTypeQueryBuilderTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void query() {
        EntityMetaDataExtractor entityMetaDataExtractor = new EntityMetaDataExtractor(Person.class);
        List<String> strings = ColumnQueryBuilder.generateDdlQueryRows(entityMetaDataExtractor.getColumns());
        assertThat(strings).containsOnly("email VARCHAR(255) not null", "old INTEGER", "nick_name VARCHAR(255)", "id BIGINT primary key"
        );
    }
}