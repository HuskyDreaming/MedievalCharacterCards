package com.huskydreaming.medievalcharactercards.data;

import com.huskydreaming.medievalcharactercards.enumerations.Message;

public class Character {

    private int age;
    private int height;
    private String title;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String description;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title == null ? Message.GENERAL_NONE.parse() : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName == null ? Message.GENERAL_NONE.parse() : firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName == null ? Message.GENERAL_NONE.parse() : middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName == null ? Message.GENERAL_NONE.parse() : lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender == null ? Message.GENERAL_NONE.parse() : gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description == null ? Message.GENERAL_NONE.parse() : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}