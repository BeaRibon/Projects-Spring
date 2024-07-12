package udemy.exer.exem1.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udemy.exer.exem1.model.dto.PersonDto;
import udemy.exer.exem1.model.exceptions.ResourceNotFoundException;
import udemy.exer.exem1.model.repositories.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    public List<PersonDto> findAll(){
        return personRepository.findAll();
    }
    public PersonDto findById(Long id){
        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum usuário encontrado"));
    }

    public PersonDto create(PersonDto personDto) {
        logger.info("Creating one person");
        return personRepository.save(personDto);
    }

    public PersonDto update(PersonDto personDto) {
        logger.info("Update one person");

        var entity = findById(personDto.getId());
        entity.setFirstName(personDto.getFirstName());
        entity.setLastName(personDto.getLastName());
        entity.setAddress(personDto.getAddress());
        entity.setGender(personDto.getGender());
        return personRepository.save(personDto);
    } public void delete(Long id) {
        logger.info("Delete one person");

        var entity = findById(id);

        personRepository.delete(entity);

    }
}
