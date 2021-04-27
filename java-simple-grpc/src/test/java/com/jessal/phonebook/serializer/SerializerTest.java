package com.jessal.phonebook.serializer;

import com.jessal.phonebook.pb.Person;
import com.jessal.phonebook.sample.Generator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SerializerTest {

    @Test
    void writeToFilePerson() {

        String laptopBin = this.getClass().getResource("/").getPath() + "person.bin";
        Person person = new Generator().NewPerson();
        try {
            new Serializer().writeToFilePerson( person, laptopBin );
        } catch (IOException e) {
            fail();
        }

    }

    @Test
    void readFromFilePerson() {

        String personBin = this.getClass().getResource("/").getPath() + "person.bin";
        try {
            Person person = new Serializer().readFromFilePerson(personBin);
            Assert.assertEquals(person.getName(),"Jessal");
            Assert.assertEquals(person.getEmail(),"subscribe.jessal.va@gmail.com");
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void writeToFilePersonJson() {


        String laptopBin = this.getClass().getResource("/").getPath() + "simplePerson.json";
        Person person = new Generator().NewPerson();
        try {
            new Serializer().writeToFilePersonJson( person, laptopBin );
        } catch (IOException e) {
            fail();
        }

    }
}