package com.jessal.phonebook.repository;

public interface PersonInMemoryDataStore {

    void Save( PersonEntity personEntity );
    PersonEntity Find( Integer id );


}
