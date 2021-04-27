package com.jessal.phonebook.util;

import com.jessal.phonebook.pb.Person;
import com.jessal.phonebook.repository.PersonEntity;
import com.jessal.phonebook.repository.PhoneNumberEntity;
import com.jessal.phonebook.repository.PhoneType;

public class Mapper {

    public PersonEntity fromPerson(Person person){

        PersonEntity personEntity = new PersonEntity();
        personEntity.setId( person.getId() );
        personEntity.setName( personEntity.getName() );
        personEntity.setEmail(person.getEmail() );
        return personEntity;
    }

    public PhoneNumberEntity fromPhone(Person.PhoneNumber phoneNumber){

        PhoneNumberEntity phoneNumberEntity = new PhoneNumberEntity();
        phoneNumberEntity.setNumber( phoneNumber.getNumber() );
        phoneNumberEntity.setType( PhoneType.valueOf(phoneNumber.getType().toString()) );
        return phoneNumberEntity;

    }
}
