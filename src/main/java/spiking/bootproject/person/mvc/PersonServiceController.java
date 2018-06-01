package spiking.bootproject.person.mvc;

import spiking.bootproject.person.services.PersonService;
import spiking.bootproject.person.model.Person;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

/**
 * @author cspiking
 */
@Log
@Controller
public class PersonServiceController
{
    private final PersonService personService;
    private final SimpMessageSendingOperations messagingTemplate;

    public PersonServiceController(PersonService personService, SimpMessageSendingOperations messagingTemplate)
    {
        this.personService = personService;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping(value="/getPeople")
    @ResponseBody
    public ResponseEntity<List<Person>> getPeople()
    {
        final List<Person> people = personService.getAllPeople();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @GetMapping(value="/getPerson/{id}")
    @ResponseBody
    public ResponseEntity<Person> getPersonById(@PathVariable("id") String id)
    {
        log.info("getPerson/" + id);
        final Optional<Person> person = personService.getPersonById(id);
        return person.isPresent() ? new ResponseEntity<>(person.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/addPerson")
    @ResponseBody
    public ResponseEntity<Person> addPerson(@RequestParam("forename") String forename, @RequestParam("surname") String surname)
    {
        log.info("addPerson:" + forename + "/" + surname);
        final Person person = personService.addPerson(forename, surname);
        messagingTemplate.convertAndSend("/topic/personListUpdate", "");
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping(value="/deletePerson/{id}")
    @ResponseBody
    public ResponseEntity<Person> deletePerson(@PathVariable("id") String id)
    {
        log.info("deletePerson/" + id);
        final Optional<Person> person = personService.deletePerson(id);
        messagingTemplate.convertAndSend("/topic/personListUpdate", "");
        return person.isPresent() ? new ResponseEntity<>(person.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.OK);
    }
}
