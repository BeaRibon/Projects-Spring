package udemy.exer.exem1.model.mappers.custom;

import org.springframework.stereotype.Service;
import udemy.exer.exem1.model.v1.entities.PersonV1;
import udemy.exer.exem1.model.v2.entities.PersonV2;

import java.util.Date;

@Service
public class PersonMapper {

//    public PersonV2 convertEntityToVO(PersonV1 person){
//        PersonV2 personV2 = new PersonV2();
//
//        personV2.setKey(person.getKey());
//        personV2.setFirstName(person.getFirstName());
//        personV2.setLastName(person.getLastName());
//        personV2.setAddress(person.getAddress());
//        personV2.setGender(person.getGender());
//        personV2.setBirthDay(new Date());
//
//        return personV2;
//    }

    public PersonV1 convertVOtoEntity(PersonV2 person){
        PersonV1 personV1 = new PersonV1();

        personV1.setKey(person.getId());
        personV1.setFirstName(person.getFirstName());
        personV1.setLastName(person.getLastName());
        personV1.setAddress(person.getAddress());
        personV1.setGender(person.getGender());

        return personV1;
    }
}
