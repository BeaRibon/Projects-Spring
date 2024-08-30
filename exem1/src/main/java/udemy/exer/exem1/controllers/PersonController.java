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
    @GetMapping(value = "/{id}",
    produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML} )
    public ResponseEntity<PersonDtoV1> findById(@PathVariable(value = "id")Long id) throws Throwable {
     PersonV1 person = personService.findById(id);
        return ResponseEntity.ok(DozerMapper.parseObject(person, PersonDtoV1.class));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public List<PersonDtoV1> findByAll() throws Exception {
        return personService.findAll();
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<PersonDtoV1> create(@RequestBody PersonDtoV1 personDto)throws Exception {
        PersonV1 person = personService.create(personDto);
        return ResponseEntity.ok(DozerMapper.parseObject(person, PersonDtoV1.class));
    }
    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<PersonDtoV1> update(@RequestBody PersonDtoV1 personDto) throws Throwable {
        PersonV1 person = personService.update(personDto.getId(), personDto);
        return ResponseEntity.ok(DozerMapper.parseObject(person, PersonDtoV1.class));

    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id) throws Throwable {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}