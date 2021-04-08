package com.jessal.phonebook.sample;

import com.jessal.phonebook.pb.Person;
import java.util.Random;

public class Generator {

    private Random random = new Random();

    public Person NewPerson(){

        return Person.newBuilder()
                .setId( random.nextInt( (int)1e5 ) )
                .setName("Jessal")
                .setEmail("subscribe.jessal.va@gmail.com")
                .addPhones(NewPhoneNumber( Person.PhoneType.MOBILE ))
                .addPhones(NewPhoneNumber( Person.PhoneType.HOME ))
                .build();

    }

    public Person.PhoneNumber NewPhoneNumber( Person.PhoneType phoneType ){

        return Person.PhoneNumber.newBuilder()
                .setType(phoneType)
                .setNumber(  Long.toString( ( 9000000000L + random.nextInt() ) ))
                .build();

    }

    public static void main(String[] args) {

        Generator generator = new Generator();
        System.out.println(generator.NewPerson());

    }

}
