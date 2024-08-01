package udemy.exer.exem1.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udemy.exer.exem1.model.dto.PersonDto;
import udemy.exer.exem1.model.entities.Person;
import udemy.exer.exem1.model.exceptions.ResourceNotFoundException;
import udemy.exer.exem1.model.mappers.DozerMapper;
import udemy.exer.exem1.model.repositories.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;


    public List<PersonDto> findAll(){
        return DozerMapper.parseListObjects(personRepository.findAll(), PersonDto.class);
    }
    public Person findById(Long id) throws Throwable {
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum usuário encontrado"));
        return entity;
    }

    public Person create(PersonDto personDto) {
        logger.info("Creating one person");
        var entity = personRepository.save(DozerMapper.parseObject(personDto, Person.class));

        return entity;
    }

    public Person update(Long id, PersonDto personDto) throws Throwable {
        logger.info("Update one person");

        var entity = findById(id);
        entity.setFirstName(personDto.getFirstName());
        entity.setLastName(personDto.getLastName());
        entity.setAddress(personDto.getAddress());
        entity.setGender(personDto.getGender());

        return personRepository.save(entity);
    }


    public void delete(Long id) throws Throwable {
        logger.info("Delete one person");

        var entity = findById(id);

        personRepository.delete(entity);

    }
}
