package test;

import persistence.sql.ddl.Person;

public class PersonGenerator {

    public static final int AGE = 28;
    public static final String NAME = "지영";
    public static final String EMAIL = "jy@lim.com";
    public static Person getDefualtPerson() {
        Person person = new Person();
        person.setAge(AGE);
        person.setName(NAME);
        person.setEmail(EMAIL);

        return person;
    }
}
