package ru.job4j.search;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса PhoneDictionary.
 * @author Alexey Makarov
 * @since 09.08.18
 * @version 0.1
 */
public class PhoneDictionaryTest {

    /**
     * Поиск по имени полному.
     */
    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        List<Person> persons = phones.find("Petr");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }

    /**
     * Поиск по части имени.
     */
    @Test
    public void whenFindByPartName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        List<Person> persons = phones.find("Pe");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }

    /**
     * Поиск по части фамилии.
     */
    @Test
    public void whenFindByPartSurname() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        List<Person> persons = phones.find("Arsent");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }

    /**
     * Поиск по части телефона.
     */
    @Test
    public void whenFindByPartPhone() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        List<Person> persons = phones.find("534");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }

    /**
     * Поиск по части адреса.
     */
    @Test
    public void whenFindByPartAdress() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        List<Person> persons = phones.find("Bryan");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }

    /**
     * Поиск по части адреса.
     */
    @Test
    public void whenMakeInvalidSearchThenEmptyList() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        List<Person> persons = phones.find("Bryand");
        assertThat(persons.iterator().hasNext(), is(false));
    }


}