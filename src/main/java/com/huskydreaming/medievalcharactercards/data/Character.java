package com.huskydreaming.medievalcharactercards.data;

public class Character {

    private int age;
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

    public String getTitle() {
        return title == null ? "none" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName == null ? "none" : firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName == null ? "none" : middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName == null ? "none" : lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender == null ? "none" : gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description == null ? "none" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}