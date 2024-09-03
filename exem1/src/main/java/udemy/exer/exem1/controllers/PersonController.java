package udemy.exer.exem1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import udemy.exer.exem1.util.MediaType;
import org.springframework.web.bind.annotation.*;
import udemy.exer.exem1.model.v1.dto.PersonDtoV1;
import udemy.exer.exem1.model.mappers.DozerMapper;
import udemy.exer.exem1.services.PersonService;
import udemy.exer.exem1.model.v1.entities.PersonV1;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
    @Autowired
    private PersonService personService;
    @GetMapping(value = "/{key}",
    produces = {MediaType.APPLICATION_JSON} )
    public ResponseEntity<PersonDtoV1> findById(@PathVariable(value = "key")Long key) throws Throwable {
     PersonDtoV1 person = personService.findById(key);
        return ResponseEntity.ok(person);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON})
    public List<PersonDtoV1> findByAll(Long key) throws Exception {
        return personService.findAll();
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<PersonDtoV1> create(@RequestBody PersonDtoV1 personDto)throws Exception {
        PersonDtoV1 person = personService.create(personDto);
        return ResponseEntity.ok(person);
    }
    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            value = "/{key}")
    public ResponseEntity<PersonDtoV1> update(@RequestBody PersonDtoV1 personDto) throws Throwable {
        PersonDtoV1 person = personService.update(personDto.getKey(), personDto);
        return ResponseEntity.ok(person);

    }
    @DeleteMapping(value = "/{key}")
    public ResponseEntity<?> delete(@PathVariable(value = "key")Long key) throws Throwable {
        personService.delete(key);
        return ResponseEntity.noContent().build();
    }
}