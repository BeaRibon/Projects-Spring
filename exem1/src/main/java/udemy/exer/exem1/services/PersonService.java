package udemy.exer.exem1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import udemy.exer.exem1.controllers.PersonController;
import udemy.exer.exem1.model.mappers.custom.PersonMapper;
import udemy.exer.exem1.model.v1.dto.PersonDtoV1;
import udemy.exer.exem1.exceptions.ResourceNotFoundException;
import udemy.exer.exem1.model.mappers.DozerMapper;
import udemy.exer.exem1.repositories.PersonRepository;
import udemy.exer.exem1.model.v1.entities.PersonV1;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonMapper personMapper;


    public List<PersonDtoV1> findAll(){
        var persons = DozerMapper.parseListObjects(personRepository.findAll(), PersonDtoV1.class);

        persons.stream()
                .forEach( p -> {
                    try {
                        p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                });

        return persons;
    }
    public PersonDtoV1 findById(Long key) throws Throwable {
        var entity = personRepository.findById(key)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum usuário encontrado"));
        PersonDtoV1 dto = DozerMapper.parseObject(entity, PersonDtoV1.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(key)).withSelfRel());
        return dto;
    }

    public PersonDtoV1 create(PersonDtoV1 personDto) throws Exception {
        logger.info("Creating one person");
        var entity = personRepository.save(DozerMapper.parseObject(personDto, PersonV1.class));
        PersonDtoV1 dto = DozerMapper.parseObject(entity, PersonDtoV1.class);

        dto.add(linkTo(methodOn(PersonController.class).findByAll(dto.getKey())).withSelfRel());

        return dto;
    }

    public PersonDtoV1 update(Long key, PersonDtoV1 personDto) throws Throwable {
        logger.info("Update one person");

        var findById = findById(key);
        PersonV1 entity = DozerMapper.parseObject(findById, PersonV1.class);
        entity.setFirstName(personDto.getFirstName());
        entity.setLastName(personDto.getLastName());
        entity.setAddress(personDto.getAddress());
        entity.setGender(personDto.getGender());

        var entidade = personRepository.save(DozerMapper.parseObject(entity, PersonV1.class));
        PersonDtoV1 dto = DozerMapper.parseObject(entity, PersonDtoV1.class);

        dto.add(linkTo(methodOn(PersonController.class).findByAll(dto.getKey())).withSelfRel());

        return dto;
       // return personRepository.save(entity);
    }

    public void delete(Long key) throws Throwable {
        logger.info("Delete one person");

        var findById = findById(key);
        PersonV1 entity = DozerMapper.parseObject(findById, PersonV1.class);

        personRepository.delete(entity);

    }

//    public PersonV2 createV2(PersonDtoV2 personDto) {
//        logger.info("creating one person");
//        var entity = DozerMapper.parseObject(personDto, PersonV2.class);
//        var entity2 = personMapper.convertVOtoEntity(entity);
//        var vo = personMapper.convertEntityToVO(personRepository.save(entity2));
//
//        return vo;
//    }
}
