package com.jessal.phonebook.repository;

public interface PhoneInMemoryDataStore {

    void Save( PhoneNumberEntity phoneNumberEntity );
    PhoneNumberEntity Find( String id );


}
