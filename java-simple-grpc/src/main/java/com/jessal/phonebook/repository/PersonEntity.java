package com.jessal.phonebook.repository;

import lombok.Data;

import java.util.List;

@Data
public class PersonEntity {

    private Integer id;
    private String name;
    private String email;
    private List<String> phonesNumbers;

}
