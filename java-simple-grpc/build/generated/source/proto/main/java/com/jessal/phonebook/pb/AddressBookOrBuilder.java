// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: person.proto

package com.jessal.phonebook.pb;

public interface AddressBookOrBuilder extends
    // @@protoc_insertion_point(interface_extends:jessal.phonebook.AddressBook)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .jessal.phonebook.Person people = 1;</code>
   */
  java.util.List<com.jessal.phonebook.pb.Person> 
      getPeopleList();
  /**
   * <code>repeated .jessal.phonebook.Person people = 1;</code>
   */
  com.jessal.phonebook.pb.Person getPeople(int index);
  /**
   * <code>repeated .jessal.phonebook.Person people = 1;</code>
   */
  int getPeopleCount();
  /**
   * <code>repeated .jessal.phonebook.Person people = 1;</code>
   */
  java.util.List<? extends com.jessal.phonebook.pb.PersonOrBuilder> 
      getPeopleOrBuilderList();
  /**
   * <code>repeated .jessal.phonebook.Person people = 1;</code>
   */
  com.jessal.phonebook.pb.PersonOrBuilder getPeopleOrBuilder(
      int index);
}
