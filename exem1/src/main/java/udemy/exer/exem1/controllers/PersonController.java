package udemy.exer.exem1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udemy.exer.exem1.model.dto.PersonDto;
import udemy.exer.exem1.model.entities.Person;
import udemy.exer.exem1.model.mappers.DozerMapper;
import udemy.exer.exem1.model.services.PersonService;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;
    @GetMapping(value = "/{id}",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> findById(@PathVariable(value = "id")Long id) throws Throwable {
        Person person = personService.findById(id);
        return ResponseEntity.ok(DozerMapper.parseObject(person, PersonDto.class));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDto> findByAll()throws Exception {
        return personService.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> create(@RequestBody PersonDto personDto)throws Exception {
        Person person = personService.create(personDto);
        return ResponseEntity.ok(DozerMapper.parseObject(person, PersonDto.class));
    }
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> update(@RequestBody PersonDto personDto) throws Throwable {
        Person person = personService.update(personDto.getId(), personDto);
        return ResponseEntity.ok(DozerMapper.parseObject(person, PersonDto.class));

    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id) throws Throwable {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}