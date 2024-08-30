package udemy.exer.exem1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udemy.exer.exem1.model.mappers.custom.PersonMapper;
import udemy.exer.exem1.model.v1.dto.PersonDtoV1;
import udemy.exer.exem1.exceptions.ResourceNotFoundException;
import udemy.exer.exem1.model.mappers.DozerMapper;
import udemy.exer.exem1.repositories.PersonRepository;
import udemy.exer.exem1.model.v1.entities.PersonV1;
import udemy.exer.exem1.model.v2.dto.PersonDtoV2;
import udemy.exer.exem1.model.v2.entities.PersonV2;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonMapper personMapper;


    public List<PersonDtoV1> findAll(){
        return DozerMapper.parseListObjects(personRepository.findAll(), PersonDtoV1.class);
    }
    public PersonV1 findById(Long id) throws Throwable {
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum usuário encontrado"));
        return entity;
    }

    public PersonV1 create(PersonDtoV1 personDto) {
        logger.info("Creating one person");
        var entity = personRepository.save(DozerMapper.parseObject(personDto, PersonV1.class));

        return entity;
    }

    public PersonV1 update(Long id, PersonDtoV1 personDto) throws Throwable {
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

    public PersonV2 createV2(PersonDtoV2 personDto) {
        logger.info("creating one person");
        var entity = DozerMapper.parseObject(personDto, PersonV2.class);
        var entity2 = personMapper.convertVOtoEntity(entity);
        var vo = personMapper.convertEntityToVO(personRepository.save(entity2));

        return vo;
    }
}
