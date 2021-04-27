package com.jessal.phonebook.service;

import com.jessal.phonebook.pb.AddPersonRequest;
import com.jessal.phonebook.pb.AddPersonResponse;
import com.jessal.phonebook.pb.Person;
import com.jessal.phonebook.pb.PersonServiceGrpc;
import com.jessal.phonebook.repository.PersonEntity;
import com.jessal.phonebook.repository.PersonInMemoryDataStore;
import com.jessal.phonebook.repository.PhoneInMemoryDataStore;
import com.jessal.phonebook.repository.PhoneNumberEntity;
import com.jessal.phonebook.util.AlreadyExistException;
import com.jessal.phonebook.util.Mapper;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

public class PersonService extends PersonServiceGrpc.PersonServiceImplBase{

    private static final Logger logger = Logger.getLogger( PersonService.class.getName() );

    public PhoneInMemoryDataStore phoneInMemoryDataStore;
    public PersonInMemoryDataStore personInMemoryDataStore;
    private Mapper mapper;
    private Random random;

    public PersonService( Random random, PhoneInMemoryDataStore phoneInMemoryDataStore,  PersonInMemoryDataStore personInMemoryDataStore, Mapper mapper){

        this.random = random;
        this.personInMemoryDataStore = personInMemoryDataStore;
        this.phoneInMemoryDataStore = phoneInMemoryDataStore;
        this.mapper = mapper;

    }

    @Override
    public void addPerson(AddPersonRequest request, StreamObserver<AddPersonResponse> responseObserver) {

        Person person = request.getPerson();

        int id = person.getId();
        if( id != 0  ){
            responseObserver.onError(Status
                    .INVALID_ARGUMENT
                    .withDescription("Id should be null")
                    .asException());
        }

        person = person.toBuilder().setId( random.nextInt( (int)1e5 ) ).build();

        PersonEntity personEntity = mapper.fromPerson(person);
        personEntity.setPhonesNumbers( new ArrayList<>() );

        logger.info("got person addition with id :"+ id);

        try{

            for (Person.PhoneNumber phoneNumber: person.getPhonesList() ) {

                if( phoneInMemoryDataStore.Find( phoneNumber.getNumber()) != null ){
                    continue;
                }

                logger.info("saving phone number :"+ phoneNumber);
                phoneInMemoryDataStore.Save( mapper.fromPhone(phoneNumber) );
                personEntity.getPhonesNumbers().add( phoneNumber.getNumber() );

            }
            personInMemoryDataStore.Save( personEntity );

        } catch ( AlreadyExistException alreadyExistException){

            responseObserver.onError( Status.ALREADY_EXISTS.asException() );

        } catch ( Exception e ){

            responseObserver.onError( Status.INTERNAL.asException() );

        }

        AddPersonResponse addPersonResponse = AddPersonResponse.newBuilder().setPerson( person ).build();
        responseObserver.onNext( addPersonResponse );
        responseObserver.onCompleted();
        logger.info("Person is saved");

    }
}
