package com.jessal.phonebook.serializer;

import com.jessal.phonebook.pb.Person;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Serializer {

    public void writeToFilePerson(Person person, String filename ) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        fileOutputStream.write( person.toByteArray() );
        fileOutputStream.close();

    }


    public Person readFromFilePerson( String filename ) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(filename);
        Person person = Person.parseFrom(fileInputStream.readAllBytes());
        fileInputStream.close();
        return  person;

    }

}
