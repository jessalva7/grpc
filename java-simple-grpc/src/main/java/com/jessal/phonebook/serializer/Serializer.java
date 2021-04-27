package com.jessal.phonebook.serializer;

import com.google.protobuf.util.JsonFormat;
import com.jessal.phonebook.pb.Person;
import com.jessal.phonebook.sample.Generator;

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

    void writeToFilePersonJson(Person person, String filename ) throws IOException {

        JsonFormat.Printer printer = JsonFormat.printer().includingDefaultValueFields().preservingProtoFieldNames();

        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        fileOutputStream.write( printer.print( person ).getBytes() );
        fileOutputStream.close();

    }

}
