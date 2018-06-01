package spiking.bootproject.person.model;

import spiking.bootproject.person.dao.entity.PersonEntity;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author cspiking
 */
@Data
@Builder
@ToString
public class Person
{
    private final String id;
    private final String forename;
    private final String surname;

    public static Person fromPersonEntity(PersonEntity personEntity)
    {
        return Person.builder().id(personEntity.getId()).forename(personEntity.getForename()).surname(personEntity.getSurname()).build();
    }
}
