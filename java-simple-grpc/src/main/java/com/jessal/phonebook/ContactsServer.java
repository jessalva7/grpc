package com.jessal.phonebook;

import com.jessal.phonebook.repository.PersonInMemoryDataStore;
import com.jessal.phonebook.repository.PersonInMemoryDataStoreImpl;
import com.jessal.phonebook.repository.PhoneInMemoryDataStore;
import com.jessal.phonebook.repository.PhoneInMemoryDataStoreImpl;
import com.jessal.phonebook.service.PersonService;
import com.jessal.phonebook.util.Mapper;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ContactsServer {
    private static final Logger logger = Logger.getLogger( ContactsServer.class.getName() );

    private Server server;
    private int port;

    public ContactsServer(int port, Random random, PhoneInMemoryDataStore phoneInMemoryDataStore, PersonInMemoryDataStore personInMemoryDataStore, Mapper mapper  ) {
        this( ServerBuilder.forPort( port ), port,  random,  phoneInMemoryDataStore,   personInMemoryDataStore,  mapper );
    }

    public ContactsServer(ServerBuilder serverBuilder, int port, Random random, PhoneInMemoryDataStore phoneInMemoryDataStore,  PersonInMemoryDataStore personInMemoryDataStore, Mapper mapper) {

        this.port = port;
        PersonService personService = new PersonService(   random,  phoneInMemoryDataStore,   personInMemoryDataStore,  mapper );
        this.server = serverBuilder
                .addService( personService )
                .addService(ProtoReflectionService.newInstance())
                .build();

    }

    public void start() throws IOException {

        server.start();
        logger.info("Started service at port:" + port);

        Runtime.getRuntime().addShutdownHook( new Thread(){

            @Override
            public void run() {

                try {
                    ContactsServer.this.stop();
                } catch ( InterruptedException ie){

                    ie.printStackTrace();

                }
                logger.info("Stopped service at port:" + port);

            }

        } );

    }

    public void stop() throws InterruptedException{

        if( server != null ) {
            server.shutdown().awaitTermination( 30, TimeUnit.SECONDS);
        }

    }

    public void blockUntilShutdown() throws InterruptedException {

        if( server != null ){

            server.awaitTermination();

        }

    }

    public static void main(String[] args) throws InterruptedException, IOException {

        ContactsServer contactsServer = new ContactsServer( 8080, new Random(), new PhoneInMemoryDataStoreImpl(), new PersonInMemoryDataStoreImpl(), new Mapper());

        contactsServer.start();
        contactsServer.blockUntilShutdown();

    }

}
