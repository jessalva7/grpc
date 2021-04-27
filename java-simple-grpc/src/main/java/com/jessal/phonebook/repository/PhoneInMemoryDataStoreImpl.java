package com.jessal.phonebook.repository;

import com.jessal.phonebook.util.AlreadyExistException;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PhoneInMemoryDataStoreImpl implements PhoneInMemoryDataStore{

    ConcurrentMap<String, PhoneNumberEntity> phoneData;


    public PhoneInMemoryDataStoreImpl(){

        phoneData = new ConcurrentHashMap<>(0) ;

    }

    @Override
    public void Save(PhoneNumberEntity phoneNumberEntity) {

        if( phoneData.containsKey( phoneNumberEntity.getNumber() ) ){

            throw new AlreadyExistException("there is already a person with this id");

        }

        phoneData.put( phoneNumberEntity.getNumber(), phoneNumberEntity );

    }

    @Override
    public PhoneNumberEntity Find(String number) {

        if( !phoneData.containsKey( number ) ){

            return null;

        }

        return phoneData.get( number );

    }
}
