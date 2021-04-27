package com.jessal.phonebook.repository;

import com.jessal.phonebook.util.AlreadyExistException;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PersonInMemoryDataStoreImpl implements PersonInMemoryDataStore{

    ConcurrentMap<Integer, PersonEntity> personData;


    public PersonInMemoryDataStoreImpl(){

        personData = new ConcurrentHashMap<>(0) ;

    }

    @Override
    public void Save(PersonEntity personEntity) {

        if( personData.containsKey( personEntity.getId() ) ){

            throw new AlreadyExistException("there is already a person with this id");

        }

        personData.put( personEntity.getId(), personEntity );

    }

    @Override
    public PersonEntity Find(Integer id) {

        if( !personData.containsKey( id ) ){

            return null;

        }

        return personData.get( id );

    }
}
