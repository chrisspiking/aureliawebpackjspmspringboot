package spiking.bootproject.person;

import spiking.bootproject.person.dao.PersonRepository;
import spiking.bootproject.person.services.PersonService;
import spiking.bootproject.person.services.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

/**
 * @author cspiking
 */
@Configuration
public class ApplicationConfiguration
{
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Bean
    public PersonService getPersonService()
    {
        return new PersonServiceImpl(personRepository);
    }

}
