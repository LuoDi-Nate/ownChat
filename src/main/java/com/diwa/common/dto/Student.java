package com.diwa.common.dto;

/**
 * Created by di on 15/4/15.
 */
public class Student {
    private int id;
    private String name;
    private boolean gender;
    private int age;

    public Student(int id, String name, boolean gender, int age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public Student(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Student(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }

    public void begin(){
        System.out.println("begin");
    }

    public void end(){
        System.out.println("end");
    }
}
