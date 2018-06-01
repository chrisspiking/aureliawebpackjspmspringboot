package spiking.bootproject.person.dao.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author cspiking
 */
@Entity
@Data
@ToString
public class PersonEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String forename;
    private String surname;

    public PersonEntity()
    {

    }

    public PersonEntity(String forename, String surname)
    {
        this.forename = forename;
        this.surname = surname;
    }
}
