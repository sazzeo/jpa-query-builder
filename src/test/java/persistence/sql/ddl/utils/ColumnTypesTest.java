package persistence.sql.ddl.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import persistence.sql.ddl.Person;
import persistence.sql.ddl.type.DataType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


class ColumnTypesTest {

    @Test
    @DisplayName("column들의 이름을 가져온다.")
    void getColumnsName() {
        //Given
        ColumnTypes columnTypes = new ColumnTypes(Person.class);

        //When
        String id = columnTypes.getColumn("id").getName();
        String name = columnTypes.getColumn("name").getName();
        String age = columnTypes.getColumn("age").getName();

        //Then
        assertAll(() -> assertThat(id).isEqualTo("id"),
                () -> assertThat(name).isEqualTo("name"),
                () -> assertThat(age).isEqualTo("age"));
    }

    @Test
    @DisplayName("@Id 컬럼만 가져온다")
    void getIdColumn() {
        //Given
        ColumnTypes columnTypes = new ColumnTypes(Person.class);
        ColumnTypes idColumnTypes = columnTypes.getIdColumns();

        //When
        ColumnType id = idColumnTypes.getColumn("id");
        ColumnType name = idColumnTypes.getColumn("name");
        ColumnType age = idColumnTypes.getColumn("age");

        //Then
        assertAll(() -> assertThat(id.getName()).isEqualTo("id"),
                () -> assertThat(name).isNull(),
                () -> assertThat(age).isNull());
    }

    @Test
    @DisplayName("컬럼 타입별 DB 타입을 가져온다")
    void getColumnType() {
        //Given
        ColumnTypes columnTypes = new ColumnTypes(Person.class);

        //When
        DataType id = columnTypes.getColumn("id").getDataType();
        DataType name = columnTypes.getColumn("name").getDataType();
        DataType age = columnTypes.getColumn("age").getDataType();

        //Then
        assertAll(() -> assertThat(id.getName()).isEqualTo("BIGINT"),
                () -> assertThat(name.getName()).isEqualTo("VARCHAR"),
                () -> assertThat(age.getName()).isEqualTo("INTEGER"));
    }

    @Test
    @DisplayName("컬럼의 length 값을 가져온다")
    void getColumnLength() {
        //Given
        ColumnTypes columnTypes = new ColumnTypes(Person.class);

        //When
        Integer id = columnTypes.getColumn("id").getLength();
        Integer name = columnTypes.getColumn("nick_name").getLength();
        Integer age = columnTypes.getColumn("old").getLength();

        //Then
        assertAll(() -> assertThat(id).isNull(),
                () -> assertThat(name).isEqualTo(255),
                () -> assertThat(age).isNull());
    }


}