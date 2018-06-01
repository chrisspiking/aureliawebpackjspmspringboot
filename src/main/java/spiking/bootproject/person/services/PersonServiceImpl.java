package spiking.bootproject.person.services;

import spiking.bootproject.person.dao.PersonRepository;
import spiking.bootproject.person.dao.entity.PersonEntity;
import spiking.bootproject.person.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author cspiking
 */
public class PersonServiceImpl implements PersonService
{
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository)
    {
        this.personRepository = personRepository;
    }

    @Override
    public Person addPerson(String forename, String surname)
    {
        final PersonEntity newEntity = personRepository.save(new PersonEntity(forename, surname));
        return Person.fromPersonEntity(newEntity);
    }

    @Override
    public Optional<Person> deletePerson(String id)
    {
        final Optional<PersonEntity> existingPerson = personRepository.findById(id);
        personRepository.deleteById(id);
        return existingPerson.map(Person::fromPersonEntity);
    }

    @Override
    public Optional<Person> getPersonById(String id)
    {
        final Optional<PersonEntity> foundPerson = personRepository.findById(id);
        return foundPerson.map(Person::fromPersonEntity);
    }

    @Override
    public List<Person> getAllPeople()
    {
        final Stream<PersonEntity> stream = StreamSupport.stream(personRepository.findAll().spliterator(), false);
        return stream.map(Person::fromPersonEntity).collect(Collectors.toList());
    }
}
