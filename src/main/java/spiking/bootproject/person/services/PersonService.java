package spiking.bootproject.person.services;

import spiking.bootproject.person.model.Person;

import java.util.List;
import java.util.Optional;

/**
 * @author cspiking
 */
public interface PersonService
{
    Optional<Person> deletePerson(String id);

    List<Person> getAllPeople();

    Optional<Person> getPersonById(String id);

    Person addPerson(String forename, String surname);
}
