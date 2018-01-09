package model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by zolotuy on 26.12.2017.
 */
public class Subject {
    private String name;
    private int id;

//    public Subject(String nameOfSubject) {
//        this.name = nameOfSubject;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        return name.equals(subject.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public Subject(String both){
        String[] pairs = both.split("_");
        this.name = pairs[0];
        this.id = Integer.valueOf(pairs[1]);
    }

    @Override
    @JsonValue
    public String toString() {
        return name +"_"+ id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subject(){}

}
