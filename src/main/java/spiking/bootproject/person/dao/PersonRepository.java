package spiking.bootproject.person.dao;

import spiking.bootproject.person.dao.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author cspiking
 */
@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, String>
{
    default List<PersonEntity> findBySurname(String surname) {
        return StreamSupport.stream(findAll().spliterator(), false)
                            .filter((personEntity) -> personEntity.getSurname().equals(surname))
                            .collect(Collectors.toList());
    }
}
