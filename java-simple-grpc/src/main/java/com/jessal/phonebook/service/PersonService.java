package com.jessal.phonebook.service;

import com.jessal.phonebook.pb.AddPersonRequest;
import com.jessal.phonebook.pb.AddPersonResponse;
import com.jessal.phonebook.pb.PersonServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

public class PersonService extends PersonServiceGrpc.PersonServiceImplBase{

    private static final Logger logger = Logger.getLogger( PersonService.class.getName() );

    @Override
    public void addPerson(AddPersonResponse request, StreamObserver<AddPersonRequest> responseObserver) {
        super.addPerson(request, responseObserver);
    }
}
